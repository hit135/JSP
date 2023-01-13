package kr.or.nextit.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.member.vo.MemberVO;

public class NextITLoginCheckFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String uri = req.getRequestURI();
		System.out.println("uri : "+ uri);
		
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("memberVO");
		
		if(uri.indexOf("memberRegister") > -1) {
			chain.doFilter(request, response);
		}else {
			if(vo != null) {
				System.out.println("--- --- ---sucess login check");
				chain.doFilter(request, response);
			}else {
				System.out.println("--- --- ---fail login check");
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect(req.getContextPath()+"/login/none");
			}
		}
		
		
		
	}

}
