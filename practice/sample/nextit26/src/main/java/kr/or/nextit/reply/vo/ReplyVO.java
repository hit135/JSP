package kr.or.nextit.reply.vo;

public class ReplyVO {
	private int reNo;                       /* 댓글번호 */
	private String reCategory;              /* 분류(BOARD, PDS, FREE, ...) */
	private String reParentNo;              /* 부모 번호 */
	private String reMemId;                 /* 작성자ID */
	private String reContent;               /* 댓글 내용 */
	private String reIp;                    /* IP */
	private String reRegDate;               /* 댓글 등록일자 */
	
	
	@Override
	public String toString() {
		return "ReplyVO [reNo=" + reNo + ", reCategory=" + reCategory + ", reParentNo=" + reParentNo + ", reMemId="
				+ reMemId + ", reContent=" + reContent + ", reIp=" + reIp + ", reRegDate=" + reRegDate + "]";
	}
	
	public int getReNo() {
		return reNo;
	}
	public void setReNo(int reNo) {
		this.reNo = reNo;
	}
	public String getReCategory() {
		return reCategory;
	}
	public void setReCategory(String reCategory) {
		this.reCategory = reCategory;
	}
	public String getReParentNo() {
		return reParentNo;
	}
	public void setReParentNo(String reParentNo) {
		this.reParentNo = reParentNo;
	}
	public String getReMemId() {
		return reMemId;
	}
	public void setReMemId(String reMemId) {
		this.reMemId = reMemId;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReIp() {
		return reIp;
	}
	public void setReIp(String reIp) {
		this.reIp = reIp;
	}
	public String getReRegDate() {
		return reRegDate;
	}
	public void setReRegDate(String reRegDate) {
		this.reRegDate = reRegDate;
	}
	
	
	

}
