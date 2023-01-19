package kr.or.youth.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;
import kr.or.youth.policy.vo.codeVO;

@Mapper
public interface IMainDao {

	/**
	 * select all List of policy of DB
	 * @return List<YouthPolicy>
	 * @author pc32
	 */
	public List<YouthPolicy> getPolicyList(SearchVO searchVO);

	/**
	 * get total count of policy
	 * @param pagingVo
	 * @return int >> totalCount
	 * @author pc32
	 */
	public int getTotalCount(SearchVO searchVO);

	
	/**
	 * get selectBox List of DB
	 * @param perentId
	 * @return code List
	 * @author pc32
	 */
	public List<codeVO> getSelectList(String perentId);

	/**
	 * get memberList all
	 * @return List<MemberVO>
	 * @author hit13
	 */
	public List<MemberVO> getMemberList();

}
