<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/freeBoardForm.css">

<style>
/*a태그 css 입히기*/
.td_right a{
	color: #166cea;
}
.td_right a:hover{
	color: #ed5422;
}
/* modal css */
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
form[name="deleteForm"]{
	width: 350px;
    height: 350px;
    /* background-color: tomato; */
    margin-top: 20px;
}
.int-area{
    width: 300px;
    height: 150px;
    /* background-color: lightblue; */ 
}
.int-area:first-child{
	padding-top: 40px;
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
/* 버튼클릭액션 */
.btn-area > button:active{
	color: gray;
}

/*댓글 적용하기02			css 입히기  */
.content02{
	width: 1280px;
	height: 100%;
}
#reply_div{
	width: 1280px;
    height: 100px;
    text-align: center;
}
#reply_div label{
    position: relative;
    left: 75px;
    top: -15px;
}
#reply_div textarea{
    position: relative;
    left: 150px;
	resize:none;
   	border-radius: 10px;
  	padding-left: 10px;
  	padding-right: 10px;
  	padding-top:5px
}
#reply_div button{
	position: relative;
    left: 160px;
    top: -15px;
    border: none;
    border-radius: 5px;
    padding: 5px;
}
/* 버튼액션추가 */
#reply_div button:active{
    transform:translate(2px,2px);
}
/* 댓글 목록 css 입히기  */
#reply_list_div{
	width: 1280px;
}
.replyList_row{
   	width: 100%;
    /* height: 100px; */
    min-height: 70px;
}
.replyList_row > div{
	display: inline-block;
}

.replyList_row > div:last-child{
	display: block;
}
.replyList_row span{
    display: block;
    width: 10px;
    height: 10px;
    border-bottom: 1px solid blue;
    border-left: 1px solid blue;
    position: relative;
    top:-3px;
}
.replyList_row > div:first-child{
	margin-left : 450px;	
	margin
}
.replyList_row > div:last-child{
	margin-left : 470px;	
   	margin-top: 10px;
}
.reply_button{
	float: right;
   	margin-right: 100px;
}
.reply_button > button{
    border: none;
    border-radius: 5px;
    padding: 3px;
    margin-left: 5px;
}
.reply_button > button:first-child{
	position: absolute;
	right:350px;
}
.reply_button > button:last-child{
	position: absolute;
    right:310px;
}
.reply_button button:active{
    transform:translate(2px,2px);
}


/*댓글 적용하기			댓글 페이징 css 입히기  */
.div_paging{
    width: 100%;
    /* height: 100px; */
    height: 100%;
    /* background-color: lightcyan; */
    margin-bottom: 20px;
    margin-left: 150px;
}
.pagination{
    width: 300px;
    /* height: 30px; */
    height: 100%;
    /* background-color: lightpink; */
    text-align: center;
    display: flex;
}
.pagination > li{
	width: 20px;
}
.pagination a{
   padding-left:7px
}
.pagination a:hover {
   color: lightskyblue;
   font-weight:bold;
}
.curPage_a{
	color: red;
   	font-weight:bold;
}

/* 댓글edit하기01-2  css*/
.reply_content textarea{
    position: relative;
    left: 50px;
	resize:none;
   	border-radius: 10px;
  	padding-left: 10px;
  	padding-right: 10px;
  	padding-top:5px
}

