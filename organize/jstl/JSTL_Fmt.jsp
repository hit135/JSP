<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	p {
		color: blue;
		font-weight: bold;
	}
</style>
</head>
<body>

<!-- 우리 브라우저가 한글이어서 message_ko에 찾아들어가서 한글 출력 -->
<!-- 환경설정에 Build Path를 보면 src까지 경로가 잡혀있다 -->
<fmt:setLocale value="en"/>
<fmt:bundle basename="resource.loginInfo">
	<fmt:message key="id"/>
</fmt:bundle>

<hr>
<p>변수에 담기</p>
<fmt:setBundle basename="resource.loginInfo" var="loginInfo01" />
<fmt:message bundle="${loginInfo01 }" key="id"/>

<hr>
<p> 담고 또 변수에 담기</p>
<fmt:setBundle basename="resource.loginInfo" var="loginInfo02" />
<fmt:message bundle="${loginInfo02 }" key="id" var="loginId"/>
아이디 : ${loginId }

<hr>
<p>설정해놓은 파라미터에 담기</p>
<fmt:setBundle basename="resource.loginInfo" var="loginInfo03" />
<fmt:message bundle="${loginInfo03 }" key="id" var="logInfo"/>
<c:set var="memId" value="${logInfo }"/>
<fmt:message bundle="${loginInfo03 }" key="info">
	<fmt:param value="${memId}"/>
</fmt:message>

<hr>
<p>국제화! 다른 언어로 바꾸기</p>
<!-- message_en이 없으니 message에서 찾는다 -->
<fmt:setLocale value="ko"/>
<fmt:setBundle basename="resource.loginInfo" var="loginInfo_ko" />
<fmt:message bundle="${loginInfo_ko }" key="id"/>

<hr>
<!-- <pattern 규칙>
# : 채워야 할 자리에 비해서 데이터가 모자라면 공백으로 표시 자리수 보다 수치 데이터가 길 경우 
      자리수 만큼만 출력
0 : 빈 자리 만큼 0으로 채운다. -->
<!-- 숫자! -->
<p>숫자 pattern 규칙</p>
<fmt:formatNumber value="1234567.89"/><br><br>	 <!-- 1,234,567.89  -->
<fmt:formatNumber value="0.5" type="percent"/><br><br>	<!-- 50%  -->
<fmt:formatNumber value="10000" type="currency"  currencySymbol="￦"/><br><br>	 <!-- ₩10,000 --> 
<fmt:formatNumber value="10000" type="currency" currencySymbol="$" /><br><br>	 <!-- $10,000 --> 
<fmt:formatNumber value="1234567.8912345" pattern="#,#00.0#" /><br><br>	 <!-- 1,234,567.89  -->
<fmt:formatNumber value="1234567.8" pattern="#,#00.0#" /><br><br>	 <!-- 1,234,567.8 --> 
<fmt:formatNumber value="1234567.89" pattern=".000" /><br><br>	<!-- 1234567.890 -->
<hr>

<!-- 시간포멧!  -->
<p>시간 pattern 규칙</p>
<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
현재날짜와 시간 : ${now }<br><br>
no_option : <fmt:formatDate value="${now }" /><br><br>
default : <fmt:formatDate value="${now }" type="both" dateStyle="default" timeStyle="default" /><br><br>
short : <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short" /><br><br>
medium : <fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="medium" /><br><br>
long : <fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long" /><br><br>
full : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /><br><br>
패턴 적용 날짜1 : <fmt:formatDate value="${now }" pattern="yyyy년MM월dd일 hh시mm분ss초" /><br><br>
패턴 적용 날짜2 : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm:ss" /><br><br>
<hr>



</body>
</html>