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
<!-- pageContext 에서 여러 객체를 취득 해보기 -->
<%
/* 형변환을 시켜줘야 한다.......  */
HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();

HttpServletResponse httpResponse = (HttpServletResponse)pageContext.getResponse();

/* 세션 객체 취득 */
HttpSession httpSession = pageContext.getSession();

/* 어플리케이션 객체 취득 */
ServletContext servletContext = pageContext.getServletContext();
%>

현재 프로젝트 루트 경로 : ${pageContext.request.contextPath }
<hr>
서버에서 jsp내장을 못 쓰기 때문에 자바에서 HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest(); <br>
이런 식으로 얻어줘야 한다<br><br><hr>
<!-- pageContext에서 취득한 request랑.. JSP 내장 request랑 비교 -->
<c:if test="${httpRequest == request }">
	동일한 request 객체 입니다
</c:if>

<hr>
<c:if test="${httpResponse == response }">
	동일한 response 객체 입니다
</c:if>

<hr>
<c:if test="${httpSession == session }">
	동일한 session 객체 입니다
</c:if>

<hr>
<c:if test="${servletContext == application }">
	동일한 application 객체 입니다
</c:if>

<hr>
1. request에 담아보기,, request의 범위
<%
request.setAttribute("str", "화이팅");
/* 최상위 객체인 object로 반환하기 때문에 형변환 해줘야 한다. */
String str1 = (String)request.getAttribute("str") + "하자!";
System.out.println(str1);
request.setAttribute("str1", str1);
%>
<br>
${str }<br>
${str1 }
- request는 요청하고 응답하는 동안 유지된다!!<br>
<hr>
<h3>다른 jsp로 값 넘기기</h3>
<p>로그인</p>
<p>get방식은 url에 다 남으니까 post방식을 써야지</p>
<p>그리고 인풋할 때 이름 설정해주는게 아주아주 중요하다! >> 그래야 다른 jsp페이지에서 접근 가능</p>
<form action="./Scope02.jsp" method="post">
	<input type="text" name="memId">
	<input type="text" name="memPass">
	<input type="submit" value="전송">
</form>













</body>
</html>