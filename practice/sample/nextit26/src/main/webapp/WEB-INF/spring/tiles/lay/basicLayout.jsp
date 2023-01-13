<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 타일즈 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="wrap">
	<div class="header">
		<!-- header 영역 -->
		<tiles:insertAttribute name="header"/>
	</div>
	
	<div style="width: 100%; height: 800px; background-color: lightgray;">본문입니다</div>
	
	<div class="footer">
		<!-- footer 영역 -->
		<tiles:insertAttribute name="footer"/>
	</div>
	

</div>

</body>
</html>