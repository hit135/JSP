package kr.or.nextit.member.service;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizMailAuthException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.vo.MailAuthVO;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

public interface IMemberService {

	/**
	 * To register member
	 * @param member
	 * @author ssam
	 * @throws BizDuplicateKeyException 
	 * @throws BizNotEffectedException 
	 * @throws BizMailAuthException 
	 */
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException, BizMailAuthException;
	
	/**
	 * To check login
	 * @author ssam
	 * @param member
	 * @param request
	 * @param response
	 * @return 
	 * @throws BizNotEffectedException 
	 */
	public boolean loginCheck(MemberVO member
				, HttpServletRequest request
				, HttpServletResponse response ) throws BizNotEffectedException;
	
	
	public MemberVO getMember(String memId) throws BizNotEffectedException;
	
	public void modifyMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	
	public void removeMember(MemberVO member) throws BizNotFoundException
	, BizPasswordNotMatchedException, BizNotEffectedException;
	
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) throws BizNotFoundException;
	
	/**
	 * To remove some member
	 * @author ssam
	 * @param memMultiId
	 * @throws BizNotEffectedException 
	 */
	public void removeMultiMember(String memMultiId) throws BizNotEffectedException;

	/**
	 * To get member role
	 * @author ssam
	 * @param memId
	 * @return
	 * @throws BizNotEffectedException 
	 * @throws BizNotFoundException 
	 */
	public MemberVO getMemberRole(String memId) throws BizNotEffectedException, BizNotFoundException;


	public List<RoleInfoVO> getRoleInfo() throws BizNotEffectedException;

	
	public void updateUserRole(String memId, String[] roles);

	
	public boolean idCheck(String memId);

	
	public void memberExcelUpload(MemberVO member) throws BizNotEffectedException;

	public Boolean memberGridUpdate(MemberVO member);

	
	public boolean memberGridMultiDelete(List<String> memId_Arr);

	
	public void registerMailAuth(String mail, String authKey);

	public boolean authKeyCompare(MailAuthVO mailAuthVO);


	
}


