package kr.or.nextit.common.vo;

public class RoleInfoVO {
	
	private String roleCode;
	private String roleEng;
	private String roleKor;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleEng() {
		return roleEng;
	}
	public void setRoleEng(String roleEng) {
		this.roleEng = roleEng;
	}
	public String getRoleKor() {
		return roleKor;
	}
	public void setRoleKor(String roleKor) {
		this.roleKor = roleKor;
	}
	@Override
	public String toString() {
		return "RoleInfoVO [roleCode=" + roleCode + ", roleEng=" + roleEng + ", roleKor=" + roleKor + "]";
	}

	
	
	
}
