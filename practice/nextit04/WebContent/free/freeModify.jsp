<%@page import="kr.or.nextit.exception.BizNotFoundException"%>
<%@page import="kr.or.nextit.exception.BizPasswordNotMatchedException"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.free.service.IFreeBoardService"%>
<%@page import="kr.or.nextit.free.service.FreeBoardServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/freeBoard.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>


<jsp:useBean id="freeBoard" class="kr.or.nextit.free.vo.FreeBoardVO"></jsp:useBean>
<jsp:setProperty property="*" name="freeBoard"/>

<%
	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	try{
		freeBoardService.modifyBoard(freeBoard);
	}catch(BizNotFoundException bnf){
		request.setAttribute("bnf", bnf);
		bnf.printStackTrace();
	}catch(BizPasswordNotMatchedException bpn){
		request.setAttribute("bpn", bpn);
		bpn.printStackTrace();
	}catch(BizNotEffectedException bne){
		request.setAttribute("bne", bne);
		bne.printStackTrace();
	}catch(DaoException de){
		request.setAttribute("de", de);
		de.printStackTrace();
	}
%>

<div class="container">

	<c:if test="${bnf eq null and bpn eq null and bne eq null and de eq null }">
		<h3>게시글 수정 성공</h3>
		<div class="alert alert-success">
			<p>정상적으로 게시글이 수정되었습니다. 확인을 클릭하시면 목록페이지로 이동하며 , 해당 뷰 버튼을 클릭하시면 해당 게시글으로 이동합니다.</p>
			<div class="btn-area">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeList.jsp'">확인</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/free/freeView.jsp?boNo=${freeBoard.boNo }'">해당 뷰</button> 
			</div>
		</div>
	</c:if>

	<c:if test="${bpn ne null}">
		<h3>게시글 수정 실패</h3>
		<div class="alert alert-warning">
			<p> 입력하신 비밀번호가 맞지 않습니다. 다시 확인해 주세요</p>
			<div class="btn-area">
				<button type="button" onclick="history.back();">뒤로가기</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/home/home.jsp'">홈</button>
			</div>
		</div>
	</c:if>
	
	<c:if test="${bnf ne null or bne ne null or de ne null}">
		<h3>게시글 수정 실패</h3>
		<div class="alert alert-warning">
			<p> 게시글 수정에 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
			<div class="btn-area">
				<button type="button" onclick="history.back();">뒤로가기</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/home/home.jsp'">홈</button>
			</div>
		</div>
	</c:if>
</div>

</body>
</html>