package kr.or.nextit.member.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.dao.IMemberDao;
import kr.or.nextit.member.dao.MemberDaoImpl;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	
	IMemberDao memberDao = new MemberDaoImpl();
	
	// 멤버 등록
	@Override
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException {
		// TODO Auto-generated method stub\
		
		MemberVO vo = memberDao.getMember(member.getMemId());
		
		if(vo != null) {
			throw new BizDuplicateKeyException();
		}
		
		int resultCnt1 =  memberDao.insertMember(member);
		if( resultCnt1 != 1) {
			throw new BizNotEffectedException();
		}
		
		int resultCnt2 = memberDao.insertUserRole(member);
		if( resultCnt2 != 1) {
			throw new BizNotEffectedException();
		}
		
	}

	
	// 로그인 체크
	@Override
	public void loginCheck(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		MemberVO vo = null;
		if(member.getMemId() == null || member.getMemPass() == null) {
			try {
				response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=null");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			vo = memberDao.loginCheck(member);
		}
		try {
			if(vo == null) {
				System.out.println("member 정보를 불러오지 못하였습니다.");
				response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=fail");
			}else {
				
				// 로그인 성공한 부분
				// DB가서 권한을 받아올것이다
				// 미리 설계한 것
				List<UserRoleVO> userRoleList = memberDao.getUserRole(member);
				
				if(userRoleList != null) {
					vo.setUserRoleList(userRoleList);
				}
				
				
				
				HttpSession session = request.getSession();
				// 세션에 담아놨음 vo를
				session.setAttribute("memberVO", vo);

				String rememberMe = member.getRememberMe();
				if (rememberMe != null && rememberMe.equals("Y")) {
					Cookie cookie= new Cookie("rememberMe", member.getMemId());
					response.addCookie(cookie);
				}else{
					Cookie cookie= new Cookie("rememberMe", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				response.sendRedirect(request.getContextPath()+"/home/home.jsp");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BizNotEffectedException();
		}
		
		
	}

	
	// 개인 멤버 가져오기
	@Override
	public MemberVO getMember(String memId) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		MemberVO member = null;
		if(memId != null && ! memId.equals("")) {
			member = memberDao.getMember(memId);
			if(member == null) {
				throw new BizNotEffectedException();
			}
		}
		return member;
	}
 
	
	// 멤버 지우기
	@Override
	public void removeMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
		MemberVO vo = null;
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memberDao.getMember(member.getMemId());
		}
		
		if( vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass()) ){
			throw new BizPasswordNotMatchedException();
		}
		
		int resultCnt = memberDao.deleteMember(member);
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
	}

	// 멤버 수정하기
	@Override
	public void modifyMember(MemberVO member)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		MemberVO vo = null;
		if( member.getMemId() != null && ! member.getMemId().equals("")) {
			 vo = memberDao.getMember(member.getMemId());
		}
		
		if(vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		int resultCnt = memberDao.updateMember(member);
		
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
		
	}

	// 멤버 리스트 전부 가져오기
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO memSearchVO) throws BizNotEffectedException {
		
		// (페이징) 일단 멤버의 총 수를 구해야함
		// (검색) 멤버의 총 수를 검색에 맞게 구해야함
		int totalCount = memberDao.getTotalCount(memSearchVO);
		
		System.out.println("totalCount가 dao에서 검색에 맞게 됐는가?" + totalCount);
		// 그리고 토탈 카운트를 memPagingVO에 넣어줘야지
		memSearchVO.setTotalRowCount(totalCount);
		
		// 나머지 페이지들함수 구현해주고
		memSearchVO.pageSetting();
		
		// 이제 리스트를 구할때 페이징도 가져간다
		List<MemberVO> memberList = memberDao.getMemberListToDB(memSearchVO);
		
		
		if(memberList == null) {
			throw new BizNotEffectedException();
		}
		
		
		System.out.println("비즈니스 로직의 memberList : " + memberList );
		
		return memberList;
	}
	
	
	
	
	
	
	
}
