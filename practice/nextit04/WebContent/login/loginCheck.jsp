<%@page import="kr.or.nextit.exception.DaoException"%>
<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="kr.or.nextit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.nextit.member.service.IMemberService"%>
<%@page import="kr.or.nextit.member.vo.MemberVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/loginCheck.css">
</head>
<body>
<%
/* 
String memId = request.getParameter("memId");
String memPass = request.getParameter("memPass");
String rememberMe = request.getParameter("rememberMe");
System.out.println("memId :" + memId);

if(memId =="" || memPass == ""){
	response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=null");
}else{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	try{
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb1 = new StringBuffer();
		sb1.append(" SELECT 					");
		sb1.append("mem_id     	             ");
		sb1.append(",mem_pass   	             ");
		sb1.append(",mem_name   	             ");
		sb1.append(",mem_bir   	             ");
		sb1.append(",mem_zip   	             ");
		sb1.append(",mem_add1   	             ");
		sb1.append(",mem_add2   	             ");
		sb1.append(",mem_hp   	             	");
		sb1.append(",mem_mail   	             ");
		sb1.append(",mem_job   	             ");
		sb1.append(",mem_hobby                	");
		sb1.append(",mem_mileage              	");
		sb1.append(",mem_del_yn               	");
		sb1.append(",mem_join_date            	");
		sb1.append(",mem_edit_date            	");
		sb1.append(" FROM MEMBER WHERE mem_id = ? and mem_del_yn ='N' ");
		
		pstmt = conn.prepareStatement(sb1.toString());
		pstmt.setString(1, memId);
		rs = pstmt.executeQuery();
		
		MemberVO member = null;
		
		if(rs.next()){
			member = new MemberVO();
			member.setMemId(rs.getString("mem_id"));
			member.setMemPass(rs.getString("mem_pass"));
			member.setMemName(rs.getString("mem_name"));
			member.setMemBir(rs.getString("mem_bir"));
			member.setMemZip(rs.getString("mem_zip"));
			member.setMemAdd1(rs.getString("mem_add1"));
			member.setMemAdd2(rs.getString("mem_add2"));
			member.setMemHp(rs.getString("mem_hp"));
			member.setMemMail(rs.getString("mem_mail"));
			member.setMemJob(rs.getString("mem_job"));
			member.setMemHobby(rs.getString("mem_hobby"));
			member.setMemMileage(rs.getInt("mem_mileage"));
			member.setMemDelYn(rs.getString("mem_del_yn"));
			member.setMemJoinDate(rs.getString("mem_join_date"));
			member.setMemEditDate(rs.getString("mem_edit_date"));
			
			System.out.println("member.toString() : "+ member.toString());
		}
		
		
		if(member == null){
			System.out.println("meber 정보를 불러오지 못하였습니다" );
			response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=fail");
		}else{
			if(member.getMemPass().equals(memPass)){
				System.out.println("success login" );
				session.setAttribute("memberVO", member);
				
				if(rememberMe != null && rememberMe.equals("Y")){
					System.out.println("rememberMe is Y" );
					
					Cookie cookie = new Cookie("rememberMe", memId);
					response.addCookie(cookie);
				}else{
					Cookie cookie = new Cookie("rememberMe", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				//로그인 성공 후 메인 화면으로이동
				response.sendRedirect(request.getContextPath()+"/home/home.jsp");
				
			}else{
				response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=fail");
			}
		}
	}catch(Exception exc){
		request.setAttribute("exc",  exc);
		exc.printStackTrace();
	}finally{
		if(rs!=null){try{rs.close();}catch(Exception e){}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
		if(conn!=null){try{conn.close();}catch(Exception e){}}
	}
} */

%>

<jsp:useBean id="member" class="kr.or.nextit.member.vo.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>
<%
IMemberService memberService = new MemberServiceImpl();
try{
	memberService.loginCheck(member,request, response );
	
}catch(BizNotEffectedException bne){
	request.setAttribute("bne", bne);
}catch(DaoException de){
	request.setAttribute("de", de);
}
%>

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