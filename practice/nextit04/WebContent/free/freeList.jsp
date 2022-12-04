<%@page import="kr.or.nextit.code.vo.CodeVO"%>
<%@page import="kr.or.nextit.code.service.CommCodeServiceImpl"%>
<%@page import="kr.or.nextit.code.service.ICommCodeService"%>
<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="kr.or.nextit.free.vo.FreeBoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.nextit.free.service.FreeBoardServiceImpl"%>
<%@page import="kr.or.nextit.free.service.IFreeBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/freeBoardList.css">
<link rel="stylesheet" type="text/css" href="../css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

// 출력 사이즈 변경!
$(function(){
	// alert("test");
	 $("#id_rowSizePerPage").change(function() {
		console.log("id_rowSizePerPage : "+ this.value);
		// 기존의 방식
		// location.href = "${pageContext.request.contextPath}/free/freeList.jsp?curPage=1&rowSizePerPage="+this.value;
		// 다시 1페이지로 가서 사이즈 변경
		sf.find("input[name='curPage']").val(1);
		// this 랑 e.target을 잘 알도록 하라!!!!!!!!!
		// e매개변수를 안 받았기에 this만 가능
		sf.find("input[name='rowSizePerPage']").val( $(this).val() );
		sf.submit();
	});
	 
	 
/* 	 페이지1 함수처리
	 let sf = $("form[name='search']");
	 
	 // 히든태그의 curPage 받기
	 let curPage = sf.find("input[name='curPage']")
	 sf.find("button[type='submit']").click(function(e){
		 // 이벤트를 막는 함수 preventDefault
		 e.preventDefault();
		 curPage.val(1);
		 sf.submit();
		 
	 }); */
	
	 // 전역변수로 form취득
	 let sf = $("form[name='search']");
	 let curPage = sf.find("input[name='curPage']")
	 let rowSizePerPage = sf.find("input[name='rowSizePerPage']")
	 
	 
	 // data에 넣어둔 데이터를 취득하기
	 // 제이쿼리 셀렉 붙여쓰면 and 떨어지면 or이었나..?
	 // a태그에서 #처리는 상단으로 올림
	 $('ul.pagination li a').click(function(e) {
		 // alert("a click");
		 e.preventDefault();
		 // 데이터 취득
		 // this == e.target >> 거의 똑같다!
		 // 사실 html은 대소문자를 가리지 않는다..
		 console.log( "this : " + $(this).data("curpage") );
		 console.log( "event : " + $(e.target).data("curpage") );
		 
		 // 값을 취득했구나 이제 전송하면 된다!
	 	curPage.val( $(e.target).data("curpage") );
	 	rowSizePerPage.val( $(this).data("rowSizePerPage") );
	 	sf.submit()
	});
	
	// 다른 조건을 하고 검색버튼을 눌렀을 때 전에 설정해둔 출력개수, 출력 페이지 등 초기화
	// 다시 확인해보기
	sf.find("button[type='submit']").click(function(e) {
		// 전송 막기
		e.preventDefault();
		curPage.val(1);
		rowSizePerPage.val(10);
		sf.submit()
	});
	
	// 초기화 버튼을 누르면 초기화 구현
	$('#id_btn_reset').click(function() {
		sf.find("select[name='searchType'] option:eq(0)").attr("selected", "selected");
		sf.find("select[name='searchCategory'] option:eq(0)").attr("selected", "selected");
		sf.find("input[name='searchWord']").val("");
		sf.find("input[name='curPage']").val(1);
		sf.find("input[name='rowSizePerPage']").val(10);
		sf.submit();
	});
	
});
function fn_boardViewBoNo(boNo) {
	// alert("boNo : " + boNo )
	
	let st = $("select[name='searchType']").val();
	let sw = $("input[name='searchWord']").val();
	let sc = $("select[name='searchCategory']").val();
	
	let cp = $("input[name='curPage']").val()
	let rpp = $("input[name='rowSizePerPage']").val();
	
	location.href = "${pageContext.request.contextPath}/free/freeView.jsp?boNo="
			+boNo+"&searchType="+st+"&searchWord="+sw+"&searchCategory="+sc+"&curPage="+cp+"&rowSizePerPage="+rpp
	;
}
</script>


