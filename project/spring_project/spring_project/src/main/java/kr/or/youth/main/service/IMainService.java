package kr.or.youth.main.service;

import java.util.List;

import kr.or.youth.common.vo.PagingVO;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;
import kr.or.youth.policy.vo.codeVO;

public interface IMainService {
	
	/**
	 * get all list of policy
	 * @author pc32
	 * @throws BizNotEffectedException 
	 */
	public List<YouthPolicy> getPolicyList(SearchVO searchVO) throws BizNotEffectedException;

	/**
	 * get selectBox List
	 * @param perentId
	 * @return code List
	 * @author pc32
	 * @throws BizNotEffectedException 
	 */
	public List<codeVO> getSelectList(String perentId) throws BizNotEffectedException;
	
	/**
	 * get memberList
	 * @return memberList
	 * @author hit13
	 * @throws BizNotEffectedException 
	 */
	public List<MemberVO> getMemberList() throws BizNotEffectedException;
	
}
