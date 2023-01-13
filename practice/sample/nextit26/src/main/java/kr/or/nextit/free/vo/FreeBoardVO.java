package kr.or.nextit.free.vo;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.common.valid.FreeModify;
import kr.or.nextit.common.valid.FreeRegister;

public class FreeBoardVO {

	private String boNo;                    /* 글번호 */
	
	@NotEmpty(message = "글 제목을 입력해주세요" , groups = {FreeModify.class, FreeRegister.class})
	private String boTitle;                 /* 글제목 */
	
	private String boCategory;              /* 글 카테고리 */
	private String boWriter;                /* 글쓴이 */
	
	@Pattern(regexp = "^\\w{4,10}$", message = "비밀번호는 영문 숫자 조합 4~10 자리입니다"
			, groups = {FreeModify.class, FreeRegister.class})
	private String boPass;                  /* 글 비번 */
	
	@NotEmpty(message = "글 내용을 입력해주세요!", groups = FreeRegister.class)
	private String boContent;               /* 글 내용 */
	
	private String boIp;                    /* 글쓴이 IP */
	private int boHit;                      /* 조회수 */
	private String boRegDate;               /* 글 등록일 */
	private String boModDate;               /* 글 수정일 */
	private String boDelYn;                 /* 글 지워졌나 여부 */
	private String boDelId;                 /* 글 삭제자 */
	private String boDelDate;               /* 글 지운날 */
	
	//boCategoryNm 
	private String boCategoryNm;					 
	
	//rnum 
	private String rnum; 
	
	// 첨부파일 리스트
	private List<AttachVO> attachList;
	
	// 첨부파일 번호 배열
	private int[] delAtchNos;
	
	

	
	@Override
	public String toString() {
		return "FreeBoardVO [boNo=" + boNo + ", boTitle=" + boTitle + ", boCategory=" + boCategory + ", boWriter="
				+ boWriter + ", boPass=" + boPass + ", boContent=" + boContent + ", boIp=" + boIp + ", boHit=" + boHit
				+ ", boRegDate=" + boRegDate + ", boModDate=" + boModDate + ", boDelYn=" + boDelYn + ", boDelId="
				+ boDelId + ", boDelDate=" + boDelDate + ", boCategoryNm=" + boCategoryNm + ", rnum=" + rnum
				+ ", attachList=" + attachList + ", delAtchNos=" + Arrays.toString(delAtchNos) + "]";
	}
	public int[] getDelAtchNos() {
		return delAtchNos;
	}
	public void setDelAtchNos(int[] delAtchNos) {
		this.delAtchNos = delAtchNos;
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
	public String getBoCategoryNm() {
		return boCategoryNm;
	}
	public void setBoCategoryNm(String boCategoryNm) {
		this.boCategoryNm = boCategoryNm;
	}
	
	
	public String getBoNo() {
		return boNo;
	}
	public void setBoNo(String boNo) {
		this.boNo = boNo;
	}
	public String getBoTitle() {
		return boTitle;
	}
	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}
	public String getBoCategory() {
		return boCategory;
	}
	public void setBoCategory(String boCategory) {
		this.boCategory = boCategory;
	}
	public String getBoWriter() {
		return boWriter;
	}
	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}
	public String getBoPass() {
		return boPass;
	}
	public void setBoPass(String boPass) {
		this.boPass = boPass;
	}
	public String getBoContent() {
		return boContent;
	}
	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}
	public String getBoIp() {
		return boIp;
	}
	public void setBoIp(String boIp) {
		this.boIp = boIp;
	}
	public int getBoHit() {
		return boHit;
	}
	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}
	public String getBoRegDate() {
		return boRegDate;
	}
	public void setBoRegDate(String boRegDate) {
		this.boRegDate = boRegDate;
	}
	public String getBoModDate() {
		return boModDate;
	}
	public void setBoModDate(String boModDate) {
		this.boModDate = boModDate;
	}
	public String getBoDelYn() {
		return boDelYn;
	}
	public void setBoDelYn(String boDelYn) {
		this.boDelYn = boDelYn;
	}
	public String getBoDelId() {
		return boDelId;
	}
	public void setBoDelId(String boDelId) {
		this.boDelId = boDelId;
	}
	public String getBoDelDate() {
		return boDelDate;
	}
	public void setBoDelDate(String boDelDate) {
		this.boDelDate = boDelDate;
	}

 
	
	

	 
	
	
	
}
