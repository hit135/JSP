package kr.or.nextit.common.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.nextit.member.vo.MemberVO;

// User은 UserDetails을 인터페이스로 받으니
// 그것을 상속 받으면 됨
public class CustomUser extends User {
	
	public CustomUser(MemberVO member) {
		super(
			 member.getMemId()
			,member.getMemPass()
			,member.getRoleList().stream()
				.map(role -> new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
				);
	}
	
	
}
