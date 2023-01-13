<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/memberList.css">
<style type="text/css">
	.div_board_write{
		display: flex;
		left: 250px;
		top: 0px;	
	}
	.div_board_write > input{
		margin-left:10px;
		height: 30px;
	}
	#modal_div1{
		width: 100%;
		height: 100%;
		position: fixed; 
		top: 0; right: 0; bottom: 0; left: 0;
		background-color: rgba(0,0,0,0.4);	
		z-index: 1;
		display: none;
	}
	#modal_div2{
		width: 400px;
		height: 200px;
		background-color: lightgrey;
		text-align: center;
		position: fixed;
		left: calc(50% - 200px);
		top: calc(50% - 200px);
	}
	#modal_div2 > p {
		margin-top: 50px;
	}
	#modal_div2 > a {
		margin-left: 300px;
	}
	form[name="excelUpload"]{
		width: 350px;
		height: 350px;
		/* background-color: tomato; */
		margin-top: 20px;
	}
	.int-area{
		color: white;
		width: 300px;
		height: 150px;
		/* background-color: lightblue; */ 
	}
	.int-area:first-child{
		padding-top: 20px;
	}
	.int-area input{
		width: 80%;
		padding: 30px 10px 10px;
		background-color: transparent;
		border: none;
		border-bottom: 1px solid #999;
		font-size: 18px;
		color: #fff;
	}
	.btn-area > button{
		width: 40%;
		height: 40px;
		color: #fff;
		font-size: 20px;
		border: none;
		border-radius: 15px;
		background-color: lightpink;
		position: relative;
		top: -50px
	}
	.btn-area > button:active{
		color: gray;
	}
</style>


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
            
			<c:if test="${bnf ne null or de ne null}">
				<div class="alert alert-warning">
					목록을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
				</div>	
				<div class="div_button">
					<input type="button" onclick="history.back();" value="뒤로가기">
				</div>
			</c:if>      
            
            <c:if test="${bnf eq null and de eq null}">
            	<div class="div_search">
					<form name="search" action="${pageContext.request.contextPath}/member/memberList" method="post">
						<input type="hidden" name="curPage" value="${searchVO.curPage}"> 
						<input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage}">
						<div>
							<label for="id_searchType">검색</label>
							&nbsp;&nbsp;
							<select id="id_searchType" name="searchType">
								<option value="ID" ${searchVO.searchType eq "ID" ? "selected='selected'" : ""}>아이디</option>
								<option value="NM" ${searchVO.searchType eq "NM" ? "selected='selected'" : ""}>이름</option>
								<option value="HP" ${searchVO.searchType eq "HP" ? "selected='selected'" : ""}>휴대폰</option>
							</select>
							<input type="text" name="searchWord" value="${searchVO.searchWord }" placeholder="검색어">
							&nbsp;&nbsp;&nbsp;&nbsp;	
							
							<label for="id_searchJob">직업</label>
							&nbsp;&nbsp;
							<select id="id_searchJob" name="searchJob">
								<option value="">-- 전체 --</option>
								<c:forEach items="${jobList}" var="jobCode">
									<option value="${jobCode.commCd}" ${searchVO.searchJob eq jobCode.commCd ? "selected='selected'" : "" } >${jobCode.commNm}</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<label for="id_searchHobby">취미</label>
							&nbsp;&nbsp;
							<select id="id_searchHobby" name="searchHobby">
								<option value="">-- 전체 --</option>
								<c:forEach items="${hobbyList}" var="hobbyCode">
									<option value="${hobbyCode.commCd}" ${searchVO.searchHobby eq hobbyCode.commCd ? "selected='selected'" : "" }>${hobbyCode.commNm}</option>
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
	                    	 <col width="40">	
	                    
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
	                        		<th><input type="checkbox" id="check_all"> </th>
	                    
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
	
	                     <c:forEach items="${memberList }" var="member">
					 			<tr>
					 				<td> <input type="checkbox" name="check_list" value="${member.memId }"></td>
					 				<td>${member.rnum }</td>
					 				
									<td><a href="${pageContext.request.contextPath }/member/memberRole?memId=${member.memId}"
												> ${member.memId }</a> </td>
									
									<td>${member.memName }</td>
									<td>${member.memHp }</td>
									<td>${member.memJoinDate}</td>
									<c:forEach items="${jobList }" var="code">
										<c:if test="${code.commCd eq member.memJob }">
											<td>
												${code.commNm }
											</td>
										</c:if>
									</c:forEach>
									<c:forEach items="${hobbyList }" var="code">
										<c:if test="${code.commCd eq member.memHobby }">
											<td>
												${code.commNm }
											</td>
										</c:if>
									</c:forEach>
									<td>${member.memMileage }</td>
								</tr>
						 	</c:forEach>
	                        
	                        
	                    </tbody>
	                </table>
	            </div>
	
	              <!-- paging -->
	            <div class="div_paging">
	                <ul class="pagination">
	            		<c:if test="${searchVO.firstPage gt 10 }">
		                	<li><a href="#" data-curPage=${searchVO.firstPage-1 }   data-rowSizePerPage=${searchVO.rowSizePerPage } >&laquo;</a></li>
		                </c:if> 
		                
						<c:if test="${searchVO.curPage ne 1 }">
							<li><a href="#" data-curPage=${searchVO.curPage-1 }   data-rowSizePerPage=${searchVO.rowSizePerPage } >&lt;</a></li>
						</c:if>
		                
						<c:forEach begin="${searchVO.firstPage }" end="${searchVO.lastPage }" step="1" var="i"> 
							<c:if test="${searchVO.curPage ne i}">
								<li><a href="#" data-curPage=${i }   data-rowSizePerPage=${searchVO.rowSizePerPage }  >${i }</a></li>
							</c:if>
							<c:if test="${searchVO.curPage eq i }">
								<li><a href="#" class="curPage_a">${i }</a></li>
							</c:if>
						</c:forEach>
	                
						<c:if test="${searchVO.lastPage ne searchVO.totalPageCount }">
							<li><a href="#" data-curPage=${searchVO.curPage+1  }   data-rowSizePerPage=${searchVO.rowSizePerPage }  >&gt;</a></li>
							<li><a href="#" data-curPage=${searchVO.lastPage+1  }   data-rowSizePerPage=${searchVO.rowSizePerPage }>&raquo;</a></li>
						</c:if>
	                </ul>
	                
	                <div class="div_board_write">
	                		<input type="button" onclick="fn_delete()" value="회원삭제">
	                		<input type="button" onclick="fn_memberExcelUpload()" value="회원엑셀업로드">
	                		<input type="button" onclick="fn_memberExcelDownload()" value="회원엑셀다운로드">
	                </div>
	                
	                
	            </div>
           	</c:if>
        </div>
    </div>
    
	<!-- 엑셀 업로드 모달 -->
	<div id="modal_div1" >
		<div id="modal_div2" >
			<form name="excelUpload" action="${pageContext.request.contextPath }/member/memberExcelUpload" method="post" enctype="multipart/form-data">
				<div class="int-area">
					<span>엑셀파일을 업로드 해주세요</span>	
					<input type="file" name="memberExcelUpload" required="required">
				</div>
				<div class="btn-area">
					<button type="submit" >등록</button>
					<button type="button" onclick="fn_Cancel()">취소</button>
				</div>
			</form>
		</div>
	</div>
	
	
	
	<form name="memMultiDelete"  action="${pageContext.request.contextPath}/member/memberMultiDelete" 
			method="post">
		<input type="hidden" name="memMultiId" value="">
	</form>



