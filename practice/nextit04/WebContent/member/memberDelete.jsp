<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.exception.BizNotFoundException"%>
<%@page import="kr.or.nextit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.IMemberService"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="kr.or.nextit.exception.BizPasswordNotMatchedException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/member.css">
<link rel="stylesheet" type="text/css" href="../css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<jsp:useBean id="member" class="kr.or.nextit.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>
<%/* 
Connection conn = null;
PreparedStatement pstmt = null;
try{
	conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
	StringBuffer sb = new StringBuffer();
	sb.append("   update member set 		 	");
	sb.append("    	mem_del_yn ='Y' 			");
	sb.append("   where mem_id = ? 				");

	pstmt = conn.prepareStatement(sb.toString());
	
	System.out.println("member.getMemId()::"+member.getMemId());
	
	pstmt.setString(1, member.getMemId());
	int resultCnt = pstmt.executeUpdate();
	
	if(resultCnt == 0){
		throw new BizNotEffectedException();
	}
	session.removeAttribute("memberVO");
	
}catch(BizNotEffectedException bne){
	request.setAttribute("bne", bne);
	bne.printStackTrace();
}catch(Exception se){
	request.setAttribute("se", se);
	se.printStackTrace();
	
}finally{
	//4??????
	if(pstmt != null){
		try{
			pstmt.close();
		}catch(Exception e){
		}
	}
	if(conn != null){
		try{
			conn.close();
		}catch(Exception e){
		}
	}
} */

%>

	<!-- ???????????? ????????????  -->
	<%
		IMemberService memberService = new MemberServiceImpl();

		try{
			memberService.removeMember(member);
			session.removeAttribute("memberVO");
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

	 
	 
	<c:if test="${ bnf eq null and bpn eq null and bne eq null and de eq null }">
		<h3>???????????? ??????</h3>
		<div>
			<p>?????? ????????? ?????? ???????????????. ????????? ???????????? ????????? ???????????? ???????????????.</p>
			<div class="btn-area">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/login/login.jsp'">??????</button>
			</div>
		</div>
	</c:if>

	<c:if test="${ bpn ne null}">
		<h3>???????????? ??????</h3>
		<div>
			<p>???????????? ??????????????? ???????????? ????????????. ?????? ?????? ??????????????????.</p>
			<div class="btn-area">
				<button type="button" onclick="history.back();">????????????</button>
			</div>
		</div>
	</c:if>

	<c:if test="${ bnf ne null or bne ne null or de ne null  }">
		<h3>???????????? ??????</h3>
		<div>
			<p> ?????? ????????? ?????????????????????. ???????????? ?????? ??????????????????. 042-719-8850</p>
			<div class="btn-area">
				<button type="button" onclick="history.back();">????????????</button>
			</div>
		</div>
	</c:if>
	 
	 
	 
</div>
</body>
</html>