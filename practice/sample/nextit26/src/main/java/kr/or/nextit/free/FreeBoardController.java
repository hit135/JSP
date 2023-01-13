package kr.or.nextit.free;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.code.service.ICommCodeService;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.common.util.NextITFileUpload;
import kr.or.nextit.common.valid.FreeModify;
import kr.or.nextit.common.valid.FreeRegister;
import kr.or.nextit.common.vo.ResultMessageVO;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.exception.DaoException;
import kr.or.nextit.free.service.IFreeBoardService;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;


@Controller
@RequestMapping("/free")
public class FreeBoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NextITFileUpload nextITFileUpload;
	
	@Autowired
	private ResultMessageVO resultMessageVO;
	
	@Inject
	private ICommCodeService codeService;
	
	@ModelAttribute("categoryList")
	public List<CodeVO>	categoryList(){
		return codeService.getCodeListByParent("BC00");
	}
	
	@Resource(name="freeBoardService")
	private IFreeBoardService freeBoardService;	
	
	
	@RequestMapping("/freeList")
	public String freeList(@ModelAttribute("searchVO") FreeBoardSearchVO searchVO, Model model) {
		// System.out.println("FreeBoardController freeList");

		try{
			List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
			model.addAttribute("freeBoardList", freeBoardList);
			
		}catch(BizNotEffectedException bne){
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		}catch(Exception de){
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "free.freeList";
	}
	
	
	@RequestMapping("/freeForm")
	public String freeForm(@ModelAttribute("freeBoard") FreeBoardVO freeBoard) {
		// System.out.println("FreeBoardController freeForm");
		return "free.freeForm";
	}
	
	
	@RequestMapping("/freeRegister")
	public String freeRegister(@Validated(value = FreeRegister.class) @ModelAttribute("freeBoard") FreeBoardVO freeBoard
								, BindingResult error
								, Model model
								// , @RequestParam(required = false) MultipartFile[] boFiles 이것 대신
								// , MultipartHttpServletRequest mRequest 요것을 가지고 추출 가능함
								, MultipartHttpServletRequest mRequest ) {
		if(error.hasErrors()) {
			return "free.freeForm";
		}
		// 왜 밸리데이션이 이상하지?
		logger.info("freeBoard : " + freeBoard);
		
		// , MultipartHttpServletRequest mRequest를 쓸 때 사용하는 로직
		// , MultipartHttpServletRequest mRequest는 다른 request에서 다른 추가적인 작업을 하고 싶을 때
		// MultipartHttpServletRequest을 사용하면 된다
		List<MultipartFile> fileList = mRequest.getFiles("boFiles");
		MultipartFile[] boFiles = new MultipartFile[fileList.size()];
		for(int i = 0; i < fileList.size(); i++) {
			boFiles[i] = fileList.get(i);
		}
		
		
		/*
		// 파일 등록
		String originalFileName = boFiles.getOriginalFilename();
		logger.info("originalFileName : " + originalFileName);
		// 파일이 있다면 파일 업로드
		if(boFiles != null) {
			String filePath = "/home/pc32/upload/";
			try {
				byte[] bytes = boFiles.getBytes();
				File file = new File(filePath, boFiles.getOriginalFilename());
				FileCopyUtils.copy(bytes, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		// 파일 업로드 실패 성공 불리언값
		boolean fileUploadFlag = false;
		
		List<AttachVO> attachList = null;
		if(boFiles != null && boFiles[0].getSize() > 0 && !boFiles[0].getOriginalFilename().equals("") ) {
			// boFiles, db에 있는거, 폴더명
			try {
				attachList = nextITFileUpload.fileUpload(boFiles, "FREE" , "free");
				// freeBoard에 attachList를 찡겨주면 편하지 않을까?
				freeBoard.setAttachList(attachList);
				fileUploadFlag = true;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				fileUploadFlag = false;
			}
		}
		
		
		
		try{
			if(freeBoard.getBoTitle() != null && ! freeBoard.getBoTitle().equals("") ) {
				
				// 사용자가 제목에 악의적인 코드 적었는지 확인
				// XSS Cros Site Script
				// String boTitle = nextITXSSCheck(freeBoard.getBoTitle());
				// freeBoard.setBoTitle(boTitle);
				
				freeBoardService.registerBoard(freeBoard);
			}else {
				throw new Exception();
			}
			
			if(fileUploadFlag) {
				return "redirect:/free/freeList";
			}else {
				resultMessageVO.failSettion(false, "파일업로드실패", "게시글은 등록 되었으나, 파일이 업로드 되지 못하였습니다."
																	  + "전산실에 문의해주세요 042)719-8850");
			}
			
			
			
		}catch(BizNotEffectedException bne){
			// model.addAttribute("bne", bne);
			bne.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 등록 실패", "게시글 등록에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(Exception de){
			// model.addAttribute("de", de);
			de.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 등록 실패", "게시글 등록에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "/common/message";
	}
	
	
	// 악의적인 코드 확인하는 메소드
	private String nextITXSSCheck(String value) {
		StringBuffer strBuffer = new StringBuffer();
		for(int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '<':
				strBuffer.append("&lt;");
				break;
			case '>':
				strBuffer.append("&gt;");
				break;
			case '&':
				strBuffer.append("&amp;");
				break;
			case '"':
				strBuffer.append("&quot;");
				break;
			case '\'':
				strBuffer.append("&apos;");
				break;
			default:
				strBuffer.append(c);
				break;
			}
		}
		value = strBuffer.toString();
		return value;
	}


	@RequestMapping("/freeView")
	public String freeView(@ModelAttribute("searchVO") FreeBoardSearchVO searchVO, @RequestParam String boNo ,Model model) {
		// System.out.println("FreeBoardController freeView");
		// System.out.println("boNo : " + boNo);
		
		try{
			FreeBoardVO freeBoard  = null;	
			if( boNo != null && ! boNo.equals("") ) {
				freeBoard = freeBoardService.getBoard(boNo);
			}else {
				throw new Exception();
			}

			//Á¶ÈžŒö Áõ°¡ 
			freeBoardService.increaseHit(boNo);
			
			model.addAttribute("freeBoard", freeBoard);
			
		}catch(BizNotEffectedException bne){
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		}catch(Exception de){
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "free.freeView";
	}
	

	@RequestMapping("/freeEdit")
	public String freeEdit(@ModelAttribute FreeBoardSearchVO searchVO
									, @RequestParam String boNo
									, Model model) {
		// System.out.println("FreeBoardController freeEdit");
		
		try{
			FreeBoardVO freeBoard  = null;	
			if( boNo != null && ! boNo.equals("") ) {
				freeBoard = freeBoardService.getBoard(boNo);
			}else {
				throw new Exception();
			}
		
			// 조회수 증가
			freeBoardService.increaseHit(boNo);
			
			model.addAttribute("freeBoard", freeBoard);
			
		}catch(BizNotEffectedException bne){
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		}catch(Exception de){
			model.addAttribute("de", de);
			de.printStackTrace();
		}

		// return "/free/freeEdit";
		return "free.freeEdit";
	}
	
	
	@RequestMapping("/freeModify")
	public String freeModify(@Validated(value = FreeModify.class) @ModelAttribute("freeBoard") FreeBoardVO freeBoard
										, BindingResult error
										, Model model
										, @RequestParam(required = false) MultipartFile[] boFiles) {
		
		logger.info("freeBoard : " + freeBoard);
		
		if(error.hasErrors()) {
			return "free.freeEdit";
		}
		
		// 파일 업로드 실패 성공 불리언값
		boolean fileUploadFlag = true;
		
		List<AttachVO> attachList = null;
		if(boFiles != null) {
			// boFiles, db에 있는거, 폴더명
			try {
				attachList = nextITFileUpload.fileUpload(boFiles, "FREE" , "free");
				// freeBoard에 attachList를 찡겨주면 편하지 않을까?
				freeBoard.setAttachList(attachList);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				fileUploadFlag = false;
			}
		}
		
		
		try{
			if( freeBoard.getBoNo()!= null && ! freeBoard.getBoNo().equals("") ) {
				freeBoardService.modifyBoard(freeBoard);
			}else {
				throw new Exception();
			}
			if(fileUploadFlag) {
				return "redirect:/free/freeView?boNo="+freeBoard.getBoNo();
			}else {
				resultMessageVO.failSettion(false, "파일업로드실패", "게시글은 등록되었으나 "
							+ "파일은 업로드 되지 못하였습니다. 전산실에 문의 부탁드립니다. 042)-719-8850");
			}
			
		}catch(BizNotFoundException bnf){
			// model.addAttribute("bnf", bnf);
			bnf.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 수정 실패", "게시글 수정에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(BizPasswordNotMatchedException bpn){
			// model.addAttribute("bpn", bpn);
			bpn.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 수정 실패", "게시글 수정에 실패했습니다."
					+ " 비밀번호를 다시 확인해주세요");
		}catch(BizNotEffectedException bne){
			// model.addAttribute("bne", bne);
			bne.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 수정 실패", "게시글 수정에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(Exception de){
			// model.addAttribute("de", de);
			de.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 수정 실패", "게시글 수정에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
	
	
	@RequestMapping("/freeDelete")
	public String freeDelete(@ModelAttribute FreeBoardVO freeBoard, Model model) {
		// System.out.println("FreeBoardController freeDelete");

		try{
			if( freeBoard.getBoNo()!= null && ! freeBoard.getBoNo().equals("") ) {
				freeBoardService.deleteBoard(freeBoard);
			}else {
				throw new Exception();
			}
			return "redirect:/free/freeList";
			
		}catch(BizNotEffectedException bne){
			// model.addAttribute("bne", bne);
			bne.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 삭제 실패", "게시글 삭제에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(BizPasswordNotMatchedException bpn){
			// model.addAttribute("bpn", bpn);
			bpn.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 삭제 실패", "게시글 삭제에 실패했습니다."
					+ " 비밀번호를 다시 확인해주세요");
		}catch(Exception de){
			// model.addAttribute("de", de);
			de.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 삭제 실패", "게시글 삭제에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}
		
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "/common/message";
	}
	
	
	@RequestMapping("/freeHide")
	public String freeHide(@RequestParam String memId, @RequestParam String boNo, Model model) {

		try{
			if( boNo != null && ! boNo.equals("") ) {   
				freeBoardService.hideBoard(memId, boNo);
			}else {
				throw new BizNotEffectedException();
			}

			return "redirect:/free/freeList";
			
		}catch(BizNotEffectedException bne){
			// model.addAttribute("bne", bne);
			bne.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 숨김 실패", "게시글 숨김에 실패했습니다."
					+ "전산실로 문의해주세요 042)719-8850");
		}catch(Exception de){
			// model.addAttribute("de", de);
			de.printStackTrace();
			resultMessageVO.failSettion(false, "게시글 숨김 실패", "게시글 숨김에 실패했습니다."
					+ "전산실로 문의해주세요 042)719-8850");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
	
	
	
}