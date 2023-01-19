package kr.or.youth.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.service.ILoginService;
import kr.or.youth.login.service.LoginServiceImpl;
import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.main.service.IMainService;
import kr.or.youth.main.service.MainServiceImpl;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;

@Controller
public class AjaxController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	IMainService mainService;
	
	@Inject
	ILoginService loginService;
	
	// 정책 정보 페이징 아작스
	@RequestMapping("/json")
	public void json(HttpServletRequest request, HttpServletResponse response
			, @ModelAttribute("searchVO") SearchVO searchVO) throws ServletException, IOException {
		
		logger.info("AjaxController json");
		response.setContentType("application/x-json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		// object > json
		ObjectMapper mapper = new ObjectMapper();
		String jsonPolicyList = null;
		String jsonPagingVO = null;
		// get으로 던진 파라미터 받아오기
		try {
			// pagingVO 셋팅
			searchVO.pageSetting();
			// logger.info("pagingVO" + searchVO);
			List<YouthPolicy> policyList = mainService.getPolicyList(searchVO);
			jsonPolicyList = mapper.writeValueAsString(policyList);
			jsonPagingVO = mapper.writeValueAsString(searchVO);
			// logger.info("jsonPolicyList이거 모양이..?" + jsonPolicyList);
			// logger.info("jsonPagingVO의 모습" + jsonPagingVO);
			// request에 담기
			request.setAttribute("jsonPolicyList", jsonPolicyList);
		} catch (BizNotEffectedException bne) {
			bne.printStackTrace();
			request.setAttribute("bne", bne);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
		}
		
		// 배열로 받음
		out.print("[" + jsonPolicyList +","+ jsonPagingVO+ "]"); //response   
	}
	
	
	// 출석체크 아작스
	@RequestMapping("/attendCheck")
	public void attendCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	
		logger.info("AjaxController attendCheck");
		
		// 처음 설정
    	response.setContentType("application/x-json; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	// 세션에서 아이디 얻기
    	HttpSession session = request.getSession();
    	MemberVO loginMember = (MemberVO)session.getAttribute("loginMember");
    	// logger.info("서블릿에서 loginMember : " + loginMember);
    	// 리턴할 모든 날짜
    	String MemAttendDate = "";
		try {
			// mem_sysDate에서 시간들 받기(MM-DD,MM-DD,MM-DD)
			MemAttendDate = loginService.getMemAttendDate(loginMember.getMemId());
			// logger.info("서블릿으로 가져온 MemAttendDate : " + MemAttendDate);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("none");
		}
		out.print(MemAttendDate); //response   
	}
	
	
	// 출석정보 저장 아작스
	@RequestMapping("/attendance")
	public void attendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("AjaxController attendance");
		
		// 처음 설정
    	response.setContentType("application/x-json; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	// 세션에서 아이디 얻기
    	HttpSession session = request.getSession();
    	MemberVO loginMember = (MemberVO)session.getAttribute("loginMember");
    	// logger.info("아작스 서버에서의 loginMember : " + loginMember);
    	
    	// 화면에서 보낸 시간 받기
    	String loginDate = "";
    	String dateFormat = request.getParameter("dateFormat");
    	if(loginMember.getMemAttendance() != null) {
    		loginDate = loginMember.getMemAttendance().substring(0, 10);
    	}
    	// logger.info("dateFormat : " + dateFormat);
    	// logger.info("loginDate : " + loginDate);

		try {
			// 화면 날짜와 멤버의 출석 날짜가 같으면
			if(loginDate.equals(dateFormat)) {
				// logger.info("~~~~~~~~~~~loginDate dateFormat 둘이 똑같다!!!!!!!");
				throw new BizNotEffectedException();
			}
			loginService.updatePoint(loginMember.getMemId());
			loginMember = loginService.getMember(loginMember);
			// 세션에 다시 집어넣기
			session.setAttribute("loginMember", loginMember);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("fail");
			return;
		}
		
		// 배열로 받음
		out.print("success"); //response   
		
	}
	
	
	// 멤버리스트를 가져오는 아작스
	@RequestMapping("/memberList")
	public void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("AjaxController memberList");
		response.setContentType("application/x-json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		// object > json
		ObjectMapper mapper = new ObjectMapper();
		MemberVO member = new MemberVO();
		String jsonMemberList = null;
		try {
			List<MemberVO> memberList = mainService.getMemberList();
			jsonMemberList = mapper.writeValueAsString(memberList);
			// logger.info("jsonMemberList : " + jsonMemberList);
			// request에 담기
			request.setAttribute("jsonMemberList", jsonMemberList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 배열로 받음
		out.print(jsonMemberList); //response   
		
	}
	
	

}
