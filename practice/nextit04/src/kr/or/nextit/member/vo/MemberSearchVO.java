package kr.or.nextit.member.vo;

import kr.or.nextit.common.vo.PagingVO;

public class MemberSearchVO extends PagingVO{
	
	private String searchType;
	private String searchJob;
	private String searchHobby;
	private String searchWord;
	
	
	
	@Override
	public String toString() {
		return "MemberSearchVO [searchType=" + searchType + ", searchJob=" + searchJob+ ", searchWord=" + searchWord + ", searchHobby=" + searchHobby
				+ ", toString()=" + super.toString() + ", getCurPage()=" + getCurPage() + ", getRowSizePerPage()="
				+ getRowSizePerPage() + ", getPageSize()=" + getPageSize() + ", getTotalRowCount()="
				+ getTotalRowCount() + ", getFirstRow()=" + getFirstRow() + ", getLastRow()=" + getLastRow()
				+ ", getTotalPageCount()=" + getTotalPageCount() + ", getFirstPage()=" + getFirstPage()
				+ ", getLastPage()=" + getLastPage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}



	public String getSearchWord() {
		return searchWord;
	}



	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}



	public String getSearchType() {
		return searchType;
	}



	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}



	public String getSearchJob() {
		return searchJob;
	}



	public void setSearchJob(String searchJob) {
		this.searchJob = searchJob;
	}



	public String getSearchHobby() {
		return searchHobby;
	}



	public void setSearchHobby(String searchHobby) {
		this.searchHobby = searchHobby;
	}
	
	
	
	
	
	

}
