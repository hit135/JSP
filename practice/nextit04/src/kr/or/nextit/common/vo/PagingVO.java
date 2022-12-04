package kr.or.nextit.common.vo;

public class PagingVO {
	
	
	//제공되는값 
	private	int curPage=1;			//현재 페이지가 3번 페이지 이라고 가정하고	           
	private	int rowSizePerPage=10; 	//한 페이지당 10개 게시글 출력 되고 
	private	int pageSize=10;       	//하단에 표시되는 페이지 링크 갯수  10개이고    
	
	
	private	int totalRowCount;  		//디비에 총 게시글 개수가 구해와야지    
	
	//구해야하는값
	private	int firstRow ;  			//해당 페이지에서 가장 위에 있는 게시글 순번 값           
	private	int lastRow;				//해당 페이지에서 가장 밑에 있는 게시글 순번 값
	private	int totalPageCount;  	//총 페이지 링크 수    
	private	int firstPage; 			/* 페이지링크 에서 시작  페이지 번호 ( 21 22 23 24 25 26 27 28 29 30 일때  21 
											   따라서 현재페이지가 21 에서 30 사이에는 모두 21이 나오야 함)*/  	         
	private	int lastPage; 			// 페이지링크 에서 마지막  페이지 번호 ( 21 22 23 24 25 26 27 28 29 30 일때  30 )         
	
	

	
	@Override
	public String toString() {
		return "PagingVO [curPage=" + curPage + ", rowSizePerPage=" + rowSizePerPage + ", pageSize=" + pageSize
				+ ", totalRowCount=" + totalRowCount + ", firstRow=" + firstRow + ", lastRow=" + lastRow
				+ ", totalPageCount=" + totalPageCount + ", firstPage=" + firstPage + ", lastPage=" + lastPage + "]";
	}
	
	// 페이징 셋팅
	public void pageSetting() {
		// 1
		firstRow = totalRowCount - ( curPage -1 ) * rowSizePerPage ;
		// 2
		lastRow = firstRow - (rowSizePerPage -1); 
		if(lastRow < 0) {
			lastRow= 1 ;
		}
		// 3 
		totalPageCount=(totalRowCount-1)/rowSizePerPage +1;
		// 4
		firstPage = (curPage-1)/pageSize*pageSize +1;
		// 5
		lastPage = firstPage + pageSize -1;
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount ; 
		}

	}
	
	
	
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getRowSizePerPage() {
		return rowSizePerPage;
	}
	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	
	
	
	
}
