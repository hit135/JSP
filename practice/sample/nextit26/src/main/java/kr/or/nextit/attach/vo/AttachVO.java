package kr.or.nextit.attach.vo;

public class AttachVO {
	
	private int atchNo;                	 /* 첨부파일 번호(PK) */
	private String atchParentNo;            /* 부모글의 PK  */
	private String atchCategory;            /* 상위글 분류(BOARD, FREE, QNA, PDS 등) */
	private String atchFileName;            /* 실제 저장된 파일명 */
	private String atchOriginalName;        /* 사용자가 올린 원래 파일명 */
	private long atchFileSize;              /* 파일 사이즈 */
	private String atchConvertSize;         /* 파일 크기 단위 적용 */
	private String atchContentType;         /* 컨텐츠 타입 */
	private String atchPath;                /* 저장 경로(board)  */
	private int atchDownHit;                /* 다운로드 횟수 */
	private String atchDelYn;               /* 삭제여부(스케쥴에 의해서 파일삭제처리) */
	private String atchRegId;               /* 등록자 */
	private String atchRegDate;             /* 등록일 */
	
	
	@Override
	public String toString() {
		return "AttachVO [atchNo=" + atchNo + ", atchParentNo=" + atchParentNo + ", atchCategory=" + atchCategory
				+ ", atchFileName=" + atchFileName + ", atchOriginalName=" + atchOriginalName + ", atchFileSize="
				+ atchFileSize + ", atchConvertSize=" + atchConvertSize + ", atchContentType=" + atchContentType
				+ ", atchPath=" + atchPath + ", atchDownHit=" + atchDownHit + ", atchDelYn=" + atchDelYn
				+ ", atchRegId=" + atchRegId + ", atchRegDate=" + atchRegDate + "]";
	}
	public int getAtchNo() {
		return atchNo;
	}
	public void setAtchNo(int atchNo) {
		this.atchNo = atchNo;
	}
	public String getAtchParentNo() {
		return atchParentNo;
	}
	public void setAtchParentNo(String atchParentNo) {
		this.atchParentNo = atchParentNo;
	}
	public String getAtchCategory() {
		return atchCategory;
	}
	public void setAtchCategory(String atchCategory) {
		this.atchCategory = atchCategory;
	}
	public String getAtchFileName() {
		return atchFileName;
	}
	public void setAtchFileName(String atchFileName) {
		this.atchFileName = atchFileName;
	}
	public String getAtchOriginalName() {
		return atchOriginalName;
	}
	public void setAtchOriginalName(String atchOriginalName) {
		this.atchOriginalName = atchOriginalName;
	}
	public long getAtchFileSize() {
		return atchFileSize;
	}
	public void setAtchFileSize(long atchFileSize) {
		this.atchFileSize = atchFileSize;
	}
	public String getAtchConvertSize() {
		return atchConvertSize;
	}
	public void setAtchConvertSize(String atchConvertSize) {
		this.atchConvertSize = atchConvertSize;
	}
	public String getAtchContentType() {
		return atchContentType;
	}
	public void setAtchContentType(String atchContentType) {
		this.atchContentType = atchContentType;
	}
	public String getAtchPath() {
		return atchPath;
	}
	public void setAtchPath(String atchPath) {
		this.atchPath = atchPath;
	}
	public int getAtchDownHit() {
		return atchDownHit;
	}
	public void setAtchDownHit(int atchDownHit) {
		this.atchDownHit = atchDownHit;
	}
	public String getAtchDelYn() {
		return atchDelYn;
	}
	public void setAtchDelYn(String atchDelYn) {
		this.atchDelYn = atchDelYn;
	}
	public String getAtchRegId() {
		return atchRegId;
	}
	public void setAtchRegId(String atchRegId) {
		this.atchRegId = atchRegId;
	}
	public String getAtchRegDate() {
		return atchRegDate;
	}
	public void setAtchRegDate(String atchRegDate) {
		this.atchRegDate = atchRegDate;
	}
	
	

}
