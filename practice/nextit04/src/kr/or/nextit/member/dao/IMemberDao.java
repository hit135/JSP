package kr.or.nextit.member.dao;


import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

public interface IMemberDao {

	MemberVO getMember(String memId);

	int insertMember(MemberVO member);

	int insertUserRole(MemberVO member);

	MemberVO loginCheck(MemberVO member);

	public int updateMember(MemberVO member);

	int deleteMember(MemberVO member);

	List<UserRoleVO> getUserRole(MemberVO member);

	
	List<MemberVO> getMemberListToDB(MemberSearchVO memSearchVO);

	
	int getTotalCount(MemberSearchVO memSearchVO);



 

	
}
