<%@page import="kr.or.nextit.code.vo.CodeVO"%>
<%@page import="kr.or.nextit.code.service.CommCodeServiceImpl"%>
<%@page import="kr.or.nextit.code.service.ICommCodeService"%>
<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.nextit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/memberList.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- 화면딴에서 동작 -->
<script type="text/javascript">


$(document).ready(function(){
	
	// 전역변수로 검색 폼태그 선언
	let searchForm = $("form[name='search']");
	let curPage = searchForm.find("input[name='curPage']");
	let rowSizePerPage = searchForm.find("input[name='rowSizePerPage']");
	
	// 셀렉박스
	$('#id_rowSizePerPage').change(function() {
		console.log("id_rowSizePerPage : " + this.value);
		// 1페이지로 다시 돌아간 후 출력 사이즈 변경
		// location.href = "${pageContext.request.contextPath}/member/memberList.jsp?curPage=1&rowSizePerPage="+this.value;
		
		// data로 다시 서브밋
		searchForm.find("input[name='curPage']").val(1);
		searchForm.find("input[name='rowSizePerPage']").val( $(this).val() );
		searchForm.submit();
	});

	// 검색 버튼 클릭
	$("button[type='submit']").click(function(e) {
		// e.preventDefault();
		console.log("submit");
		curPage.val(1);
		rowSizePerPage.val(10);
		searchForm.submit();
	});
	
	// 초기화 버튼 클릭
	$("#id_btn_reset").click(function() {
		console.log("초기화 버튼");
		searchForm.find("select[name='searchType'] option:eq(0)").attr("selected", "selected");
		searchForm.find("select[name='searchJob'] option:eq(0)").attr("selected", "selected");
		searchForm.find("select[name='searchHobby'] option:eq(0)").attr("selected", "selected");
		searchForm.find("input[name='searchWord'").val("");
		curPage.val(1);
		rowSizePerPage.val(10);
		searchForm.submit();
	});
	
	 $('ul.pagination li a').click(function(e) {
		 // alert("a click");
		 e.preventDefault();
		 // 데이터 취득
		 // this == e.target >> 거의 똑같다!
		 // 사실 html은 대소문자를 가리지 않는다..
		 console.log( "this : " + $(this).data("curpage") );
		 console.log( "event : " + $(e.target).data("curpage") );
		 console.log( "rowSizePerPage : " + $(this).data("rowsizeperpage") );
		 
		 // 값을 취득했구나 이제 전송하면 된다!
	 	curPage.val( $(e.target).data("curpage") );
	 	rowSizePerPage.val( $(e.target).data("rowsizeperpage") );
	 	searchForm.submit();
	});




});


</script>



</head>

<jsp:useBean id="memSearchVO" class="kr.or.nextit.member.vo.MemberSearchVO"></jsp:useBean>
<jsp:setProperty property="*" name="memSearchVO"/>


<%
// 직업, 취미 코드 가져오기
ICommCodeService codeService = new CommCodeServiceImpl();
List<CodeVO> jobList  = codeService.getCodeListByParent("JB00");
request.setAttribute("jobList", jobList);

List<CodeVO> hobbyList  = codeService.getCodeListByParent("HB00");
request.setAttribute("hobbyList", hobbyList);
%>


<%
// 멤버 리스트를 가져와야겠지
IMemberService memberService = new MemberServiceImpl();

try{
	List<MemberVO> memberList = memberService.getMemberList(memSearchVO);
	System.out.println("화면에서의 memSearchVO " + memSearchVO);
	request.setAttribute("memberList", memberList);
}catch(BizNotEffectedException bne){
	request.setAttribute("bne", bne);
}catch(DaoException de){
	request.setAttribute("de", de);
}

// 코드 리스트 가져오기
/* ICommCodeService codeService = new CommCodeServiceImpl();

List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
System.out.println("화면까지 끌어온 jobList : " + jobList);
System.out.println("화면까지 끌어온 hobbyList : " + hobbyList);
request.setAttribute("jobList", jobList);
request.setAttribute("hobbyList", hobbyList); */
// 이거 필요 없음 .. 쿼리문에서 조인하면 됨



%>








<body>

<div id="wrap">
    <div class="header">
        <div class="top_nav">
            <!-- header 영역 -->
            <%@ include file="/header/header.jsp" %>
            
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
                <h1>회원 목록</h1>
            </div>
            
