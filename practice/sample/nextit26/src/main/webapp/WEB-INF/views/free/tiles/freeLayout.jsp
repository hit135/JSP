<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 타일즈 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

	<div id="wrap">
		<div class="header">
			<div class="top_nav">
				<!-- header 영역 -->
				<tiles:insertAttribute name="header"/>
			</div>

		</div>
	</div>
	
	<tiles:insertAttribute name="body"/>
	
	<div id="page_footer">
		<!-- footer 영역 -->
		<tiles:insertAttribute name="footer"/>
	</div>
	



</body>
</html>