package kr.or.youth.login.service;

import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.vo.MemberVO;

public interface ILoginService {

	/**
	 * registe Member
	 * @param member
	 * @author pc32
	 * @throws BizNotEffectedException 
	 */
	public void registeMember(MemberVO member) throws BizNotEffectedException;
	
	/**
	 * get member what input memId
	 * @param memId
	 * @return MemberVO
	 * @author hit13
	 * @throws BizNotEffectedException 
	 */
	public MemberVO getMember(MemberVO member) throws BizNotEffectedException;

	/**
	 * update memPoint + 100
	 * @param memId
	 * @author hit13
	 * @throws BizNotEffectedException 
	 */
	public void updatePoint(String memId) throws BizNotEffectedException;
	
	/**
	 * get all attendDate of memId
	 * @param memId
	 * @return all ateendDate
	 * @author hit13
	 * @throws BizNotEffectedException 
	 */
	public String getMemAttendDate(String memId) throws BizNotEffectedException;
	
}
