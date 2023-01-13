package kr.or.nextit.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.code.service.ICommCodeService;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.common.util.NextITFileUpload;
import kr.or.nextit.common.valid.MemberModify;
import kr.or.nextit.common.valid.MemberRegister;
import kr.or.nextit.common.vo.ResultMessageVO;
import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizMailAuthException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.exception.DaoException;
import kr.or.nextit.member.service.IMemberService;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

@Controller
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NextITFileUpload nextITFileUpload;
	
	@Autowired
	private IMemberService memberService;
	
	@Inject
	private ICommCodeService codeService;
	
	@Autowired
	private ResultMessageVO resultMessageVO;
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList(){
		return codeService.getCodeListByParent("JB00");
	}
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbybList(){
		return codeService.getCodeListByParent("HB00");
	}
	
	// validate한 것들을 @Validated을 이용해 받아줘야함
	// BindingResult로 에러를 받아주며 , @Validated 바로 뒤에 뒤따라 나와야함
	@RequestMapping(value="/member/memberRegister", method=RequestMethod.POST )
	public String memberRegister(@Validated(value = MemberRegister.class) @ModelAttribute("member") MemberVO member
									, BindingResult error
									, Model model
									, @RequestParam(required = false) MultipartFile[] profilePhoto) {
		if(error.hasErrors()) {
			return "/login/join";
		}
		
		
	// 파일 업로드 실패 성공 불리언값
			boolean fileUploadFlag = true;
			
			List<AttachVO> attachList = null;
			if(profilePhoto != null) {
				// boFiles, db에 있는거, 폴더명
				try {
					attachList = nextITFileUpload.fileUpload(profilePhoto, "PROFILEPHOTO" , "profilePhoto");
					// freeBoard에 attachList를 찡겨주면 편하지 않을까?
					member.setAttachList(attachList);
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					fileUploadFlag = false;
				}
			}
		
		// 실패했을때 처리 하나의 페이지로
		
		
		try{
				if(member.getMemId() != null && ! member.getMemId().equals("")) {
					memberService.registerMember(member);
				}else {
					throw new Exception();
				}
				
				if(fileUploadFlag) {
					return "redirect:/login/sign";
				}else {
					resultMessageVO.failSettion(false, "회원 등록 성공, 프로필 업로드 실패", "회원은 등록 되었으나, 프로필이 업로드 되지 못하였습니다."
							  + "전산실에 문의해주세요 042)719-8850");
				}
			
		}catch(BizDuplicateKeyException bde){
			// request.setAttribute("bde", bde);
			// model.addAttribute("bde", bde);
			bde.printStackTrace();
			resultMessageVO.failSettion(false, "회원 등록 실패", "이미 사용중인 아이디 입니다! 다른 아이디를 사용해주세요");
		}catch(BizMailAuthException bmae){
				bmae.printStackTrace();
				resultMessageVO.failSettion(false, "회원 등록 실패", "메일이 인증 되지 않았습니다. 메일 인증 버튼으로 인증해주세요");
		}catch(BizNotEffectedException bne){
			// request.setAttribute("bne", bne);
			// model.addAttribute("bne", bne);
			bne.printStackTrace();
			resultMessageVO.failSettion(false, "회원 등록 실패", "회원 등록에 실패하였습니다."
					+ " 전살실에 문의 부탁드립니다. 042)719-8850");
		}catch(Exception de){
			// request.setAttribute("de", de);
			// model.addAttribute("de", de);
			de.printStackTrace();
			resultMessageVO.failSettion(false, "회원 등록 실패", "회원 등록에 실패하였습니다."
					+ " 전살실에 문의 부탁드립니다. 042)719-8850");
		}
		
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
	
	
	@RequestMapping("/member/memberView")
	public String memberView(@RequestParam("memId") String memId, Model model) {

		try{
			MemberVO member = null;
			if(memId != null && !memId.equals("")) {
				member = memberService.getMember(memId);
			}else {
				throw new BizNotEffectedException();
			}
			//request.setAttribute("member", member);
			model.addAttribute("member", member);
			
		}catch(BizNotEffectedException bne){
			bne.printStackTrace();
			//request.setAttribute("bne", bne);
			model.addAttribute("bne", bne);
		}catch(DaoException de){
			de.printStackTrace();
			//request.setAttribute("de", de);
			model.addAttribute("de", de);
		}
		return "member.memberView";
	}
	
	
	@RequestMapping("/member/memberEdit")
	public String memberEdit(@RequestParam("memId") String memId, Model model  ) {

		System.out.println("(memberEdit) memId :"+ memId );
		
		try{
			MemberVO member = memberService.getMember(memId);
			// 비번 안나오게
			member.setMemPass("");
			model.addAttribute("member", member);
		}catch(BizNotEffectedException bne){
			bne.printStackTrace();
			model.addAttribute("bne", bne);
		}catch(DaoException de){
			de.printStackTrace();
			model.addAttribute("de", de);
		}
		return "member.memberEdit";
	}
	
	@RequestMapping("/member/memberModify")
	public String memberModify(@Validated(value = MemberModify.class) @ModelAttribute("member") 
								   MemberVO member
								  ,BindingResult error
								  ,Model model 
								  ,@RequestParam(required = false) MultipartFile[] profilePhoto) {
		
		
		if(error.hasErrors()) {
			return "member.memberEdit";
		}
		
		
		// 파일 업로드 실패 성공 불리언값 >> 파일을 memId로 업로드 해주면 된다..
		// 그럼 키값이 늘어나기 때문에 어차피 프로필 이미지 불러오는 것은
		// 키값이 가장 큰 값 하나만 불러오므로
		boolean fileUploadFlag = true;
		
		List<AttachVO> attachList = null;
		if(profilePhoto != null) {
			// boFiles, db에 있는거, 폴더명
			try {
				attachList = nextITFileUpload.fileUpload(profilePhoto, "PROFILEPHOTO" , "profilePhoto");
				member.setAttachList(attachList);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				fileUploadFlag = false;
			}
		}
		
		
		try{
			if(member.getMemId() !=null && ! member.getMemId().equals("")) {
				memberService.modifyMember(member);
			}else {
				throw new Exception();
			}
			
			// 프로필 업로드 수정 성공시
			if(fileUploadFlag) {
				return "redirect:/member/memberView?memId="+member.getMemId();
			}else {
				resultMessageVO.failSettion(false, "회원정보 정보 수정 완료, 회원 이미지 실패", "회원정보 수정에는 성공했지만, 이미지 수정에 실패했습니다."
						+ " 전산실로 문의해주세요 042)719-8850");
			}
			
		}catch(BizNotEffectedException bne){
			bne.printStackTrace();
			// model.addAttribute("bne", bne);
			resultMessageVO.failSettion(false, "회원정보 수정 실패", "회원정보 수정에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(BizPasswordNotMatchedException bpn){
			bpn.printStackTrace();
			// model.addAttribute("bpn", bpn);
			resultMessageVO.failSettion(false, "회원정보 수정 실패"
					, " 입력하신 패스워드가 올바르지 않습니다. 다시 입력해주세요!");
		}catch(BizNotFoundException bnf){
			bnf.printStackTrace();
			// model.addAttribute("bnf", bnf);
			resultMessageVO.failSettion(false, "회원정보 수정 실패", "회원정보 수정에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(Exception de){
			de.printStackTrace();
			// model.addAttribute("de", de);
			resultMessageVO.failSettion(false, "회원정보 수정 실패", "회원정보 수정에 실패했습니다."
					+ " 전산실로 문의해주세요 042)719-8850");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
	
	
	@RequestMapping("/member/memberDelete")
	public String memberDelete(@ModelAttribute MemberVO member, Model model, HttpServletRequest request) {

		
		try{
			if(member.getMemId() !=null && ! member.getMemId().equals("")) {
				memberService.removeMember(member);
			}else {
				throw new Exception();
			}
			HttpSession session =  request.getSession();
			session.removeAttribute("memberVO");
			
			return "redirect:/login/quit";
			
		}catch(BizNotFoundException bnf){
			// request.setAttribute("bnf", bnf);
			bnf.printStackTrace();
			resultMessageVO.failSettion(false, "회원 탈퇴 실패", "회원탈퇴에 실패했습니다."
					+ "  전산실로 문의해주세요 042)719-8850");
		}catch(BizPasswordNotMatchedException bpn){
			// request.setAttribute("bpn", bpn);
			bpn.printStackTrace();
			resultMessageVO.failSettion(false, "회원 탈퇴 실패", "입력하신 비밀번호가 올바르지 않습니다. 다시 입력해주세요");
		}catch(BizNotEffectedException bne){
			// request.setAttribute("bne", bne);
			bne.printStackTrace();
			resultMessageVO.failSettion(false, "회원 탈퇴 실패", "회원탈퇴에 실패했습니다. "
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(Exception de){
			// request.setAttribute("de", de);
			de.printStackTrace();
			resultMessageVO.failSettion(false, "회원 탈퇴 실패", "회원탈퇴에 실패했습니다. "
					+ " 전산실로 문의해주세요 042)719-8850");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
	
	@RequestMapping("/member/memberList")
	public String memberList(Model model, @ModelAttribute("searchVO") MemberSearchVO searchVO) {

		try{
			List<MemberVO> memberList = memberService.getMemberList(searchVO);
			model.addAttribute("memberList", memberList);
		}catch(BizNotFoundException bnf){
			bnf.printStackTrace();
			model.addAttribute("bnf", bnf);
		}catch(DaoException de){
			de.printStackTrace();
			model.addAttribute("de", de);
		}
		
		return "member.memberList";
	}
	
	@RequestMapping("/member/memberMultiDelete")
	public String memberMultiDelete(@RequestParam String memMultiId, Model model) {

		
		
		try{
			memberService.removeMultiMember(memMultiId);
			return "redirect:/member/memberList";
			
		}catch(BizNotEffectedException bne){
			bne.printStackTrace();
			// model.addAttribute("bne", bne);
			resultMessageVO.failSettion(false, "회원 삭제 실패", "회원삭제에 실패했습니다. "
					+ " 전산실로 문의해주세요 042)719-8850");
		}catch(Exception de){
			de.printStackTrace();
			// model.addAttribute("de", de);
			resultMessageVO.failSettion(false, "회원 삭제 실패", "회원삭제에 실패했습니다. "
					+ " 전산실로 문의해주세요 042)719-8850");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
	
	@RequestMapping("/member/memberRole")
	public String memberRole(@RequestParam(name="memId", required = false) String memId, Model model) {

		
		try{
			MemberVO member = null;
			if( memId != null && ! memId.equals("")) {
				member = memberService.getMemberRole(memId);
			}
			//request.setAttribute("member", member);
			model.addAttribute("member", member);
			
			List<RoleInfoVO> roleInfoList  = memberService.getRoleInfo();
			//request.setAttribute("roleInfoList", roleInfoList);
			model.addAttribute("roleInfoList", roleInfoList);
			
		}catch(BizNotFoundException bnf){
			bnf.printStackTrace();
			//request.setAttribute("bnf", bnf);
			model.addAttribute("bnf", bnf);
			
		}catch(BizNotEffectedException bne){
			bne.printStackTrace();
			//request.setAttribute("bne", bne);
			model.addAttribute("bne", bne);
			
		}catch(Exception de){
			de.printStackTrace();
			//request.setAttribute("de", de);
			model.addAttribute("de", de);
		}
		return "member.memberRole";
	}
	
	
	@RequestMapping("/member/memberRoleUpdate")
	public String memberRoleUpdate(@RequestParam("memId") String memId
			, @RequestParam(required = false) String[] userRole
			, Model model) {

		

	 	try{
			memberService.updateUserRole(memId, userRole);
			return "redirect:/member/memberList";
		}catch(Exception de){
			de.printStackTrace();
			// model.addAttribute("de", de);
			resultMessageVO.failSettion(false, "회원권한 수정 실패", "회원권한 수정에 실패했습니다. "
					+ " 전산실로 문의해주세요 042)719-8850");
		}
	 	model.addAttribute("resultMessageVO", resultMessageVO);
	 	
		return "/common/message";
	}
	
/*	
	@RequestMapping(value = "/member/memberExcelUpload", method = RequestMethod.POST)
	public String memberExceUpload(@RequestAttribute(required = true) MultipartFile memberExcelUpload
										, Model model) {
		
		String path1 = "/home/pc32/upload/excel";
		String path2 = UUID.randomUUID().toString();
		
		String fileName = memberExcelUpload.getOriginalFilename();
		File saveFile = new File(path1 + File.separator + path2 + File.separator + fileName);
		
		if(!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			// 여기서 업로드 했고
			memberExcelUpload.transferTo(saveFile);
			
			// 업로드 잘되면
			return "redirect:/member/memberList";
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMessageVO.failSettion(false, "회원 엑셀 등록실패", "회원 엑셀 등록에 실패하였습니다. "
					+ "전산실에 문의 부탁드립니다. 042)719-8850");
		}
		
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}
*/
	
	@RequestMapping(value = "/member/memberExcelUpload", method = RequestMethod.POST)
	public String memberExceUpload(@RequestAttribute(required = true) MultipartFile memberExcelUpload
										, Model model) {
		
		String path1 = "/home/pc32/upload/excel";
		String path2 = UUID.randomUUID().toString();
		
		String fileName = memberExcelUpload.getOriginalFilename();
		File saveFile = new File(path1 + File.separator + path2 + File.separator + fileName);
		
		/*
		String fileName = memberExcelUpload.getOriginalFilename();
		InputStream inputStream = memberExcelUpload.getInputStream();
		FileInputStream fis = (FileInputStream) inputStream;
		
		요로코롬 하면 굳이 엑셀 파일을 서버에 저장하지 않고
		fis에 사용자가 올린 엑셀 데이터를 받아온다
		 */
		
		if(!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			// 여기서 업로드 했고
			memberExcelUpload.transferTo(saveFile);
			
			// 파일 업로드가 끝났으니 엑셀의 데이터를 읽어서 디비에 적재하기
			
			File excel = new File(path1 + File.separator + path2 + File.separator + fileName);
			FileInputStream fis = new FileInputStream(excel);
			
			Workbook workbook = null;
			if(fileName.endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis);
			}else if(fileName.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			}
			
			if(workbook != null) {
				int sheets = workbook.getNumberOfSheets();
				getSheet(workbook, sheets);
			}
			
			
			
			// 업로드 잘되면
			return "redirect:/member/memberList";
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMessageVO.failSettion(false, "회원 엑셀 등록실패", "회원 엑셀 등록에 실패하였습니다. "
					+ "전산실에 문의 부탁드립니다. 042)719-8850");
		}
		
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "/common/message";
	}

	
	// 해당 시트에 따라 로우 개수 획득
	private void getSheet(Workbook workbook, int sheets) throws BizNotEffectedException {
		
		for(int i = 0; i < sheets; i++) {
			// 시트 수만큼 포문 돌려서 시트 안의 내용 받기
			Sheet sheet = workbook.getSheetAt(i);
			int startRow = sheet.getFirstRowNum();
			int endRow = sheet.getLastRowNum();
			
			getRow(sheet, startRow, endRow);
		}
		
	}

	// 로우에 따라 열 개수 취득
	private void getRow(Sheet sheet, int startRow, int endRow) throws BizNotEffectedException {
		
		int cellCnt = 0;
		for(int i = startRow; i <= endRow; i++) {
			Row row = sheet.getRow(i);
			if(row != null) {
				if( i == startRow) {
					// 컬럼(셀)명을 나타내는 첫번째 행은 데이터는 안읽지만 >> 개수는 읽어온다
					cellCnt = row.getLastCellNum();
				}else {
					// 데이터 읽을 함수
					getCellValue(row, cellCnt);
				}
			}
		}
		
	}

	// 로우와 셀 개수에 따라 데이터를 읽어오는 함수
	private void getCellValue(Row row, int cellCnt) throws BizNotEffectedException {
		
		String[] excelData = new String[cellCnt];
		
		// 셀 개수만큼 셀 내용 취득
		for(int i = 0; i < cellCnt; i++) {
			Cell cell = row.getCell(i);
			
			if(cell != null) {
				// 데이터의 타입에 따라 어떻게 받을 것인가
				switch (cell.getCellType()) {
				case BLANK:
					excelData[i] = "";
					break;
					
				case STRING:
					excelData[i] = cell.getStringCellValue();
					break;
				
				// 숫자일때
				case NUMERIC:
					// 그런데 또 날짜 형식의 숫자일때

					if(DateUtil.isCellDateFormatted(cell)) {
						// cell이 날짜 형식일 때
						excelData[i] = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
					}else {
						// cell이 그냥 숫자 형식 일 때
						excelData[i] = cell.getNumericCellValue()+"";
					}
					break;
				
				// 에러 처리
				case ERROR:
					excelData[i] = "";
					break;
					
				default:
					excelData[i] = "";
					break;
				}
			}
		}
		
		// member에 취득한 값들 넣어주기
		MemberVO member = new MemberVO();
		for(int i = 0; i < excelData.length; i++) {
			
			if(i==0) {
				member.setMemId(excelData[i]);
			}else if(i==1) {
				member.setMemPass(excelData[i]);
			}else if(i==2) {
				member.setMemName(excelData[i]);
			}else if(i==3) {
				member.setMemBir(excelData[i]);
			}else if(i==4) {
				member.setMemZip(excelData[i]);
			}else if(i==5) {
				member.setMemAdd1(excelData[i]);
			}else if(i==6) {
				member.setMemAdd2(excelData[i]);
			}else if(i==7) {
				member.setMemHp(excelData[i]);
			}else if(i==8) {
				member.setMemMail(excelData[i]);
			}else if(i==9) {
				member.setMemJob(excelData[i]);
			}else if(i==10) {
				member.setMemHobby(excelData[i]);
			}else if(i==11) {
				member.setMemMileage((int)Double.parseDouble(excelData[i]));	//마일리지가 0.0 이므로 더블로 받고 int로 형변환
			}else if(i==12) {
				member.setMemDelYn(excelData[i]);
			}else if(i==13) {
				member.setMemJoinDate(excelData[i]);
			}else if(i==14) {
				member.setMemEditDate(excelData[i]);
			}
			
		}
		
		if(member != null) {
			memberService.memberExcelUpload(member);
		}
	}
	
	/* 임의의 엑셀파일 다운로드
	@RequestMapping("/member/memberExcelDownload")
	public void memberExcelDownload(HttpServletResponse response) {
		
		XSSFWorkbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		
		wb = new XSSFWorkbook();
		sheet = wb.createSheet("NextIT회원목록");
		
		try {
		
			// row 생성
			row = sheet.createRow(0);	// 0번째 행
			cell = row.createCell(0);
			cell.setCellValue("순번");
			cell = row.createCell(1);
			cell.setCellValue("아이디");
			cell = row.createCell(2);
			cell.setCellValue("이름");
			
			// row 생성
			row = sheet.createRow(1);	// 1번째 행
			cell = row.createCell(0);
			cell.setCellValue("1");
			cell = row.createCell(1);
			cell.setCellValue("nextit11");
			cell = row.createCell(2);
			cell.setCellValue("넥스트아이티11");
			
			//row 생성
			row = sheet.createRow(2);	// 2번째 행
			cell = row.createCell(0);
			cell.setCellValue("2");
			cell = row.createCell(1);
			cell.setCellValue("nextit12");
			cell = row.createCell(2);
			cell.setCellValue("넥스트아이티12");
			
			// 컨텐츠 타입과 파일면 지정
			response.setContentType("ms-vnd/excel");
			
			//attachment는 다운로드를 나타내고 filename은 파일이름 지정
			response.setHeader("Content-Disposition", "attachment; filename=memberList.xlsx"); 
			
		
			wb.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	*/
	
	@RequestMapping("/member/memberExcelDownload")
	public void memberExcelDownload(@ModelAttribute MemberSearchVO searchVO
						, HttpServletResponse response) {
		
		logger.info("searchVO : " + searchVO.toString());
		
		XSSFWorkbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		
		try {
		
			List<MemberVO> memberList = memberService.getMemberList(searchVO);
			
			if(memberList != null) {
				
				wb = new XSSFWorkbook();
				sheet = wb.createSheet("NextIT회원목록");
				
				int cellCount = 0;
				row = sheet.createRow(0);
				
				cell = row.createCell(cellCount++);
				cell.setCellValue("순번");
				cell = row.createCell(cellCount++);
				cell.setCellValue("ID");
				cell = row.createCell(cellCount++);
				cell.setCellValue("회원명");
				cell = row.createCell(cellCount++);
				cell.setCellValue("HP");
				cell = row.createCell(cellCount++);
				cell.setCellValue("생일");
				cell = row.createCell(cellCount++);
				cell.setCellValue("직업");
				cell = row.createCell(cellCount++);
				cell.setCellValue("취미");
				cell = row.createCell(cellCount++);
				cell.setCellValue("마일리지");
				
				for(int i = 0; i < memberList.size(); i++) {
					row = sheet.createRow(i+1);
					cellCount = 0;
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getRnum());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemId());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemName());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemHp());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemBir());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemJob());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemHobby());
					cell = row.createCell(cellCount++);
					cell.setCellValue(memberList.get(i).getMemMileage());
				}
				
				
				// 컨텐츠 타입과 파일면 지정
				response.setContentType("ms-vnd/excel");
				
				//attachment는 다운로드를 나타내고 filename은 파일이름 지정
				response.setHeader("Content-Disposition", "attachment; filename=memberList.xlsx"); 
				
			
				wb.write(response.getOutputStream());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 배열로 보냈을때
	@RequestMapping("/member/memberGrid")
	public String memberGrid(@RequestParam(required = false, name = "sendData[]")List<String> sendData  ) {
		logger.info("sendData : " + sendData.toString());
		
		
		return "member.memberList";
	}
	*/
	// serialize 썼을 때
	
	@RequestMapping("/member/memberGrid")
	@ResponseBody
	public MemberSearchVO memberGrid(@ModelAttribute MemberSearchVO searchVO) {
		logger.info("searchVO : " + searchVO.toString());
		List<MemberVO> memberList = null;
		try {
			memberList = memberService.getMemberList(searchVO);
			searchVO.setMemberList(memberList);
			
			List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
			searchVO.setJobList(jobList);
			
			List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
			searchVO.setHobbyList(hobbyList);
			
		} catch (BizNotFoundException e) {
			e.printStackTrace();
		}
		
		// return "member.memberList";
		return searchVO;
	}
	
	
	@RequestMapping("/member/memberGridUpdate")
	@ResponseBody
	public boolean memberGridUpdate(@RequestBody Map<String, Object> map) {
		
		String memId = (String) map.get("memId");
		String memName = (String) map.get("memName");
		String memHp = (String) map.get("memHp");
		String memJob = (String) map.get("memJob");
		String memHobby = (String) map.get("memHobby");
		int memMileage = (int) map.get("memMileage");
		
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member.setMemName(memName);
		member.setMemHp(memHp);
		member.setMemJob(memJob);
		member.setMemHobby(memHobby);
		member.setMemMileage(memMileage);
		
		logger.info("member : " + member.toString());
		
		boolean result = memberService.memberGridUpdate(member);
		
		// return "member.memberList";
		return result;
	}
	
	
	
	// 그리드에서 아이디들 삭제
	@RequestMapping("/member/memberGridMultiDelete")
	@ResponseBody
	public boolean memberGridMultiDelete(@RequestParam(required = true
							, name="memIdArr[]") List<String> memId_Arr ) {
		logger.info("memId_arr : " + memId_Arr);
		
		boolean result = false;
		if(memId_Arr.size() > 0) {
			result = memberService.memberGridMultiDelete(memId_Arr);
		}
		
		// return "member.memberList";
		return result;
	}
	
	
	
	
	
	
}
