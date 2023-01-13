<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/freeBoardEdit.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/smarteditor2-2.8.2.3/js/HuskyEZCreator.js"></script>
<script>
function fn_submitCheck(){
	/*
	console.log("fn_submitCheck");
	
	if($("input[name='boTitle']").val() == ""){
		alert("제목을 작성해주세요");
		return;
	}else if($("input[name='boPass']").val() == ""){
		alert("비밀번호를 입력해주세요");
		return;
	}else if($("input[name='boCategory']").val()==""){
		alert("글 분류를 선택해주세요");
		return;
	}
	*/
	
	let ret = confirm("저장하시겠습니까?");
	if(ret){
		let f= document.freeModify;
		//textarea의 값을 가져오기 위해서는 UPDATE_CONTENTS_FIELD 메시지를 호출해주어야 한다.	
		oEditors.getById["modifyTxt"].exec("UPDATE_CONTENTS_FIELD", []); 
		let content = document.getElementById("modifyTxt").value
		console.log("f : ", f);
		console.log("content : ", content);
		
		f.submit();
	}else{
		alert("취소하셧습니다.");
	}
}

// 스마트 에디터
// 스마트 에디터 1차
let oEditors = [];
smartEditor = function() {
  console.log("Naver SmartEditor")
  nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "modifyTxt",
    sSkinURI: "${pageContext.request.contextPath }/smarteditor2-2.8.2.3/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
  })
}
$(document).ready(function() {
  smartEditor();
});

// 등록 버튼 시 전송
function fn_checkForm(){
	console.log("fn_checkForm");
	//textarea의 값을 가져오기 위해서는 UPDATE_CONTENTS_FIELD 메시지를 호출해주어야 한다.	
	oEditors.getById["modifyTxt"].exec("UPDATE_CONTENTS_FIELD", []); 
	let content = document.getElementById("modifyTxt").value
	
	// 수정된 내용을 가지고 오지 못하고 있음
	console.log("content : ", content);
	
	return false;
	
	// 만약 값이 없으면
/* 	if( content == ""  || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>')  { 
		alert("내용을 입력해주세요");
		oEditors.getById["modifyTxt"].exec("FOCUS");
		return false;
	
	// 값이 있다면
	}else{
		let result = confirm("전송할까요?");
		if(result == true){
			alert("전송하였습니다.");
		}else{
			alert("취소하였습니다.");
			return false;		
		}
	} */
}