<script type="text/javascript">

$('#id_rowSizePerPage').change(function() {
	sf.find("input[name='curPage']").val(1);
	sf.find("input[name='rowSizePerPage']").val($(this).val());
	sf.submit();
});


let sf =$("form[name='search']");
let curPage= sf.find("input[name='curPage']");
let rowSizePerPage= sf.find("input[name='rowSizePerPage']");
$('ul.pagination li a').click(function(e) {
	e.preventDefault();
	
	console.log($(e.target).data("curpage"));  //개발자도구에서 보면 소문자임 ( HTML5에서 대문자는 지원하지 않음 )
	
	curPage.val($(this).data("curpage")); 
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
	sf.find("select[name='searchJob'] option:eq(0)").prop("selected", "selected");
	sf.find("select[name='searchHobby'] option:eq(0)").prop("selected", "selected");
	sf.find("input[name='searchWord']").val("");
	sf.find("input[name='curPage']").val(1);
	sf.find("input[name='rowSizePerPage']").val(10);
	sf.submit();
});  



function fn_delete(){
	console.log("fn_delete");
	
	let result = confirm("삭제를 진행하시겠습니까?");
	
	if(result){
		let cl = $("input:checkbox[name='check_list']:checked");
		console.log("cl : ", cl);
		console.log("cl.length: ", cl.length);
		
		if(cl.length == 0 ){
			alert("선택된 회원이 없습니다. 삭제하고자 하는 회원에 체크를 한 후 진행해 주세요");
			return;
		}
		
		let multiId = [];
		cl.each(function(){
			console.log("$(this).val() :" + $(this).val());
			multiId.push($(this).val());
		});
		
		console.log("multiId : ", multiId	);
		console.log("JSON.stringify(multiId) : ", JSON.stringify(multiId)	);
		$("form[name='memMultiDelete']").find("input[name='memMultiId']").val(JSON.stringify(multiId));
		$("form[name='memMultiDelete']").submit();
	}
}


$("#check_all").click(function(){
	console.log("check_all");
	
	if($(this).prop("checked")){
		$("input[name='check_list']").prop("checked", true);
	}else{
		$("input[name='check_list']").prop("checked", false);
	}
	
});	

// 회원 엑셀 파일 업로드, 다운로드 메소드
function fn_memberExcelUpload(){
	//alert("fn_memberExcelUpload");
	$("#modal_div1").fadeIn();
}
function fn_Cancel(){
	console.log("fn_Cancel");
	$("#modal_div1").fadeOut();
}

// 엑셀 다운로드 메소드
function fn_memberExcelDownload() {
	console.log("fn_memberExcelDownload");
	
	// 검색 폼 취득
	let form = $("form[name='search']");
	console.log("form.serialize() : " , form.serialize());
	// form.serialize()로 검색 폼 데이터 가져가기
	location.href = "<c:url value='/member/memberExcelDownload'/>"
						+"?"+form.serialize();
}


</script>