<!--예외 발생시 처리 -->
		 <c:if test="${bne != null || de != null}">
				<div class="alert alert-warning">
					목록을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
				</div>	
				<div class="div_button">
					<input type="button" onclick="history.back();" value="뒤로가기">
				</div>
		 </c:if>
		 
            
            
<!--정상적으로 회원리스트를 불러왔을 경우 -->        
          <c:if test="${bne == null && de == null }">
          
<!--검색 form  -->
            	<div class="div_search">
					<form name="search" action="${pageContext.request.contextPath}/member/memberList.jsp" method="post">
						<input type="hidden" name="curPage" value="${memSearchVO.curPage }"> 
						<input type="hidden" name="rowSizePerPage" value="${memSearchVO.rowSizePerPage }">
						<div>
							<label for="id_searchType">검색</label>
							&nbsp;&nbsp;
							<select id="id_searchType" name="searchType">
								<option value="ID" ${memSearchVO.searchType == "ID" ? "selected='selected'" : "" }>아이디</option>
								<option value="NM" ${memSearchVO.searchType == "NM" ? "selected='selected'" : "" }>이름</option>
								<option value="HP" ${memSearchVO.searchType == "HP" ? "selected='selected'" : "" }>휴대폰</option>
							</select>
							<input type="text" name="searchWord" value="${memSearchVO.searchWord }" placeholder="검색어">
							&nbsp;&nbsp;&nbsp;&nbsp;	
							
							<label for="id_searchJob">직업</label>
							&nbsp;&nbsp;
							<select id="id_searchJob" name="searchJob">
								<option value="">-- 전체 --</option>
								<c:forEach items="${jobList }" var="job">
									<option value="${job.commCd }" ${memSearchVO.searchJob == job.commCd ? "selected='selected'" : "" }>${job.commNm }</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<label for="id_searchHobby">취미</label>
							&nbsp;&nbsp;
							<select id="id_searchHobby" name="searchHobby">
								<option value="">-- 전체 --</option>
								<c:forEach items="${hobbyList }" var="hobby">
										<option value="${hobby.commCd }" ${memSearchVO.searchHobby == hobby.commCd ? "selected='selected'" : "" }>${hobby.commNm }</option>
								</c:forEach>							 
						 
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<button type="submit">검 색 </button>
							<button type="submit" id="id_btn_reset" >초기화</button>
						</div>
					</form>
				</div>  
            	
<!--전체건수조회-->
            	<div class="rowSizePerPage">
					<div>
						전체   ${memSearchVO.totalRowCount }건 조회
						<select id="id_rowSizePerPage" name="rowSizePerPage">
							<c:forEach begin="10" end="50" step="10" var="i">
								<option value="${i }" ${memSearchVO.rowSizePerPage == i ? "selected = 'selected'" : ""}>${i }</option>
							</c:forEach>
							
						</select>
					</div>
				</div>
            	
	            <!-- 리스트 -->
	            <div id="div_table">
	                <table>
	                    <colgroup>
	                        <col width="60">
	                        <col >
	                        <col width="150">
	                        <col width="150">
	                        <col width="150">
	                        <col width="100">
	                        <col width="100">
	                        <col width="100">
	                    </colgroup>
	                    <thead>
	                        <tr>
	                        	<th>순번</th>
	                         <th>ID</th>
								<th>회원명</th>
								<th>HP</th>
								<th>생일</th>
								<th>직업</th>
								<th>취미</th>
								<th>마일리지</th>
	                        </tr>
	                    </thead>
	                    <tbody>
<!-- 회원 리스트 출력하기  --> 
	                      <c:forEach items="${memberList }" var="memList">
					 			<tr>
					 				<td><c:out value="${memList.rnum }"></c:out> </td><!-- 순번 -->
									<td><c:out value="${memList.memId }"></c:out></td><!--ID -->
									<td><c:out value="${memList.memName }"></c:out></td><!--회원명  -->
									<td><c:out value="${memList.memHp }"></c:out></td><!--HP  -->
									<td><c:out value="${memList.memBir }"></c:out></td><!--생일  -->
									<!--직업  -->
									<td><c:out value="${memList.memJob }"></c:out></td>
									<!-- 취미 -->
								 	<td><c:out value="${memList.memHobby }"></c:out></td>
									<td><c:out value="${memList.memMileage }"></c:out></td> <!--마일리지  -->
								</tr>
	                      </c:forEach>
						  
	                        
	                    </tbody>
	                </table>
	            </div>
	
	              <!-- paging -->
	            <div class="div_paging">
	                <ul class="pagination">
