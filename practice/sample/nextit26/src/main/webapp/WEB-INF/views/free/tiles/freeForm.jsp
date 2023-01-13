<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/freeBoardView.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/smarteditor2-2.8.2.3/js/HuskyEZCreator.js"></script>
<script type="text/javascript">

// 스마트 에디터 1차
let oEditors = [];
smartEditor = function() {
  console.log("Naver SmartEditor")
  nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "editorTxt",
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
	oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", []); 
	let content = document.getElementById("editorTxt").value
	
	// 만약 값이 없으면
	if( content == ""  || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>')  { 
		alert("내용을 입력해주세요");
		oEditors.getById["editorTxt"].exec("FOCUS");
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
	}
}



</script>
<style type="text/css">
/*삭제버튼 css*/
.btn_delete{
	float: right;
}
/* 스마트 에디터 2차 */
.td_right{
	width:600px;
}
#editorTxt{
	width: 595px;
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
            <!-- 입력양식 -->
            <form:form id="freeForm" 
            			action="${pageContext.request.contextPath }/free/freeRegister" 
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
                              <td class="td_left">제목</td>
                              <td class="td_right">
                                  <!-- <input type="text" name="boTitle" value="" autocomplete="off" required="required" > -->
                                  <form:input path="boTitle"/>
                                  <form:errors path="boTitle"/>
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">작성자</td>
                              <td class="td_right">
                                  <input type="hidden" name="boWriter" value="${memberVO.memId}" >
                                  <!-- 세션에서 정보 취득 -->
                                  <c:out value="${memberVO.memId}"/> 
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 비번</td>
                              <td class="td_right">
                                  <!-- <input type="password" name="boPass" value="" autocomplete="off"  required="required" pattern="^\w{4,20}$" title="알파벳과 숫자로 최소4글자 최대20글자 이내 입력"  > -->
		  							 <form:password path="boPass"/>
		  							 <form:errors path="boPass"/><br>
		  							 수정 또는 삭제시에 필요합니다. 
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 분류</td>
                              <td class="td_right">
                          		<select name="boCategory"  required="required">
									<option value="">-- 선택하세요--</option>
									<c:forEach items="${categoryList}" var="category">
										<option value="${category.commCd }">${category.commNm }</option>
									</c:forEach>
								</select>	
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
                              <td class="td_left">내용</td>
                              <td class="td_right">
                                  <!-- <textarea rows="10" name="boContent" required="required"></textarea> -->
                                  <%-- <form:textarea rows="10" path="boContent"/> --%>
                                  	<!-- 텍스트 에이리어 대신 스마트 에디터! -->
                                  	<textarea name="boContent" id="editorTxt" rows="20" cols="10"
                                  				placeholder="내용을 입력해주세요" ></textarea>
                                  
                                  
                                  <form:errors path="boContent"/>
                              </td>
                          </tr>
                          
                        <tr>
                        		<td class="td_left">첨부파일
                        			<button type="button" id="id_btn_new_file">추가</button>
                        		</td>
                        		<td class="td_right file_area">
                        			<div class="file_div">
                        				<input type="file" name="boFiles">
                        				<button type="button" class="btn_delete">삭제</button>
                        			</div>
                        		</td>
                          </tr>
                          
                          
                          
                          
                      </table>
                  </div>
                  <!-- 버튼 -->
                  <div class="div_button">
                      <input type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeList'" value="목록">
                      <input type="submit" value="등록">
                  </div>.
       		</form:form>
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

</script>