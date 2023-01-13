<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/freeBoardList.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function(){

	$('#id_rowSizePerPage').change(function() {
		sf.find("input[name='curPage']").val(1);
		sf.find("input[name='rowSizePerPage']").val($(this).val());
		sf.submit();
	});

				
	let sf =$("form[name='search']");
	let curPage= sf.find("input[name='curPage']");
	let rowSizePerPage = sf.find("input[name='rowSizePerPage']");
	$('ul.pagination li a').click(function(e) {
		e.preventDefault();

		console.log($(e.target).data("curpage"));  
		
		curPage.val($(e.target).data("curpage")); 
		rowSizePerPage.val($(this).data("rowsizeperpage")); 
		sf.submit();
	});


	sf.find("button[type=submit]").click(function(e) {
		e.preventDefault();
		curPage.val(1);
		rowSizePerPage.val(10);
		sf.submit();
	});

	
	$('#id_btn_reset').click(function() {
		sf.find("select[name='searchType'] option:eq(0)").attr("selected", "selected");	
		sf.find("select[name='searchCategory'] option:eq(0)").prop("selected", "selected");	
		sf.find("input[name='searchWord']").val("");
		sf.find("input[name='curPage']").val(1);
		sf.find("input[name='rowSizePerPage']").val(10);
		sf.submit();
	});  	


	
	
	
});

function fn_boardViewBoNo(boNo){
	//console.log("boNo: "+ boNo);
	
	let st = $("select[name='searchType']").val();
	let sw = $("input[name='searchWord']").val();
	let sc = $("select[name='searchCategory']").val();
	
	let cp = $("input[name='curPage']").val();
	let rpp = $("input[name='rowSizePerPage']").val();
	console.log("st : ",st, ", sw: ", sw, ", sc", sc);
	location.href="${pageContext.request.contextPath}/free/freeView?boNo="+boNo+"&searchType="+st+"&searchWord="+sw+"&searchCategory="+sc+"&curPage="+cp+"&rowSizePerPage="+rpp;
	
	
}
</script>

    <div class="intro_bg">
        <div class="intro_text">
            <h1>NextIT</h1>
            <h4>넥스트아이티</h4>
        </div>
    </div>
    <!-- intro_bg e -->

    <!-- 전체 영역잡기 -->
    <div class="contents">
        <!-- 사용할 영역잡기 -->
        <div class="content01">
            <div class="content01_h1">
                <h1>자유게시판</h1>
            </div>

			<c:if test="${bne ne null or de ne null}">
				<div class="alert alert-warning">
					목록을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
				</div>	
				<div class="div_button">
					<input type="button" onclick="history.back();" value="뒤로가기">
				</div>
			</c:if>      

			<c:if test="${bnf eq null and de eq null}">
		 		<div class="div_search">
					<form name="search" action="${pageContext.request.contextPath }/free/freeList" method="post">
						<input type="hidden" name="curPage" value="${searchVO.curPage}"> 
						<input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage}">
						
						<div>
							<label for="id_searchType">검색</label>
							&nbsp;&nbsp;
							<select id="id_searchType" name="searchType">
								<option value="T" ${searchVO.searchType eq "T" ? "selected='selected'" : ""} >제목</option>
								<option value="W" ${searchVO.searchType eq "W" ? "selected='selected'" : ""} >작성자</option>
								<option value="C" ${searchVO.searchType eq "C" ? "selected='selected'" : ""} >내용</option>
							</select>
							<input type="text" name="searchWord" value="${searchVO.searchWord }" placeholder="검색어">
							&nbsp;&nbsp;&nbsp;&nbsp;	
							
							<label for="id_searchCategory">분류</label>
							&nbsp;&nbsp;
							<select id="id_searchCategory" name="searchCategory">
								<option value="">-- 전체 --</option>
								<c:forEach items="${categoryList}" var="categoryCode">
									<option value="${categoryCode.commCd}" ${searchVO.searchCategory eq categoryCode.commCd ? "selected='selected'" : "" } >${categoryCode.commNm}</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="submit">검 색 </button>
							<button type="button" id="id_btn_reset" >초기화</button>
						</div>
					</form>
				</div>        
            
            
				<div class="rowSizePerPage">
					<div>
						전체 ${searchVO.totalRowCount } 건 조회
						
						<select id="id_rowSizePerPage" name="rowSizePerPage">
							<c:forEach begin="10" end="50" step="10" var="i">
								<option value="${i }" ${searchVO.rowSizePerPage eq i ? "selected='selected'" : "" }>${i }</option>
							</c:forEach>
						</select>
					</div>
				</div>
	            
	            <!-- 리스트 -->
	            <div id="div_table">
	                <table>
	                    <colgroup>
	                        <col width="100">
	                        <col width="150">
	                        <col >
	                        <col width="150">
	                        <col width="150">
	                        <col width="100">
	                    </colgroup>
	                    <thead>
	                        <tr>
	                            <th>글번호</th>
								<th>분류</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록일</th>
								<th>조회수</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	
	       					<c:forEach items="${freeBoardList }" var ="freeBoard">
	       						<tr>
									<td>${freeBoard.rnum }</td>
									<td>${freeBoard.boCategoryNm }</td>
									<td>
										<a href="#" onclick="fn_boardViewBoNo('${freeBoard.boNo }')">
											${freeBoard.boTitle }						
										</a>
									</td>
									<td>${freeBoard.boWriter }</td>
									<td>${freeBoard.boRegDate }</td>
									<td>${freeBoard.boHit }</td>
	       						</tr>
	       					</c:forEach>	                 
	                        
	                    </tbody>
	                </table>
	            </div>
	
	            <!-- paging -->
	            <div class="div_paging">
	                <ul class="pagination">
						<c:if test="${searchVO.firstPage gt 10 }">
		                	<li><a href="#" 
		                		data-curPage=${searchVO.firstPage-1 }   data-rowSizePerPage=${searchVO.rowSizePerPage } >&laquo;</a></li>
		                </c:if> 
		                
						<c:if test="${searchVO.curPage ne 1 }">
							<li><a href="#" 
								data-curPage=${searchVO.curPage-1 }   data-rowSizePerPage=${searchVO.rowSizePerPage } >&lt;</a></li>
						</c:if>
		                
						<c:forEach begin="${searchVO.firstPage }" end="${searchVO.lastPage }" step="1" var="i"> 
							<c:if test="${searchVO.curPage ne i}">
								<li><a href="#"
									data-curPage=${i }   data-rowSizePerPage=${searchVO.rowSizePerPage }  >${i }</a></li>
							</c:if>
							<c:if test="${searchVO.curPage eq i }">
								<li><a href="#" class="curPage_a">${i }</a></li>
							</c:if>
						</c:forEach>
	                
						<c:if test="${searchVO.lastPage ne searchVO.totalPageCount }">
							<li><a href="#"
									data-curPage=${searchVO.curPage+1  }   data-rowSizePerPage=${searchVO.rowSizePerPage }  >&gt;</a></li>
							<li><a href="#"
									data-curPage=${searchVO.lastPage+1  }   data-rowSizePerPage=${searchVO.rowSizePerPage }>&raquo;</a></li>
						</c:if>
						
	
					</ul>
	                
	                <div class="div_board_write">
	                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeForm'" value="글쓰기">
	                </div>
	            </div>
            </c:if>
            
            
        </div>
    </div>