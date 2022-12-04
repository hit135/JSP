package kr.or.nextit.member.vo;

import java.util.List;

import kr.or.nextit.common.vo.UserRoleVO;

public class MemberVO {
	
	private String memId;                   /* 회원아이디 */
	private String memPass;                 /* 회원비밀번호 */
	private String memName;                 /* 회원이름 */
	private String memBir;                  /* 회원생년월일 */
	private String memZip;                  /* 회원우편번호 */
	private String memAdd1;                 /* 회원주소 */
	private String memAdd2;                 /* 회원상세주소 */
	private String memHp;                   /* 회원전화번호 */
	private String memMail;                 /* 회원이메일 */
	private String memJob;                  /* 회원직업 */
	private String memHobby;                /* 회원취미 */
	private int memMileage;                 /* 회원마일리지 */
	private String memDelYn;                /* 회원삭제여부 */
	private String memJoinDate;             /* 회원가입일 */
	private String memEditDate;             /* 회원정보수정일 */
	
	private String memPassNew;
	
	private String rememberMe;
	
	private List<UserRoleVO> userRoleList;
	
	private String rnum;
	
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public List<UserRoleVO> getUserRoleList() {
		return userRoleList;
	}
	public void setUserRoleList(List<UserRoleVO> userRoleList) {
		this.userRoleList = userRoleList;
	}
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getMemPassNew() {
		return memPassNew;
	}
	public void setMemPassNew(String memPassNew) {
		this.memPassNew = memPassNew;
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
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemZip() {
		return memZip;
	}
	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemJob() {
		return memJob;
	}
	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}
	public String getMemHobby() {
		return memHobby;
	}
	public void setMemHobby(String memHobby) {
		this.memHobby = memHobby;
	}
	public int getMemMileage() {
		return memMileage;
	}
	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}
	public String getMemDelYn() {
		return memDelYn;
	}
	public void setMemDelYn(String memDelYn) {
		this.memDelYn = memDelYn;
	}
	public String getMemJoinDate() {
		return memJoinDate;
	}
	public void setMemJoinDate(String memJoinDate) {
		this.memJoinDate = memJoinDate;
	}
	public String getMemEditDate() {
		return memEditDate;
	}
	public void setMemEditDate(String memEditDate) {
		this.memEditDate = memEditDate;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memBir=" + memBir
				+ ", memZip=" + memZip + ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memHp=" + memHp
				+ ", memMail=" + memMail + ", memJob=" + memJob + ", memHobby=" + memHobby + ", memMileage="
				+ memMileage + ", memDelYn=" + memDelYn + ", memJoinDate=" + memJoinDate + ", memEditDate="
				+ memEditDate + "]";
	}
	
	
	
}
