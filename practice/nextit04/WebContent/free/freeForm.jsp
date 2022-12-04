<%@page import="kr.or.nextit.code.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.nextit.code.service.CommCodeServiceImpl"%>
<%@page import="kr.or.nextit.code.service.ICommCodeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/freeBoardView.css">
<link rel="stylesheet" type="text/css" href="../css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<%
ICommCodeService codeService = new CommCodeServiceImpl();
List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
request.setAttribute("categoryList", categoryList);
%>

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
                <h1>자유게시판</h1>
            </div>
            <form id="freeForm" action="${pageContext.request.contextPath }/free/freeRegister.jsp" 
            			method="post">
                  <div id="div_table">
                      <table>
                          <colgroup>
                              <col width="200">
                              <col width="400">
                          </colgroup>
                          <tr>
                              <td class="td_left">제목</td>
                              <td class="td_right">
                                  <input type="text" name="boTitle" value="" autocomplete="off" 
                                  		required="required">                               
                         		</td>
                          </tr>
                          <tr>
                              <td class="td_left">작성자</td>
                              <td class="td_right">
                              			<input type="hidden" name ="boWriter" value="${memberVO.memId }">
        										<c:out value="${memberVO.memId }"/>	                      				
									
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 비번</td>
                              <td class="td_right">
                              <input type="password" id="boPass" name="boPass" autocomplete="off" 
                              				value="" pattern=\w{4,20} title="알파벳과 숫자4글자 이상 20자 이내 입력"/>
                              				수정 또는 삭제시 필요합니다.
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">글 분류</td>
                              <td class="td_right">
                              	<select name="boCategory"  required="required">
											<!-- <option value="">-- 선택하세요--</option>					
											<option value="BC01">프로그램</option>
											<option value="BC02">웹</option>
											<option value="BC03">사는 이야기</option>
											<option value="BC04">취업</option> -->
											
											<c:forEach items="${categoryList}" var="category">
											   <option value="${category.commCd }">${category.commNm }</option>
											</c:forEach>
											
										</select>
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">IP</td>
                              <td class="td_right">
                              		<c:out value="${pageContext.request.remoteAddr }"/>
                              		<input type="hidden" name='boIp' value="${pageContext.request.remoteAddr }">
                              				
                              </td>
                          </tr>
                          <tr>
                              <td class="td_left">내용</td>
                              <td class="td_right">
                        					<textarea rows="10" name="boContent" required="required"></textarea>
                              </td>
                          </tr>
                      </table>
                  </div>
                  <!-- 버튼 -->
                  <div class="div_button">
                       <input type="button" 
                       	onclick="location.href='${pageContext.request.contextPath}/free/freeList.jsp'" 
                       	value="목록">
                      <input type="submit" value="등록">
                  </div>
       		</form>
        </div>
    </div>

	<!-- footer -->
	<footer id="page_footer">
		<!-- footer영역 -->
		<%@ include file="/footer/footer.jsp" %>
    </footer>
</div>  
</body>
</html>