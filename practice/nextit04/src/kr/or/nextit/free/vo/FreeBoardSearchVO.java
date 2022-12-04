package kr.or.nextit.free.vo;

import kr.or.nextit.common.vo.PagingVO;

public class FreeBoardSearchVO extends PagingVO {
	
	private String searchType;
	
	private String searchWord;
	
	private String searchCategory;

	
	
	
	
	@Override
	public String toString() {
		return "FreeBoardSearchVO [searchType=" + searchType + ", searchWord=" + searchWord + ", searchCategory="
				+ searchCategory + "]"
				+ super.toString() ;
	}

	
	
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	
	
	
	
	
	
}
