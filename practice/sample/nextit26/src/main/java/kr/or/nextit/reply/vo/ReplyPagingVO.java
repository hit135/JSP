package kr.or.nextit.reply.vo;

import java.util.List;

import kr.or.nextit.common.vo.PagingVO;

public class ReplyPagingVO extends PagingVO{
	
	private String reCategory;              /* 분류(BOARD, PDS, FREE, ...) */
	private String reParentNo;              /* 부모 번호 */
	private List<ReplyVO> replyList;
	
	
	@Override
	public String toString() {
		return "ReplyPagingVO [reCategory=" + reCategory + ", reParentNo=" + reParentNo + ", replyList=" + replyList
				+ ", getCurPage()=" + getCurPage() + ", getRowSizePerPage()=" + getRowSizePerPage() + ", getPageSize()="
				+ getPageSize() + ", getTotalRowCount()=" + getTotalRowCount() + ", getFirstRow()=" + getFirstRow()
				+ ", getLastRow()=" + getLastRow() + ", getTotalPageCount()=" + getTotalPageCount()
				+ ", getFirstPage()=" + getFirstPage() + ", getLastPage()=" + getLastPage() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
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
	public List<ReplyVO> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyVO> replyList) {
		this.replyList = replyList;
	}			
	
	

}
