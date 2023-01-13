<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function fn_login(){
	//alert("fn_login");
	let f = document.loginForm;
	f.action="${pageContext.request.contextPath}/login/loginCheck";
	f.submit();
}
$(function(){
	window.setTimeout(function(){
		let lc = $("#loginCheck").val();
		if(lc == 'fail'){
			alert("로그인 실패하였습니다. ID 또는 PASSWORD를 확인해주세요!");
		}else if(lc == 'none'){
			alert("로그인 하셔야 이용가능합니다.");
		}else if(lc == 'error'){
			alert("처리 도중 에러가 발생하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
		}else if(lc == 'sign'){
			alert("정상적으로 회원등록이 되었습니다. 확인을 누르시고 로그인을 진행주세요");
		}else if(lc == 'quit'){
			alert("회원탈퇴 되었습니다. 다시 가입을 원하시면 join을 눌러주세요");
		}
		
	}, 200 );
});
</script>
</head>
<body>

<input type="hidden" id="loginCheck" value="${msg }">

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
                <%-- <a href="#" onclick="location.href='${pageContext.request.contextPath}/login/join.do'">join</a> --%>
                <a href="#" onclick="location.href='${pageContext.request.contextPath}/login/join'">join</a>
            </li>
            <li>
                <a href="#" onclick="">FORGOT PASSWORD</a>
            </li>
        </ul>
    </div>
</section> 
</body>
</html>