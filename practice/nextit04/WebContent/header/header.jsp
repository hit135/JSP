<%@page import="kr.or.nextit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
if(memberVO == null){
	request.setAttribute("loginState", "none");
}

%>
<script>
$(function(){
	//alert("header jquery");
	let loginState = $("#loginState").val();
	if(loginState == "none"){
		alert("로그인 하셔야 이용 가능합니다.");
		location.href="${pageContext.request.contextPath}/login/login.jsp";
	}
	
})
</script>


<input type="hidden" id="loginState" value="${loginState }">

<div class="searchArea">
	<form action="">
		<input type="text" id="input_search" name="input_search"
		 value="" placeholder="Search">
		<span>검색</span>
	</form>
</div>
<ul class="nav">
	<li><a href="">HOME</a></li>
	<li><a href="#about">ABOUT</a></li>
	<li><a href="#service">SERVICE</a></li>
	<li><a href="#content">CONTENT</a></li>
	<li><a href="">FREEBOARD</a></li>
	<li><a href="">${memberVO.memId }</a></li>
</ul>