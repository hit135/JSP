<%@page import="kr.or.nextit.exception.BizNotEffectedException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/memberView.css">
<link rel="stylesheet" type="text/css" href="../css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 
 
<script type="text/javascript">
function fn_memberModify(){
	let ret = confirm("저장하시겠습니까?");
	if(ret){
		let memPassNew = $("#memPassNew").val();
		let memPassNewCheck = $("#memPassNewCheck").val();
		if( memPassNew =="" || memPassNewCheck ==""){
			alert("신규 비밀번호가 입력되지 않았습니다.");
			return;
		}
		if(memPassNew != memPassNewCheck){
			alert("신규 비밀번호가 일치 하지 않습니다.");
			$("#memPassNew").val("");
			$("#memPassNewCheck").val("");
			return;
		}else{
			let f = document.memberModifyForm;
			console.log("f", f);
			f.action = "${pageContext.request.contextPath}/member/memberModify.jsp";
			f.submit();
		}
	}
}

</script> 
 
 
 
 <%
String memId = request.getParameter("memId");
System.out.println("memId : "+ memId);

MemberVO mem = (MemberVO)session.getAttribute("memberVO");

if(memId != null && mem !=null && memId.equals(mem.getMemId())){
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
		StringBuffer sb = new StringBuffer();
		sb.append(" select ");
		sb.append(" mem_id     	             ");
		sb.append(" ,mem_pass   	             ");
		sb.append(" ,mem_name   	             ");
		sb.append(" ,mem_bir   	             ");
		sb.append(" ,mem_zip   	             ");
		sb.append(" ,mem_add1   	             ");
		sb.append(" ,mem_add2   	             ");
		sb.append(" ,mem_hp   	             	");
		sb.append(" ,mem_mail   	             ");
		sb.append(" ,mem_job   	             ");
		sb.append(" ,mem_hobby                	");
		sb.append(" ,mem_mileage              	");
		sb.append(" ,mem_del_yn               	");
		sb.append(" from member 					");
		sb.append(" where mem_id = ? 			");
		
		pstmt = conn.prepareStatement(sb.toString());
		pstmt.setString(1, memId);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			System.out.println("find member");
			
			MemberVO member = new MemberVO();
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
			
			request.setAttribute("member", member);
		}
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null){try{rs.close();}catch(Exception e){}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
		if(conn!=null){try{conn.close();}catch(Exception e){}}
	}
	
	
	
}else{
	System.out.println("memId and mem are not the same");
	BizNotEffectedException bne = new BizNotEffectedException();
	request.setAttribute("bne", bne);
}


%>
 
</head>
<body>


