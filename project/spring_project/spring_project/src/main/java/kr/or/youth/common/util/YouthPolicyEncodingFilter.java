package kr.or.youth.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class YouthPolicyEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("--- start EncodingFilter ");
		
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
		
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("--- end EncodingFilter ");
	}

}
