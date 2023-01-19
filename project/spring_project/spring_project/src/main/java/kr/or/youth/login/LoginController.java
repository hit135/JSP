package kr.or.youth.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.youth.exception.BizDuplicateKeyException;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.service.ILoginService;
import kr.or.youth.login.vo.MemberVO;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	ILoginService loginService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response
						, @ModelAttribute("member") MemberVO member) throws Exception {
		
		// 세션 사용
		HttpSession session = request.getSession();
		logger.info("Login process에 들어옴");
		
		// 화면에 alert 띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 넘긴거 받기
		// logger.info("Login process의 member : " + member);
		
		// 회원 정보
		MemberVO dbMember = new MemberVO();
		
		if(member.getMemName().equals("") || member.getMemName() == null) {
			// sign in
			// logger.info("sign in 잘옴!!!!!!!!!!!!!!!!!!!!!!");
			
			try {
				dbMember = loginService.getMember(member);
				// 비번 틀리면
				if(dbMember.getMemPass().equals("fail")) {
					throw new BizDuplicateKeyException();
				}
			// 아이디 없음
			} catch (BizNotEffectedException bne) {
				bne.printStackTrace();
				out.println("<script>alert('ID가 없습니다! 회원가입을 진행해주세요!');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/'; </script>");
				out.flush();
				return null;
			// 비번 틀림
			} catch (BizDuplicateKeyException bde) {
				bde.printStackTrace();
				out.println("<script>alert('비밀번호가 틀립니다! 다시 로그인해주십시오!');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/'; </script>");
				out.flush();
				return null;
			// 그외의 오류
			} catch (Exception e) {
				out.println("<script>alert('로그인에 실패했습니다!\n 전산실에 문의해주세요 010-4403-9382');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/'; </script>");
				out.flush();
				e.printStackTrace();
				return null;
			}
			// 로그인 성공
			// 일단 비번 지우고
			dbMember.setMemPass("");
			// logger.info("dbMember : " + dbMember);
			// 세션에 등록
			session.setAttribute("loginMember", dbMember);
			out.println("<script>alert('" + dbMember.getMemName() + "님 반갑습니다!');" +
					"function getContextPath() {\n" + 
					"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
					"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
					"};"
					+ "location.href=getContextPath()+'/'; </script>");
			out.flush();
		}else {
			// sign up
			try {
				loginService.registeMember(member);
			} catch (BizNotEffectedException bne) {
				bne.printStackTrace();
				out.println("<script>alert('이미 등록된 계정입니다..');" +
							"function getContextPath() {\n" + 
							"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
							"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
							"};"
							+ "location.href=getContextPath()+'/'; </script>");
				out.flush();
				return null;
				
			} catch (Exception e) {
				e.printStackTrace();
				out.println("<script>alert('회원등록에 실패했습니다\\n 전산실에 문의해주세요 010-4403-9382');" +
						"function getContextPath() {\n" + 
						"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
						"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
						"};"
						+ "location.href=getContextPath()+'/'; </script>");
				out.flush();
				return null;
			}
			out.println("<script>alert('계정이 등록되었습니다!');" +
					"function getContextPath() {\n" + 
					"	var hostIndex = location.href.indexOf( location.host ) + location.host.length;\n" + 
					"	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );\n" + 
					"};"
					+ "location.href=getContextPath()+'/'; </script>");
			out.flush();
			return null;
		}
		
		
		
		return "main/main";
	}
	
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("LogOut process에 잘 도착!");
		
		// 세션에서 로그인한 아이디 지우기
		HttpSession session = request.getSession();
		session.removeAttribute("loginMember");
		
		return "main/main";
	}
	
	
	@RequestMapping("/adminLogin")
	public String adminLogin(){
		return "main/adminLogin";
	}
	
	
	@RequestMapping("/adminLogin/loginCheck")
	public String loginCheck(){
		return "main/members";
	}
	
	
}