<div id="wrap">
	 <div class="header">
         <div class="top_nav">
             <!-- header 영역 -->
             <%@ include file="/header/header.jsp" %>
             
         </div>
     </div>
     <!-- header e -->

     <div class="intro_bg">
         <div class="intro_text">
             <h1>NextIT</h1>
             <h4>넥스트아이티</h4>
         </div>
     </div>
     <!-- intro_bg e -->

     <!-- 전체 영역잡기 -->
     <div class="contents">
         <!-- 사용할 영역잡기 -->
         <div class="content01">
             <div class="content01_h1">
                 <h1>회원 정보 상세</h1>
             </div>
             <!-- 회원 정보 테이블 -->
             <div id="div_table">
             
             	<c:choose>
             		<c:when test="${bne ne null }">
             			<h3>회원 정보 조회 실패</h3>
						<div class="alert alert-success">
							<p>회원 정보 조회 실패 하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850</p>
							<div class="btn-area">
								<button type="button" onclick="">뒤로가기</button>
							</div>
						</div>
             		</c:when>
             		<c:when test="${bne eq null }">
             			 	<form name="memberModifyForm" method="post">
             			 		<input type="hidden" name="memId" value="${member.memId }">
									<table>
										<tr>
   											<td class="td_left" >아이디</td>
   											<td class="td_right" ><c:out value="${member.memId }"></c:out></td>
   										</tr>
   										<tr>
   											<td class="td_left">기존 비밀번호</td>
   											<td class="td_right">
   												<input type="password" id="memPass" name="memPass" value="" pattern=\w{4,} title="알파벳과 숫자4글자 이상입력"/>
   											</td>
   										</tr>
   										<tr>
   											<td class="td_left">신 비밀번호</td>
   											<td class="td_right">
   												<input type="password" id="memPassNew" name="memPassNew" value="" 
   														pattern=\w{4,} title="알파벳과 숫자4글자 이상입력"/>
   											</td>
   										</tr>
   										<tr>
   											<td class="td_left">신 비밀번호</td>
   											<td class="td_right">
   												<input type="password" id="memPassNewCheck" name="memPassNewCheck" 
   														value="" pattern=\w{4,} title="알파벳과 숫자4글자 이상입력"/>
   											</td>
   										</tr>
     									<tr>
   											<td class="td_left" >회원명</td>
   											<td class="td_right" >
   												<input type="text" name="memName" value="${member.memName }"
													pattern="[가-힣]{2,}" title="한글로 2글자이상입력" required="required"/>
   											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >우편번</td>
   											<td class="td_right" >
												<input type="text" name="memZip" value="${member.memZip }">
											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >주소</td>
   											<td class="td_right" >
   												<input type="text" name="memAdd1" value="${member.memAdd1 }">
   												<input type="text" name="memAdd2" value="${member.memAdd2 }">
   										</tr>
   										<tr>
   											<td class="td_left" >생일</td>
   											<td class="td_right" >
   												<input type="date" name="memBir" 
   													value="${fn:substring( member.memBir, 0, 10) }">
											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >메일</td>
   											<td class="td_right" >
   												<input type="email" name="memMail" value="${member.memMail }" 
   													required="required">
											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >핸드폰</td>
   											<td class="td_right" >
   												<input type="tel" name="memHp" value="${member.memHp }">
											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >직업</td>
   											<td class="td_right" >
												<select name="memJob" required="required">
													<option value="">-- 직업 선택 --</option>
													<option value="JB01" ${member.memJob eq "JB01" ? "selected='selected'" : "" } >주부</option>
													<option value="JB02" ${member.memJob eq "JB02" ? "selected='selected'" : "" }>은행원</option>
													<option value="JB03" ${member.memJob eq "JB03" ? "selected='selected'" : "" }>공무원</option>
													<option value="JB04" ${member.memJob eq "JB04" ? "selected='selected'" : "" }>축산업</option>
													<option value="JB05" ${member.memJob eq "JB05" ? "selected='selected'" : "" }>회사원</option>
													<option value="JB06" ${member.memJob eq "JB06" ? "selected='selected'" : "" }>농업</option>
													<option value="JB07" ${member.memJob eq "JB07" ? "selected='selected'" : "" }>자영업</option>
													<option value="JB08" ${member.memJob eq "JB08" ? "selected='selected'" : "" }>학생</option>
													<option value="JB09" ${member.memJob eq "JB09" ? "selected='selected'" : "" }>교사</option>					
												</select>
   											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >취미</td>
   											<td class="td_right" >
   													<select name="memHobby" required="required">
														<option value="">-- 취미 선택 --</option>
														<option value="HB01" ${member.memHobby eq "HB01" ? "selected='selected'" : "" } >서예</option>
														<option value="HB02" ${member.memHobby eq "HB02" ? "selected='selected'" : "" } >장기</option>
														<option value="HB03" ${member.memHobby eq "HB03" ? "selected='selected'" : "" } >수영</option>
														<option value="HB04" ${member.memHobby eq "HB04" ? "selected='selected'" : "" } >독서</option>
														<option value="HB05" ${member.memHobby eq "HB05" ? "selected='selected'" : "" } >당구</option>
														<option value="HB06" ${member.memHobby eq "HB06" ? "selected='selected'" : "" } >바둑</option>
														<option value="HB07" ${member.memHobby eq "HB07" ? "selected='selected'" : "" } >볼링</option>
														<option value="HB08" ${member.memHobby eq "HB08" ? "selected='selected'" : "" } >스키</option>
														<option value="HB09" ${member.memHobby eq "HB09" ? "selected='selected'" : "" } >만화</option>
														<option value="HB10" ${member.memHobby eq "HB010" ? "selected='selected'" : "" } >낚시</option>
														<option value="HB11" ${member.memHobby eq "HB011" ? "selected='selected'" : "" } >영화감상</option>
														<option value="HB12" ${member.memHobby eq "HB012" ? "selected='selected'" : "" } >등산</option>
														<option value="HB13" ${member.memHobby eq "HB013" ? "selected='selected'" : "" } >개그</option>
														<option value="HB14" ${member.memHobby eq "HB014" ? "selected='selected'" : "" } >카레이싱</option>						
													</select>
   											
   											</td>
   										</tr>
   										<tr>
   											<td class="td_left" >마일리지</td>
   											<td class="td_right" ><c:out value="${member.memMileage }"></c:out></td>
   										</tr>	
									</table>   
									<div class="div_button">
										<input type="button" 
											onclick="location.href='${pageContext.request.contextPath}/home/home.jsp'"
											value="홈">
										<input type="button" onclick="fn_memberModify()" value="저장">
										<input type="button" onclick="" value="취소">
									</div>
																				
									
									          			 	
             			 	</form>
	                 </c:when>
             	</c:choose>
             </div>
         </div>
     </div>

     <!-- footer -->
     <footer id="page_footer">
         <%@ include file="/footer/footer.jsp" %>
     </footer>

</div>    
</body>
</html>