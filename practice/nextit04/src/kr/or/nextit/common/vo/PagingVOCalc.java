package kr.or.nextit.common.vo;

public class PagingVOCalc {

	
	
	public static void main(String[] args) {
		
		//제공되는값 
		int curPage=3;	//현재 페이지가 3번 페이지 이라고 가정하고	           
		int rowSizePerPage=10; //한 페이지당 10개 게시글 출력 되고 
		int pageSize=10;       //하단에 표시되는 페이지 링크 갯수  10개이고    
		int totalRowCount = 83;  //디비에 총 게시글 개수가 83개로 가정하고     
		
		//구해야하는값
		int firstRow ;  //해당 페이지에서 가장 위에 있는 게시글 순번 값           
		int lastRow;	//해당 페이지에서 가장 밑에 있는 게시글 순번 값
		int totalPageCount;  //총 페이지 링크 수    
		int firstPage; /* 페이지링크 에서 시작  페이지 번호 ( 21 22 23 24 25 26 27 28 29 30 일때  21 
							이며 사용자가 23 페이지를 보고 있는 상태라면 시작페이지는 21 이고 마지막페이지는 30 이다
							따라서 현재페이지가 21 에서 30 사이에는 모두 21이 나오야 함)*/  	         
		int lastPage; // 페이지링크 에서 마지막  페이지 번호 ( 21 22 23 24 25 26 27 28 29 30 일때  30 )           
		      
		//firstRow, lastRow, totalPageCount, firstPage, lastPage 필드 값을 구할 수 있는 계산식을 완성하세요 
		
				
		/*firstRow
		 	curPage = 3
		 	rowSizePerPage = 10
		 	pageSize  = 10
		 	totalRowCount  =  93
		 	
			1페이지 83 ~
			2페이지 73	 ~
			3페이지 63 ~
			
			63 = 83 - ( 3 - 1 ) * 10  // curPage가 3인데 앞에 두개 페이지가 있으므로 3빼기 1을 해야하고 한 페이지당 게시글 수 가  10개 이므로  10을 곱하면 63 나옴  
		 */
		//firstRow = ;
		firstRow = totalRowCount - ( curPage -1 ) * rowSizePerPage ;
		
		
		/*lastRow
		 	curPage = 3
		 	rowSizePerPage = 10
		 	pageSize  = 10
		 	totalRowCount  =  83
	 		fristRow = 63
	 	
			1페이지 83 ~
			2페이지 73	 ~
			3페이지 63 ~ 54
			
			54 = 63 - ( 한 페이지당 게시글 수   - 1 ) 
		*/
		//lastRow = ;
		lastRow = firstRow - (rowSizePerPage -1); 
					
		
		/*총개시글이 10 이하인 경우 lastRow가 음수로 나오므로  1으로 세팅하기
		 	디비에 총 7개에 글이 있음
		 	위계산식대로 하면  lastRow = 7 - ( 10-1 ) = -2 
		 	따라서 음수가 나오면 한페이지 1~7까지 이므로 1으로 세팅하면됨 */
		if(lastRow < 0) {
			lastRow= 1 ;
		}

		
		/*totalPageCount
		 	경우1 
		 		게시글이 83 개이면  총 페이지 개수는 9 이어야 한다.
					-> 83/10 은 8인데 나머지 3이 있어서 한 개 페이지가 더 필요 하므로 9이어야 한다.
		 			-> 83/10 +1 
		 	경우2
		 		게시글 개수가 30이면  10으로 나누면 나머지가 없으므로 총 페이지는 3이어야 한다.
		 		    하지만 위 계산식대로 하면 4가 나온다.
		 		    따라서 총 개시글이 30 이면 3이 나와야 하고 총개시글이 31 이면 4가 나와야 한다.
	 				 -> (30-1)/10 +1 = 3 (29를 10으로 나누면 int값은 2 이므로 )	
	 				 -> (31-1)/10 +1 = 4								*/
		totalPageCount=(totalRowCount-1)/rowSizePerPage +1;
				

		/*firstPage
		 	사용자가 23 페이지를 보고 있는 상태라면 시작페이지는 21 이고 마지막페이지는 30 이다
			따라서 현재페이지가 21 에서 30 사이에 는 모두 21이 나오야 함
			
			경우1 23페이지 일때   
				23 을 10으로 나누어서  소숫점 밑으로 버리고( 2.3 -> 2 ) 다시 10을 곱하면 20이 되며 첫페이지가 21부터 이므로  +1 해주면 됨   
				 	-> 23/10 * 10  +1  = 21
	 		
	 		경우2 30페이지 일때
	 			하지만 현제 페이지가 30 페이지인 경우 31이 나오므로 curPage에서 -1 해야 함
	 				-> (30-1)/10 * 10  +1  = 30							*/
		firstPage = (curPage-1)/pageSize*pageSize +1;

		
		/*lastPage 
		  	21이 시작 페이지 이면 30은 마지막 페이지므로 마지막페이지는 시작페이지 + 하단에 보여줄 페이지 링크 갯수 -1   해주면 된다.   */
		lastPage = firstPage + pageSize -1;
		
		
		// 만약에 lastPage가 totalPageCount 보다 크면 안되므로 lastPage를 totalPageCount으로  변경하기   
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount ; 
		}
		
		
		System.out.println("firstRow: "+ firstRow);
		System.out.println("lastRow: "+ lastRow);
		System.out.println("totalPageCount: "+ totalPageCount);
		System.out.println("firstPage: "+ firstPage);
		System.out.println("lastPage: "+ lastPage);
	}
	
}