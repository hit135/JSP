<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String memId = request.getParameter("memId");
String memPass = request.getParameter("memPass");
System.out.println(memId);
System.out.println(memPass);

request.setAttribute("memId", memId);
request.setAttribute("memPass", memPass);

%>
<c:choose>
	<c:when test="${memId != '' && memPass != ''}">
	<p>로그인 되었습니다</p>
	${memId }님 환영합니다
	</c:when>
	<c:otherwise>
	<!-- pageContext.request.contextPath >> 경로 -->
	<script type="text/javascript">
	alert("회원가입에 실패했습니다..");
	location.href = "${pageContext.request.contextPath}/practice/scope/Scope01.jsp";
	</script>
	</c:otherwise>
</c:choose>
















</body>
</html>