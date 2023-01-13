package kr.or.nextit.common.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.member.vo.MemberVO;

// 인터셉터는 HandlerInterceptorAdapter를 상속받아 쓴다
public class NextITRoleCheckIntercepter extends HandlerInterceptorAdapter{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 어드민 체크
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		
		// 세션에 멤버가 없다면
		if(member == null) {
			logger.info("(NextITRoleCheckIntercepter_preHandler) member is null" );
			response.sendRedirect(request.getContextPath()+"/login/none");
			return false;
		}
		
		List<UserRoleVO> userRoleList = member.getUserRoleList();
		for(UserRoleVO vo : userRoleList) {
			if("AD".equals(vo.getUserRole())) {
				return true;
			}
		}
		
		// 권한이 없다면
		logger.info("(NextITRoleCheckIntercepter_preHandler) You don't have Admin role");
		response.sendError(403);
		return false;
	}
	
	
	
	
}
