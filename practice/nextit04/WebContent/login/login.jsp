<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/login.css">



<%
String msg = request.getParameter("msg");
System.out.println("msg : "+ msg);

if(msg != null && msg.equals("null")){
	out.print("<script>");
	out.print("alert('ID 또는 PASSWORD를 입력해주세요')");
	out.print("</script>");
}else if(msg != null && msg.equals("fail")){ 
	out.print("<script>");
	out.print("alert('로그인 실패 하였습니다.ID또는 PASSWORD를 확인해주세요')");
	out.print("</script>");
}else if(msg != null && msg.equals("none")){ 
	out.print("<script>");
	out.print("alert('로그인 하셔야 이용가능합니다.')");
	out.print("</script>");
}



Cookie[] cookies = request.getCookies();
if(cookies !=null && cookies.length>0){
	for(int i=0; i<cookies.length; i++){
		if(cookies[i].getName().equals("rememberMe")){
			request.setAttribute("check", "checked");
			request.setAttribute("memId", cookies[i].getValue()	);
		}
	}
}


%>


<script type="text/javascript">
function fn_login(){
	alert("fn_login");
	let f = document.loginForm;
	f.action="${pageContext.request.contextPath}/login/loginCheck.jsp";
	f.submit();
}


</script>
</head>
<body>
<section class="login_form">
    <h1>NextIT</h1>
    <form name="loginForm" action="" method="post">
        <div class="int-area">
            <input type="text" id="memId" name="memId" value="${memId }" autocomplete="off" required>  
            <label for="memId">USER ID</label>
        </div>
        <div class="int-area">
            <input type="password" id="memPass" name="memPass" autocomplete="off" required>
            <label for="memPass">PASSWORD</label>
        </div>
        <div class="div_rememberMe">
      		<label for="rememberMe">
				<input type="checkbox" id="rememberMe" name="rememberMe"  value="Y"  ${check} />&nbsp;&nbsp;ID 기억하기
			</label>
        </div>
        <div class="btn-area">
            <button type="button" id="btn_id" name="btn_id" onclick="fn_login()">LOGIN</button>
        </div>
    </form>
    <div class="caption">
        <ul class="caption_ul">
            <li>
                <a href="#" onclick="location.href='${pageContext.request.contextPath}/login/join.jsp'">join</a>
            </li>
            <li>
                <a href="#" onclick="">FORGOT PASSWORD</a>
            </li>
        </ul>
    </div>
</section> 
</body>
</html>