</style>
<script>
function fn_freeDelete(){
	console.log("fn_freeDelete");
	$("#modal_div1").fadeIn();
}
function fn_Cancel(){
	console.log("fn_Cancel");
	$("#modal_div1").fadeOut();
}
function fn_freeHide(){
	//alert("fn_freeHide");
	let f = $("form[name='freeHide']");
	f.submit();
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
            
			<!-- 해당 정보를 불러오지 못한 경우 처리 -->
				<c:if test="${bne ne null or de ne null }">
					<div class="alert alert-warning">
							해당글을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
					</div>	
					<div class="div_button">
	                      <input type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeList'" value="목록">
	                </div>
                </c:if>
            
            <!-- 해당 정보를 올바르게 불러온 경우 처리  -->
            	<c:if test="${bne eq null and de eq null }">
                  <div id="div_table">
                      <table>
                          <colgroup>
                              <col width="200">
                              <col width="400">
                          </colgroup>
                          <tr>
                              <td class="td_left">글 번호</td>
                              <td class="td_right">
                                  ${freeBoard.boNo }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 제목</td>
                              <td class="td_right">
                                 ${freeBoard.boTitle }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 분류</td>
                              <td class="td_right">
                                  ${freeBoard.boCategoryNm }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">작성자명</td>
                              <td class="td_right">
								  ${freeBoard.boWriter }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 내용</td>
                              <td class="td_right">
                              		${freeBoard.boContent }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">IP</td>
                              <td class="td_right">
                              		${freeBoard.boIp }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">조회수</td>
                              <td class="td_right">
                                  ${freeBoard.boHit }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">최근 등록 일자</td>
                              <td class="td_right">
                                  ${freeBoard.boModDate ne null ? freeBoard.boModDate : freeBoard.boRegDate }
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">첨부파일</td>
                              <td class="td_right">
                                  <c:forEach items="${freeBoard.attachList }" var="attach" varStatus="status">
                                  			${status.count }	<!-- 1부터 시작 -->
                                  			<a href="<c:url value='/attach/download/${attach.atchNo }'/>"
                                  			target="_blank"> <!-- 해당 a태그 클릭시 새로운 창을 출력합니다. -->
                                  					${attach.atchOriginalName }
                                  			</a>
                                  			<br>
                                  			&nbsp;&nbsp;&nbsp; 크기 : ${attach.atchConvertSize }, 다운로드 횟수 : ${attach.atchDownHit }
                                  			<br>
                                  </c:forEach>
                              </td>                          
                          </tr>
                      </table>
                  </div>
                  <!-- 버튼 -->
                  <div class="div_button">
                      <input type="button" onclick="location.href='${pageContext.request.contextPath }/free/freeList?searchType=${searchVO.searchType}&searchWord=${searchVO.searchWord}&searchCategory=${searchVO.searchCategory}&curPage=${searchVO.curPage}&rowSizePerPage=${searchVO.rowSizePerPage}'" value="목록">

                      <c:if test="${freeBoard.boWriter eq memberVO.memId  }">
                      	<input type="button" onclick="location.href='${pageContext.request.contextPath }/free/freeEdit?boNo=${freeBoard.boNo }'" value="수정">
                      	<input type="button" onclick="fn_freeDelete()" value="삭제">
                      </c:if>
						<c:forEach items="${memberVO.userRoleList }" var="roleList">
							<c:if test="${roleList.userRole eq 'AD' }">
								<input type="button" onclick="fn_freeHide()" value="숨김">
								<form name="freeHide" action="${pageContext.request.contextPath }/free/freeHide" method="post">
									<input type="hidden" name="memId" value="${memberVO.memId }" />	
									<input type="hidden" name="boNo" value="${freeBoard.boNo }" />	
								</form>
							</c:if>
						</c:forEach>
                  </div>
             </c:if>
             
        </div>
        
        <!-- 댓글창 구현 -->
        <div class="content2">
        	<div id="reply_div">
        		<form name="reply_form" action="" method="post">
        			<input type="hidden" name="reCategory" value="FREE">
        			<input type="hidden" name="reParentNo" value="${freeBoard.boNo }">
        			<input type="hidden" name="reMemId" value="${memberVO.memId }">
        			<input type="hidden" name="reIp" value="${pageContext.request.remoteAddr }">
        			<div>
        				<label>댓글</label>
        				<textarea name="reContent" rows="2" cols="70"></textarea>
        				<button id="btn_reply_regist">댓글등록</button>
        			</div>
        		</form>
        	</div>
        	
        	<!-- 댓글 리스트 구현 -->
        	<div id="reply_list_div">
        		<!-- 댓글 리스트 -->
        	</div>
        </div>
        
        
    </div><!--contents	e  -->


	<!-- 글삭제 모달 -->
	<div id="modal_div1" >
		<div id="modal_div2" >
			<form name="deleteForm" action="${pageContext.request.contextPath }/free/freeDelete" method="post">
				<input type="hidden" id="boNo" name="boNo" value="${freeBoard.boNo }"/>
				<input type="hidden" id="boWriter" name="boWriter" value="${memberVO.memId }"/>
	            <div class="int-area">
	                <input type="password" id="boPass" name="boPass" value="" placeholder="PASSWORD" autocomplete="off" required/>
	            </div>
	            <div class="btn-area">
	                <button type="submit" >삭제</button>
	                <button type="button" onclick="fn_Cancel()">취소</button>
	            </div>
	        </form>
		</div>
	</div>


<!-- 댓글 script -->
<script type="text/javascript">
$("#btn_reply_regist").on("click", function(e) {
	e.preventDefault();
	
	let replyForm = $("form[name='reply_form']")
	// .serialize()는 폼태그 안을 get방식의 string 형태로 출력해줌 
	// console.log("reply_form : ", replyForm.serialize())
	
	$.ajax({
		  url:"<c:url value='/reply/replyRegister' />"
		, type: "post"
		, data: replyForm.serialize()
		, success : function() {
			// alert("success");
			$("textarea[name='reContent']").val("");
			
			fn_reply_list();
		}
		, error : function() {
			alert("error");
		}
		
	});
});

function fn_reply_list(curPage) {
	// alert("fn_reply_list");
	/*
	$.ajax({
		 url:"<c:url value='/reply/replyList'/>"
		,type:"post"
		,data:{"reCategory":"FREE", "reParentNo":"${freeBoard.boNo}", "curPage":curPage}
		,success:function(data){
			// alert("success");
			console.log("data : ", data);
			
			
			$("#reply_list_div").empty();
			$.each(data.replyList, function(i, element) {
				let str = "";
				str += ' <div class="replyList_row" data-reNo="'+ element.reNo +'"> ';
				str += ' 		<div><span class="reply_span"></span></div> ';
				str += '		<div>'+ element.reMemId +'</div>'
				str += '		<div>('+ element.reRegDate +')</div>'
				if(element.reMemId == "${memberVO.memId}"){
					str += '	<div class="reply_button">'
					str += '		<button type="button" name="btn_reply_edit">수정</button>'
					str += '		<button type="button" name="btn_reply_delete">삭제</button>'
					str += '	</div>'
				}
				str += '		<div class="reply_content">'
				str += '			<pre>'+ element.reContent +'</pre>'
				str += '		</div>'
				str += ' </div> ';
				
				$("#reply_list_div").append(str);
			});
			
			// 페이징 번호 처리
			console.log("firstPage : ", data.firstPage);
			console.log("curPage : ", data.curPage);
			console.log("lastPage : ", data.lastPage);
			
			let paging_str = "";
			paging_str += ' <div class="div_paging">'
			paging_str += ' 		<ul class="pagination">'
			
			// 꺽새
			if(data.firstPage > 10){
				paging_str += '		<li><a href="javascript:fn_paging('+ 
											(data.firstPage-1) +')">&laquo;</a></li>'
			}
			if(data.curPage != 1){
				paging_str += '		<li><a href="javascript:fn_paging('+ 
											(data.curPage-1) +')">&lt;</a></li>'
			}
			// 번호
			for(let i = data.firstPage; i <= data.lastPage; i++){
				if(data.curPage != i){
					paging_str += '	<li><a href="javascript:fn_paging('+ (i) +')">'+ i +'</a></li>'
				}else{
					paging_str += '	<li class="curPage_a">'+ i +'</li>'
				}
			}
			// 꺽새
			if(data.lastPage != data.totalPageCount){
				paging_str += '		<li><a href="javascript:fn_paging('+ 
											(data.curPage+1) +')">&gt;</a></li>'
				paging_str += '		<li><a href="javascript:fn_paging('+ 
											(data.lastPage+1) +')">&raquo;</a></li>'
			}
			paging_str += ' 		</ul>'
			paging_str += ' </div>'
			
			console.log("paging_str : ", paging_str);
			$("#reply_list_div").append(paging_str);
			
		}
		,
		error: function(err) {
			console.log("err.status : ", err.status);
			alert("댓글 목록을 불러오는데 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
		} 
		
	});
	*/
	
	let url = "<c:url value='/reply/replyList' />"
	$("#reply_list_div").load(url
			, {"reCategory":"FREE", "reParentNo":"${freeBoard.boNo}", "curPage":curPage});
	
}


$(function() {
	fn_reply_list();
	
	// $("button[name='btn_reply_delete']")
	// 페이지 로딩시 없고 ajax로 받아서 그리니 온클릭 이벤트를 페이지 로딩시 주면
	// 못 집어낸다
	// $("button[name='btn_reply_delete']").on("click", function(e) {
	// 페이지를 그릴때 있는 친구서부터 타고 들어가서 집어줘야한다.
	
	// 삭제버튼 클릭시 이벤트
	$("#reply_list_div").on("click", "button[name='btn_reply_delete']", function(e){
		
		let reNo = $(this).closest(".replyList_row").data('reno');
		console.log("reNo : ", reNo);
		
		let reMemId = "${memberVO.memId}"
		console.log("reMemId : ", reMemId);
		
		// 여기서 안담김
		let param = {"reNo": reNo, "reMemId": reMemId};
		// 잘담김
		console.log("param : ", param)
		let ret = confirm("댓글을 정말 삭제 하시겠습니까?");
		if(ret){
			$.ajax({
				url : "<c:url value='/reply/replyDelete'/>"
				,type: "post"
				,data: param
				,success: function(data) {
					console.log("data", data);
					if(data.result){
						alert("댓글이 삭제되었습니다.")
						$("#reply_list_div").html("");
						fn_reply_list();
					}else{
						alert("처리 도중 에러가 발생하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
					}
				}
				,error: function(err) {
					console.log("err.status : ", err.status);
					alert("처리 도중 에러가 발생하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
					$("#reply_list_div").html("");
					fn_reply_list();
				}
			});
		}
		
		
	});
	
	
	// 수정 버튼 클릭시 이벤트 추가
	$("#reply_list_div").on("click", "button[name='btn_reply_edit']", function(e){
		console.log("e : ", e);
		// 다음 형제 요소 탐색 .nextElementSibling
		// 이전 형제 요소 탐색 .previousElementSibling
		console.log("e.target.nextElementSibling : ", e.target.nextElementSibling);
		// 안의 값 바꾸기(저장, 취소 버튼으로)
		e.target.innerHTML = "저장";
		e.target.setAttribute("name", "btn_reply_edit_save")
		e.target.nextElementSibling.innerHTML = "취소";
		e.target.nextElementSibling.setAttribute("name", "btn_reply_edit_false")
		// 텍스트 에어리어로 바꾸기
		console.log("e.target.parentElement.nextElementSibling : ", e.target.parentElement.nextElementSibling);
		
		let editText = "";
		editText += '<form name="reply_eidt_form" action="" method="post">'
		editText += '		<input type="hidden" name="reNo" value="">'
		editText += '		<input type="hidden" name="reMemId" value="${memberVO.memId }">'
		editText += '		<textarea id="editContent" name="reContent" rows="1" cols="70">'
							+e.target.parentElement.nextElementSibling.firstElementChild.innerHTML 
							+'</textarea>';
		editText += '</form>'
		
		// 글 넣기
		e.target.parentElement.nextElementSibling.innerHTML = editText;
		/*
		e.target.parentElement.nextElementSibling.innerHTML = '<textarea id="editContent" name="reEditContent" rows="1" cols="70">'
															+e.target.parentElement.nextElementSibling.firstElementChild.innerHTML +'</textarea>';
		*/
	});
	
	// 수정 버튼의 취소 클릭시 다시 되돌아가기
	$("#reply_list_div").on("click", "button[name='btn_reply_edit_false']", function(e){
		let ret = confirm("정말로 댓글 수정을 취소하십니까?");
		if(ret){
			e.target.innerHTML = "삭제";
			e.target.setAttribute("name", "btn_reply_delete")
			e.target.previousElementSibling.innerHTML = "수정";
			e.target.previousElementSibling.setAttribute("name", "btn_reply_edit")
			console.log("취소버튼시 : ", e.target.parentElement.nextElementSibling.firstElementChild.lastElementChild.innerHTML);
			let editFalseContent = e.target.parentElement.nextElementSibling.firstElementChild.lastElementChild.innerHTML;
			e.target.parentElement.nextElementSibling.innerHTML = '<pre>'+ editFalseContent +'</pre>'
		}
	});
	
	// 수정 버튼의 저장 클릭시 ajax통신으로 업데이트
	$("#reply_list_div").on("click", "button[name='btn_reply_edit_save']", function(e){
		// 필요한 정보 집어주고
		console.log("텍스트 나왔나요 : ", $(this).closest(".replyList_row").find("textarea"));
		// 저장눌렀을 때 히든태그에 글 넘버 넣기
		let reNo = $(this).closest(".replyList_row").data('reno');
		$("#reply_list_div input[name='reNo']").val(reNo);
		// let reContent = $(this).closest(".replyList_row").find("textarea")["0"].innerHTML;
		console.log('글번호 잘 들어갔니?', $("#reply_list_div input[name='reNo']").val());
		
		let editForm = $("form[name='reply_eidt_form']");
		
		let ret = confirm("정말로 댓글을 수정하십니까?");
		if(ret){
			$.ajax({
				 url : "<c:url value='/reply/replyUpdate'/>"
				,type: "post"
				,data: editForm.serialize()
				,success: function(data) {
					console.log("수정 data : ", data)
					if(data.result){
						alert("댓글이 수정되었습니다.")
						$("#reply_list_div").html("");
						fn_reply_list();
					}else{
						alert("처리 도중 에러가 발생하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
					}
				}
				,error: function(err) {
					console.log("err.status : ", err.status);
					alert("처리 도중 에러가 발생하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
					$("#reply_list_div").html("");
					fn_reply_list();
				}
			});
		}
	});
	
});

function fn_paging(curPage) {
	console.log("curPage : ", curPage);
	
	$("#reply_list_div").html("");
	fn_reply_list(curPage);
}

</script>

