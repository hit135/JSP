package kr.or.youth.login.vo;

import java.util.List;

public class MemberVO {
	
	private String memId;					/* 회원 아이디 */
	private String memPass;					/* 회원 비번 */
	private String memName;					/* 회원 닉네임 */
	private int    memPoint = 0;			/* 회원 포인트 */
	private String memRole = "ROLE_USER";			/* 회원 역할*/
	private String memJoinDate;				/* 회원 가입 날짜 */
	private String memAttendance;			/* 회원 출석 날짜 */
	private int rank;						/* 회원 순위*/
	private List<String> userRoleList;

	

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memPoint=" + memPoint
				+ ", memRole=" + memRole + ", memJoinDate=" + memJoinDate + ", memAttendance=" + memAttendance
				+ ", rank=" + rank + ", userRoleList=" + userRoleList + "]";
	}
	public List<String> getUserRoleList() {
		return userRoleList;
	}
	public void setUserRoleList(List<String> userRoleList) {
		this.userRoleList = userRoleList;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getMemAttendance() {
		return memAttendance;
	}
	public void setMemAttendance(String memAttendance) {
		this.memAttendance = memAttendance;
	}
	public String getMemRole() {
		return memRole;
	}

	public void setMemRole(String memRole) {
		this.memRole = memRole;
	}
	public String getMemJoinDate() {
		return memJoinDate;
	}
	public void setMemJoinDate(String memJoinDate) {
		this.memJoinDate = memJoinDate;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	
	

}
