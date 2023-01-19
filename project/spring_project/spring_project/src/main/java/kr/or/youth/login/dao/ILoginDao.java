package kr.or.youth.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.youth.login.vo.MemberVO;

@Mapper
public interface ILoginDao {

	/**
	 * insert Member to DB
	 * @param member
	 * @author pc32
	 */
	public int insertMember(MemberVO member);

	/**
	 * get member by memId
	 * @param memId
	 * @return MemberVO
	 * @author hit13
	 */
	public MemberVO getMember(String memId);

	/**
	 * update point +100 for memId 
	 * @param memId
	 * @return resultCnt
	 * @author hit13
	 */
	public int updatePoint(String memId);

	/**
	 * 
	 * @param memId
	 * @return
	 */
	public String getMemAttendDate(String memId);

	/**
	 * for spring security , get userRoleList
	 * @param username
	 * @return
	 */
	public List<String> getuserRoleListByUserId(String username);
}
