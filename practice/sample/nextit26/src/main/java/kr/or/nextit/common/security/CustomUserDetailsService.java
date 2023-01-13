package kr.or.nextit.common.security;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.nextit.member.mapper.IMemberMapper;
import kr.or.nextit.member.vo.MemberVO;

public class CustomUserDetailsService implements UserDetailsService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HttpServletRequest request;
	
	@Inject
	private IMemberMapper memMapper;
	
	// 여기는 어려우니
	// 이해해서 사용하기는 어려울 수도 있다
	// 아~ 이렇게 쓰는구나 정도로 하자
	
	// security:authentication-provider user-service-ref="customUserDetailsService"
	// 그런데 이렇게 쓰는 근본적인 이유가
	// 스프링 시큐리티 권한 검수는 무조건 ROLE_~~~~ 가 들어간다
	// 그러나 나는 권한은 'AD' , 'MD' 이런식으로 주고 싶다
	// 그래서 여기서 선언(설정)한 것을 시큐리티 설정에서 불러다가 쓰는 것이다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 여기 안에서 권한부분 로그인 처리를 진행할 것이다
		logger.info("username : {}", username);
		
		MemberVO member = memMapper.getMember(username);
		
		// 멤버 없으면 리턴
		if(member == null) {
			return null;
		}
		
		
		member.setRoleList(memMapper.getuserRoleListByUserId(username));
		
		// 우리가 구현한 코드를 안타기 때문에
		// 스프링 시큐리티 탈 때 여기서 세션에 멤버를 넣어줘야 한다
		HttpSession session = request.getSession();
		
		member.setUserRoleList(memMapper.getUserRole(member));
		member.setRememberMe(request.getParameter("rememberMe"));
		
		session.setAttribute("memberVO", member);
		
		// return null;
		// 이렇게 리턴해주면 알아서 스프링 시큐리티가 로그인 검수해준다
		// 리턴이 UserDetails다
		// UserDetails는 인터페이스 >> 이 인터페이스를 쓰는 클래스 선언
		return new CustomUser(member);
	}



}
