<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"
      %>
<%	request.setCharacterEncoding("utf-8");  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
*{
    margin: 0 auto;
    padding: 0;
}
body{
    height: 100vh;
    background-color: red;
    text-align: center;
}
.container{
	position: fixed;
    top: 50%;
    left: calc( 50% - 300px);
    background-color: lightskyblue;
}

.container h1{
	color:#166cea
}
.container h3, .container h2, .alert-success {
    color: white;
}

body{
    background: url("${pageContext.request.contextPath}/images/img1.jpg") no-repeat center;
    background-size: cover;
}
body::before{
    content: "";
    position: absolute;
    top: 0; right: 0; bottom: 0; left: 0;
    background-color: rgba(0, 0, 0, 0.7);
}
body, .container{
    background-color: transparent;
}
p {
    color:white;
}

.btn-area{
    width: 420px;
}
.container button{
    width: 100%;
    height: 50px;
    color: #fff;
    font-size: 20px;
    border: none;
    border-radius: 25px;
    background-color: #166cea;
    margin-top: 30px;
}
.container button:active{
	color: gray;
}
.mainLink{
	width:600px;
	height: 100px;
}
.err_info{
	width:600px;
    text-align: justify;
}
</style>
</head>
<body>
<div class="container">
	<div class="mainLink">
		<h1>NextIT</h1>
	</div>
	
	<div class="err_info">
		<h2>(403)원하시는 페이지를 찾을 수가 없습니다.</h2>
		<p>
			방문 원하시는 페이지의 주소가 잘못 입력되었거나,
			변경 혹은 삭제되어 요청하신 페이지를 찾을 수가 없습니다.
			입력하신 페이지의 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.
			문의사항은 전신살 042-719-8850으로 문의 부탁드립니다.
		</p>
	</div> 

	<div class="btn-area">
		<button type="button" onclick="history.back();">뒤로가기</button>
	</div>
</div>
</body>
</html>