</script>
<style type="text/css">
.file_area > div > div:first-child > span{
	float: left;
}
.file_area > div > div:first-child > a{
	float: left;
}
.file_area > div > div:first-child > button{
	float: right;
}
.file_area > div > div:last-child{
	clear:both;
}
.btn_delete{
	float: right;
}
.td_right a{
	color: #166cea;
}
.td_right a:hover{
	color: #ed5422;
}
#div_table table{
	width : 700px
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
                <h1>자유게시판</h1>
            </div>
				<c:if test="${bne ne null or de ne null }">
					<div class="alert alert-warning">
							해당글을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
					</div>	
					<div class="div_button">
	                      <input type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeList'" value="목록">
	                </div>
                </c:if>
            
            	<c:if test="${bne eq null and de eq null }">
            		<form:form name="freeModify" action="${pageContext.request.contextPath }/free/freeModify" 
            					method="post" 
            					modelAttribute="freeBoard"
            					enctype="multipart/form-data"
            					onsubmit="fn_checkForm()">
            	      <div id="div_table">
	                      <table>
	                          <colgroup>
	                              <col width="200">
	                              <col width="400">
	                          </colgroup>
	                          <tr>
	                              <td class="td_left">글번호</td>
	                              <td class="td_right">
	                                  ${freeBoard.boNo }
	                                  <input type="hidden" name="boNo" value="${freeBoard.boNo }">
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">글제목</td>
	                              <td class="td_right">
	                              		<%-- <input type="text" name="boTitle" value="${freeBoard.boTitle }" required="required"> --%>
	                              		<form:input path="boTitle"/>
	                              		<form:errors path="boTitle"/>
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">작성자명</td>
	                              <td class="td_right">
										${freeBoard.boWriter } 
										<input type="hidden" name="boWriter" value="${freeBoard.boWriter }">
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">글 비번</td>
	                              <td class="td_right">
										<!-- <input type="password" name="boPass" value="" autocomplete="off"  required="required" pattern="^\w{4,20}$" title="알파벳과 숫자로 최소4글자 최대20글자 이내 입력"  > -->
	                              	<form:password path="boPass"/>
	                              	<form:errors path="boPass"/>
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">글 분류</td>
	                              <td class="td_right">
	                              		<select name="boCategory"  required="required">
											 <option value="">-- 선택하세요--</option>
											 <c:forEach items="${categoryList }" var="category">
										          <option value="${category.commCd}"  ${freeBoard.boCategory eq category.commCd ? "selected='selected'"  : ""}     >${category.commNm}</option>
											  </c:forEach>

	                              		</select>
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">내용</td>
	                              <td class="td_right">
	                              		<%-- <textarea rows="10" name="boContent" >${freeBoard.boContent }</textarea> --%>
	                              		<textarea 
	                              				name="boContent" 
	                              				id="modifyTxt"
	                              				rows="20" cols="10"
                                  				placeholder="내용을 입력해주세요" 
                                  				>${freeBoard.boContent }</textarea>
	                              		<form:errors path="boContent"/>
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">IP</td>
	                              <td class="td_right">
	                              		${pageContext.request.remoteAddr}
	                              		<input type="hidden" name="boIp" value="${pageContext.request.remoteAddr}">
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">조회수</td>
	                              <td class="td_right">
	                              		${freeBoard.boHit }
	                              </td>
	                          </tr>
	                          <tr>
	                              <td class="td_left">첨부파일
	                              		<button type="button" id="id_btn_new_file">추가</button>
	                              </td>
	                              <td class="td_right file_area">
	                                  <c:forEach items="${freeBoard.attachList }" var="attach" varStatus="status">
	                                  			<div>
	                                  				<div>
	                                  					<span>${status.count } &#46;&nbsp;&nbsp;	<!-- 1부터 시작 --></span>
	                                  					<a href="<c:url value='/attach/download/${attach.atchNo }'/>"
			                                  			target="_blank"> <!-- 해당 a태그 클릭시 새로운 창을 출력합니다. -->
			                                  					${attach.atchOriginalName }
			                                  			</a>
			                                  			<button type="button" class="btn_file_delete" data-atch-no=${attach.atchNo }>삭제</button>
	                                  				</div>
	                                  				<div>
			                                  			&nbsp;&nbsp;&nbsp; 크기 : ${attach.atchConvertSize }, 다운로드 횟수 : ${attach.atchDownHit }
	                                  				</div>
	                                  			</div>
	                                  </c:forEach>
	                                  <div class="form-inline">
	                                  		<input type="file" name="boFiles">
	                                  		<button type="button" class="btn_delete">삭제</button>
	                                  </div>
	                              </td>                          
                          </tr>
	                      </table>
	                  </div>
                  
		                <!-- 버튼 -->
		                <div class="div_button">
		                	<c:if test="${freeBoard.boWriter eq memberVO.memId  }">
			                    <input type="button" onclick="fn_submitCheck()" value="저장">
		                	</c:if>
		                    <%-- <input type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeList.do'" value="목록"> --%>
		                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeList'" value="목록">
		                </div>
                  	</form:form>
             	</c:if>
        </div>
    </div>

<script type="text/javascript">
$("#id_btn_new_file").click(function() {
	$(".file_area").append(
		'<div class="file_div">'
			+'<input type="file" name="boFiles">'
			+'<button type="button" class="btn_delete">삭제</button>'
		+'</div>'
	)
});

$(".file_area").on("click", ".btn_delete", function() {
	$(this).closest('div').remove();
});


$(".btn_file_delete").click( function() {
	console.log("btn_file_delete");
	$(this).closest('.file_area').append('<input type="hidden" name="delAtchNos" value="'+ $(this).data("atch-no") +'">');
	$(this).closest('div').parent().remove();
});
</script>

