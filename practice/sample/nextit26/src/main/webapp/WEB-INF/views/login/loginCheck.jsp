<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/loginCheck.css">
</head>
<body>

<div class="container">
		<c:if test="${bne ne  null or de ne null }">
			<h3>로그인 처리 실패</h3>
			<div class="alert alert-warning">
				<p> 로그인 처리 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
				<div class="btn-area">
					<button type="button" onclick="history.back();">뒤로가기</button>
				</div>
			</div>
		</c:if>
</div>
</body>
</html>