</head>
<jsp:useBean id="searchVO" class="kr.or.nextit.free.vo.FreeBoardSearchVO"></jsp:useBean>
<jsp:setProperty property="*" name="searchVO" />


<!-- 검색에 분류폼 가져오기 -->
<%
	ICommCodeService codeService = new CommCodeServiceImpl();
	List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
	request.setAttribute("categoryList", categoryList);
%>

<!-- 리스트 불러오기 -->
<%
	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	try{
		List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
		request.setAttribute("freeBoardList", freeBoardList);
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
	}catch(DaoException de){
		request.setAttribute("de", de);
	}

%>


<body>
	<div id="wrap">
		<div class="header">
			<div class="top_nav">
				<!-- header 영역 -->
				<%@ include file="/header/header.jsp"%>

			</div>
		</div>
		<!-- header e -->

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

				<c:if test="${bne ne null or de ne null }">
					<div>처리중 에러 발생하였습니다. 전산실에 문의 부탁드립니다. 042 - 719 -8850</div>
					<div class="div_button">
						<input type="button" onclick="history.back();" value="뒤로가기">
					</div>
				</c:if>

				<c:if test="${bne eq null and de eq null }">
				
					<!-- 페이징 검색 구현! -->
					<!-- 무조건 curPage=1로 한다 -->
					<!-- 함수 처리, 히든 속성 부여 -->
					<!-- 이번에는 함수처리 -->
					<div class="div_search">
						<form name="search"
							action="${pageContext.request.contextPath}/free/freeList.jsp"
							method="post">
							<!-- 히든으로 curPage 보내기.. -->
							<!-- 밑에 data에 넣고 스크립트에서 받은것들 -->
							<input type="hidden" name="curPage" value="${searchVO.curPage }">
							<input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage }">
							<div>
								<label for="id_searchType">검색</label> &nbsp;&nbsp; <select
									id="id_searchType" name="searchType">
									
									<!-- 검색 조건 유지!!!!!!! > selected -->
									<option value="T" ${searchVO.searchType eq "T" ? "selected='selected'" : "" }>제목</option>
									<option value="W" ${searchVO.searchType eq "W" ? "selected='selected'" : "" }>작성자</option>
									<option value="C" ${searchVO.searchType eq "C" ? "selected='selected'" : "" }>내용</option>
									
									<!-- 검색어 유지!! -->
								</select> <input type="text" name="searchWord" value="${searchVO.searchWord}" placeholder="검색어">
								&nbsp;&nbsp;&nbsp;&nbsp; <label for="id_searchCategory">분류</label>
								&nbsp;&nbsp; <select id="id_searchCategory"
									name="searchCategory">
									<option value="">-- 전체 --</option>
									<c:forEach items="${categoryList}" var="categoryCode">
									
									
									<!-- 서치 카테고리 유지!!! -->
										<option value="${categoryCode.commCd}"
										${searchVO.searchCategory eq categoryCode.commCd ? "selected='selected'" : "" }>
										${categoryCode.commNm}</option>
										
										
										
									</c:forEach>
								</select> &nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit">검 색</button>
								<button type="button" id="id_btn_reset">초기화</button>
							</div>
						</form>
					</div>



					<!-- 페이징 목록 건수 추가 -->
					<div class="rowSizePerPage">
						<div>
							전체 ${searchVO.totalRowCount}건 조회 <select id="id_rowSizePerPage"
								name="rowSizePerPage">
								<c:forEach begin="10" end="50" step="10" var="i">
									<!-- 여기 좀더 공부하기!!! -->
									<option value="${i }"
										${searchVO.rowSizePerPage eq i ? "selected = 'selected'" : "" }>${i }</option>
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
								<col>
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


								<c:forEach items="${freeBoardList }" var="freeBoard">
									<tr>
										<td><c:out value="${freeBoard.rnum }"></c:out></td>
										<td><c:out value="${freeBoard.boCategoryNm }"></c:out></td>
										<td>
											<%-- href="${pageContext.request.contextPath }/free/freeView.jsp?boNo=${freeBoard.boNo}"> --%>
											<a href='#' onclick="fn_boardViewBoNo('${freeBoard.boNo}')" >
												<c:out value="${freeBoard.boTitle }"></c:out>
										</a></td>
										<td><c:out value="${freeBoard.boWriter }"></c:out></td>
										<td><c:out value="${freeBoard.boRegDate }"></c:out></td>
										<td><c:out value="${freeBoard.boHit }"></c:out></td>
									</tr>

								</c:forEach>







							</tbody>
						</table>
					</div>

					<!-- paging -->
					<div class="div_paging">
						<ul class="pagination">



							<%--  꺽새! curPage=${pagingVO.firstPage -1 이걸 이해하자!  --%>
							
							<!-- 검색해서 페이징 이동 할 때 조건을 같이 가져가기 위해서 -->
							<!-- 겟방식으로 안쓸것이다!! -->

<%-- 							<c:if test="${searchVO.firstPage gt 10 }">
								<li><a
									href="${pageContext.request.contextPath}/free/freeList.jsp?curPage=${searchVO.firstPage -1}&rowSizePerPage=${searchVO.rowSizePerPage}">&laquo;</a></li>
							</c:if>

							<c:if test="${searchVO.curPage != 1 }">
								<li><a
									href="${pageContext.request.contextPath}/free/freeList.jsp?curPage=${searchVO.curPage -1}&rowSizePerPage=${searchVO.rowSizePerPage}">&lt;</a></li>
							</c:if>

							<c:forEach begin="${searchVO.firstPage }"
								end="${searchVO.lastPage }" step="1" var="i">
								<!-- 자기 자신으 ㅣ페이지는 눌리지 않게 -->
								<c:if test="${searchVO.curPage ne i }">
									<li><a
										href="${pageContext.request.contextPath}/free/freeList.jsp?curPage=${i}">${i}</a></li>
								</c:if>
								<c:if test="${searchVO.curPage eq i }">
									<li><a href="#" class="curPage_a">${i }</a></li>
								</c:if>


							</c:forEach>

							<c:if test="${searchVO.lastPage ne searchVO.totalPageCount}">
								<li><a
									href="${pageContext.request.contextPath}/free/freeList.jsp?curPage=${searchVO.curPage + 1}&rowSizePerPage=${searchVO.rowSizePerPage}">&gt;</a></li>
								<li><a
									href="${pageContext.request.contextPath}/free/freeList.jsp?curPage=${searchVO.lastPage + 1}&rowSizePerPage=${searchVO.rowSizePerPage}">&raquo;</a></li>
							</c:if> --%>
							
							<!-- 새로운!! -->
							<!-- a태그를 클릭하면 data에 넣어둔 값들을 취득할 것이다  -->
							<c:if test="${searchVO.firstPage gt 10 }">
								<li><a
									href="#" 
									data-curPage=${searchVO.firstPage -1} data-rowSizePerPage=${searchVO.rowSizePerPage } >&laquo;</a></li>
							</c:if>

							<c:if test="${searchVO.curPage != 1 }">
								<li><a
									href="#"
									data-curPage=${searchVO.curPage -1} data-rowSizePerPage=${searchVO.rowSizePerPage }  >&lt;</a></li>
							</c:if>

							<c:forEach begin="${searchVO.firstPage }"
								end="${searchVO.lastPage }" step="1" var="i">
								<!-- 자기 자신의 페이지는 눌리지 않게 -->
								<c:if test="${searchVO.curPage ne i }">
									<li><a
										href="#"
										data-curPage=${ i } data-rowSizePerPage=${searchVO.rowSizePerPage } >${i}</a></li>
								</c:if>
								<c:if test="${searchVO.curPage eq i }">
									<li><a href="#" class="curPage_a">${i }</a></li>
								</c:if>


							</c:forEach>

							<c:if test="${searchVO.lastPage ne searchVO.totalPageCount}">
								<li><a
									href="#"
									data-curPage=${searchVO.curPage + 1} data-rowSizePerPage=${searchVO.rowSizePerPage }>&gt;</a></li>
								<li><a
									href="#"
									data-curPage=${searchVO.lastPage + 1} data-rowSizePerPage=${searchVO.rowSizePerPage } >&raquo;</a></li>
							</c:if>




						</ul>

						<div class="div_board_write">
							<input type="button"
								onclick="location.href='${pageContext.request.contextPath}/free/freeForm.jsp'"
								value="글쓰기">
						</div>
					</div>


				</c:if>

			</div>
		</div>

		<!-- footer -->
		<footer id="page_footer">
			<!-- footer영역 -->
			<%@ include file="/footer/footer.jsp"%>
		</footer>

	</div>


</body>
</html>