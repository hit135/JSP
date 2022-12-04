package kr.or.nextit.member.service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

public interface IMemberService {

	/**
	 * To register member
	 * @param member
	 * @author ssam
	 * @throws BizDuplicateKeyException 
	 * @throws BizNotEffectedException 
	 */
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException;
	
	/**
	 * To check login
	 * @author ssam
	 * @param member
	 * @param request
	 * @param response
	 * @throws BizNotEffectedException 
	 */
	public void loginCheck(MemberVO member
				, HttpServletRequest request
				, HttpServletResponse response ) throws BizNotEffectedException;
	
	
	public MemberVO getMember(String memId) throws BizNotEffectedException;
	
	public void modifyMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	 
	
	public void removeMember(MemberVO member) throws BizNotFoundException
	, BizPasswordNotMatchedException, BizNotEffectedException;
	
	
	/**
	 * get memberList
	 * @author pc32
	 * @return
	 * @throws BizNotEffectedException 
	 */
	public List<MemberVO> getMemberList(MemberSearchVO memSearchVO) throws BizNotEffectedException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
