package kr.or.nextit.common.vo;

public class UserRoleVO {
	
	private String userId;
	private String userRole;
	private String userRoleNm;
	
	
	@Override
	public String toString() {
		return "UserRoleVO [userId=" + userId + ", userRole=" + userRole + ", userRoleNm=" + userRoleNm + "]";
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserRoleNm() {
		return userRoleNm;
	}
	public void setUserRoleNm(String userRoleNm) {
		this.userRoleNm = userRoleNm;
	}
	
	
	
	

}
