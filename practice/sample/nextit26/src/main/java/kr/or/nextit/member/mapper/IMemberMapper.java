package kr.or.nextit.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.member.vo.MailAuthVO;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

@Mapper
public interface IMemberMapper {

	MemberVO getMember(String memId);

	int insertMember(MemberVO member);

	int insertUserRole(MemberVO member);

	MemberVO loginCheck(MemberVO member);

	List<UserRoleVO> getUserRole(MemberVO member);

	int deleteMember(MemberVO member);

	int updateMember(MemberVO member);

	int getTotalRowCount(MemberSearchVO searchVO);

	List<MemberVO> getMemberList(MemberSearchVO searchVO);

	List<RoleInfoVO> getRoleInfo();

	void deleteUserRole(String memId);

	void insertMultiRole(@Param("memId") String memId, @Param("role") String role);

	int idCheck(String memId);

	List<String> getuserRoleListByUserId(String username);

	int memberExcelUpload(MemberVO member);

	int memberGridUpdate(MemberVO member);

	int memberGridMultiDelete(String memId);

	MailAuthVO getMailAuth(String mail);

	void insertMailAuth(@Param("mail") String mail, @Param("authKey") String authKey);

	void updateMailAuth(@Param("mail") String mail, @Param("authKey") String authKey);

	void completeAuth(String mail);

	Integer checkMailAuth(String memMail);

}
