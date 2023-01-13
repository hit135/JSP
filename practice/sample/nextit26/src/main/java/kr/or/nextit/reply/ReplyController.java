package kr.or.nextit.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.member.vo.MemberVO;
import kr.or.nextit.reply.service.IReplyService;
import kr.or.nextit.reply.vo.ReplyPagingVO;
import kr.or.nextit.reply.vo.ReplyVO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	@RequestMapping("/replyRegister")
	public String replyRegister(@ModelAttribute ReplyVO replyVO ) {
		logger.info("ReplyVO : " + replyVO.toString());
		
		try {
			replyService.replyRegister(replyVO);
		} catch (BizNotEffectedException e) {
			e.printStackTrace();
		}
		
		return "/free/freeView";
	}
	
	// @RequestMapping("replyList") >> 한글이 깨진다면..
	// 리퀘스트 맵힝에 프로듀시스라는 속성이 있다
	// @RequestMapping(value="/replyList", produces = "application/json;charset=utf-8")
	// @ResponseBody
	@RequestMapping("/replyList")
	public String replyList(@ModelAttribute("replyPagingVO") ReplyPagingVO replyPagingVO) {
		logger.info("replyPagingVO" + replyPagingVO.toString());
		
		// List<ReplyVO> replyList = replyService.getRplyListByParent(replyPagingVO);
		
		replyPagingVO.setReplyList(replyService.getRplyListByParent(replyPagingVO));
		
		// return "free/freeView";
		// return replyPagingVO;
		return "/free/part/reply";
	}
	
	
	@RequestMapping("/replyDelete")
	@ResponseBody
	public Map<String, Object> replyDelete(@ModelAttribute ReplyVO replyVO, HttpSession session) {
		logger.info("replyVO.getReNo() : " + replyVO.getReNo());
		logger.info("replyVO.getReMemId() : " + replyVO.getReMemId());
		
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		logger.info("member.getMemId() : ", member.getMemId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(replyVO.getReMemId() != null && replyVO.getReMemId().equals(member.getMemId())) {
			try {
				replyService.replyDelete(replyVO);
				map.put("result", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", false);
			}
		}
		
		// return "/free/freeView";
		return map;
	}
	
	
	@RequestMapping("/replyUpdate")
	@ResponseBody
	public Map<String, Object> replyUpdate(@ModelAttribute ReplyVO replyVO, HttpSession session){
		logger.info("replyVO.getReNo() : "+ replyVO.getReNo());
		logger.info("replyVO.getReMemId() : "+ replyVO.getReMemId());
		logger.info("replyVO.getReContent() : "+ replyVO.getReContent());
		// 수정자랑 세션의 아이디가 같은지 검증
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		// 결과를 위한 맵
		Map<String, Object> map = new HashMap<String, Object>();
		if(replyVO.getReMemId() != null && replyVO.getReMemId().equals(member.getMemId())) {
			logger.info("성공한 곳으로 잘 들어왔음");
			try {
				replyService.replyUpdate(replyVO);
				map.put("result", true);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", false);
			}
		}
		// 여기서 에러남.. 왜?
		return map;
	}
	
	
	
}