<!-- 페이징 처리  -->

					<!-- 꺽새처리 -->
<%-- 					<c:if test="${memSearchVO.curPage > 10 }">
		                <li><a href="${pageContext.request.contextPath }/member/memberList.jsp?curPage=${memSearchVO.firstPage - 1}&rowSizePerPage=${memSearchVO.rowSizePerPage}">&laquo;</a></li>
					</c:if> 
		                
		                
					<c:if test="${memSearchVO.curPage > 1 }">
						<li><a href="${pageContext.request.contextPath }/member/memberList.jsp?curPage=${memSearchVO.curPage - 1}&rowSizePerPage=${memSearchVO.rowSizePerPage}">&lt;</a></li>
					</c:if> 
					 
		                
					<!-- 포이치문으로 페이징 번호 출력 -->
					<c:forEach begin="${memSearchVO.firstPage }" end="${memSearchVO.lastPage }"
					step="1" var="i">
						<c:if test="${memSearchVO.curPage != i }">
							<li><a href="${pageContext.request.contextPath }/member/memberList.jsp?curPage=${i}&rowSizePerPage=${memSearchVO.rowSizePerPage}">${i}</a></li>
						</c:if>
						<c:if test="${memSearchVO.curPage == i }">
							<li><a href="#" class="curPage_a">${i}</a></li>
						</c:if>
					</c:forEach>
					
					<!-- 꺽새처리 -->
					<c:if test="${memSearchVO.curPage < memSearchVO.totalPageCount }">
						<li><a href="${pageContext.request.contextPath }/member/memberList.jsp?curPage=${memSearchVO.curPage + 1}&rowSizePerPage=${memSearchVO.rowSizePerPage}">&gt;</a></li>
					</c:if>
						 
					 <c:if test="${memSearchVO.lastPage < memSearchVO.totalPageCount }">
						<li><a href="${pageContext.request.contextPath }/member/memberList.jsp?curPage=${memSearchVO.lastPage + 1}&rowSizePerPage=${memSearchVO.rowSizePerPage}">&raquo;</a></li>
					 </c:if>  --%>
					 
					 <!-- data로 보내기 -->
					<c:if test="${memSearchVO.curPage > 10 }">
		                <li><a href="#" data-curPage=${memSearchVO.firstPage -1} data-rowSizePerPage=${memSearchVO.rowSizePerPage }>&laquo;</a></li>
					</c:if> 
		                
		                
					<c:if test="${memSearchVO.curPage > 1 }">
						<li><a href="#" data-curPage=${memSearchVO.curPage -1} data-rowSizePerPage=${memSearchVO.rowSizePerPage }>&lt;</a></li>
					</c:if> 
					 
		                
					<!-- 포이치문으로 페이징 번호 출력 -->
					<c:forEach begin="${memSearchVO.firstPage }" end="${memSearchVO.lastPage }"
					step="1" var="i">
						<c:if test="${memSearchVO.curPage != i }">
							<li><a href="#" data-curPage=${ i } data-rowSizePerPage=${memSearchVO.rowSizePerPage }>${i}</a></li>
						</c:if>
						<c:if test="${memSearchVO.curPage == i }">
							<li><a href="#" class="curPage_a">${i}</a></li>
						</c:if>
					</c:forEach>
					
					<!-- 꺽새처리 -->
					<c:if test="${memSearchVO.curPage < memSearchVO.totalPageCount }">
						<li><a href="#" data-curPage=${memSearchVO.curPage + 1} data-rowSizePerPage=${memSearchVO.rowSizePerPage }>&gt;</a></li>
					</c:if>
						 
					 <c:if test="${memSearchVO.lastPage < memSearchVO.totalPageCount }">
						<li><a href="$#" data-curPage=${memSearchVO.lastPage + 1} data-rowSizePerPage=${memSearchVO.rowSizePerPage }>&raquo;</a></li>
					 </c:if> 
					 
					 
	                </ul>
	            </div>
          
          </c:if>
            
            
            
            
            
        </div>
    </div>

    <!-- footer -->
    <footer id="page_footer">
		<!-- footer영역 -->
		<%@ include file="/footer/footer.jsp" %>
    </footer>
</div>
</body>
</html>
