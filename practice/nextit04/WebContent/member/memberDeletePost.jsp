<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/memberView.css">
<link rel="stylesheet" type="text/css" href="../css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<%
String memId = request.getParameter("memId");
System.out.println("memId : " + memId);
request.setAttribute("memId", memId);

%>

<script type="text/javascript">
function fn_memberDel(){
	let ret = confirm("정말로 탈퇴하시겠습니까?");
	if(ret){
		/* 비번 확인 */
		let memPass = $("#memPass").val();
		if(memPass == ""){
			alert("비밀번호를 입력하지 않았습니다.")
			return;
		}else{
			let f = document.memberDeleteForm;
			console.log("f : ", f);
			f.action = "${pageContext.request.contextPath}/member/memberDelete.jsp"
			f.submit();
		}
	}
}


</script>


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
                    <h1>회원 탈퇴 확인</h1>
                </div>
                <!-- 회원 정보 테이블 -->  
                <div id="div_table">
                 	<form name="memberDeleteForm" method="post">
                 		<input type="hidden" name="memId" value="${memId}">
                 		<table>
                 			<tr>
                 				<td class="td_left">비밀번호 확인</td>
                 				<td class="td_right">
                 					<input type="password" id="memPass" name="memPass"
                 					value="" pattern=\w{4,} title="알파벳과 숫자4글자 이상입력"/>
                 				</td>
                 			</tr>
                 		</table>
	              	<br>
                 		<div class="div_button">
                 			<input type="button" onclick="fn_memberDel()"
                 			value="탈퇴" >
                 		</div>
                 	</form>
                </div>
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