package kr.or.nextit.member.vo;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.common.valid.MemberModify;
import kr.or.nextit.common.valid.MemberRegister;
import kr.or.nextit.common.vo.UserRoleVO;

public class MemberVO {
	
	// @NotEmpty(message = "회원아이디는 필수 입니다.")
	// @Size(min = 4, max = 10, message = "아이디는 영문 숫자 조합 4~10로 입력해주세요")
	@Pattern(regexp = "^\\w{4,10}$", message = "아이디는 영문 숫자 조합 4~110로 입력해주세요"
			, groups = MemberRegister.class)
	private String memId;                   /* 회원아이디 */
	
	@Pattern(regexp = "^\\w{4,10}$", message = "패스워드는 영문 숫자 조합 4~10로 입력해주세요"
			, groups = {MemberRegister.class, MemberModify.class})
	private String memPass;                 /* 회원비밀번호 */
	
	@Size(min = 1, max = 40, message = "이름은 40자 이내로 입력해주세요"
			, groups = {MemberRegister.class, MemberModify.class})
	private String memName;                 /* 회원이름 */
	
	@NotEmpty(message = "생년월일을 입력해주세요")
	private String memBir;                  /* 회원생년월일 */
	
	// @Positive 숫자만 받음
	@Positive(message = "숫자로 입력해주세요")
	@Size(min = 5, max = 5, message = "우편번호는 5자리입니다")
	private String memZip;                  /* 회원우편번호 */
	private String memAdd1;                 /* 회원주소 */
	private String memAdd2;                 /* 회원상세주소 */
	
	@Pattern(regexp = "^[0-9]{10,11}$", message = "휴대폰번호는 숫자를 사용하여 10~11 자리 입력 주세요.")
	private String memHp;                   /* 회원전화번호 */
	
	// @Email 형식을 지원함
	@NotEmpty(message = "이메일을 입력해주세요")
	@Email(message = "이메일 형식에 맞춰주세요")
	private String memMail;                 /* 회원이메일 */
	
	@NotEmpty(message = "직업을 선택해주세요")
	private String memJob;                  /* 회원직업 */
	
	@NotEmpty(message = "취미를 선택해주세요")
	private String memHobby;                /* 회원취미 */
	private int memMileage;                 /* 회원마일리지 */
	private String memDelYn;                /* 회원삭제여부 */
	private String memJoinDate;             /* 회원가입일 */
	private String memEditDate;             /* 회원정보수정일 */
	
	@Pattern(regexp = "^\\w{4,10}$", message = "패스워드는 영문 숫자 조합 4~110로 입력해주세요"
			, groups = MemberModify.class )
	private String memPassNew;
	private String rememberMe;
	private List<UserRoleVO> userRoleList;
	
	private String rnum;
	
	// 첨부파일 리스트
	private List<AttachVO> attachList;
	
	// 프로필 이미지 키값 번호
	private Integer atchNo;
	
	// 스프링 시큐리티 권한을 위한 선언 >> 권한만 가져가는 변수
	private List<String> roleList;

	
	
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memBir=" + memBir
				+ ", memZip=" + memZip + ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memHp=" + memHp
				+ ", memMail=" + memMail + ", memJob=" + memJob + ", memHobby=" + memHobby + ", memMileage="
				+ memMileage + ", memDelYn=" + memDelYn + ", memJoinDate=" + memJoinDate + ", memEditDate="
				+ memEditDate + ", memPassNew=" + memPassNew + ", rememberMe=" + rememberMe + ", userRoleList="
				+ userRoleList + ", rnum=" + rnum + ", attachList=" + attachList + ", atchNo=" + atchNo + ", roleList="
				+ roleList + "]";
	}

	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public Integer getAtchNo() {
		return atchNo;
	}
	public void setAtchNo(Integer atchNo) {
		this.atchNo = atchNo;
	}
	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
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

 
	
	
	
	
}
