<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.nav a:hover {
    color: lime;
} 
/* dropDown */
.nav li:last-child{
    width: 200px;
}
.nav li ul{
    display: none;
    opacity: 0;
    margin-left: 10px;
}
.nav li:hover ul{
    animation: fade-in .5s;
    animation-fill-mode: forwards;
    display: block;
  	position: relative;
}
.nav li:hover ul > li{
	position: absolute;
	width:70px;
	/* background-color: red; */
	margin-left : 7px;
}
.nav li:hover ul > li:nth-child(3){
	left:150px;
}
.nav li:hover ul > li:nth-child(2){
	left:70px;
}
@keyframes fade-in {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
}
.nav a{
    color: black;
    font-size: 18px;
    margin-top: 5px;
}
</style>

<script>
function fn_logout(){
	console.log("fn_logout");
	let ret = confirm("로그아웃하시겠습니까?");
	if( ret == true ){
		location.href ="${pageContext.request.contextPath}/logout";
	}
}
</script>
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
	<li><a href="${pageContext.request.contextPath }/free/freeList">FREEBOARD</a></li>
	<li><a href="#">${memberVO.memId }</a>
		<ul>
			<li><a href="${pageContext.request.contextPath }/member/memberView?memId=${memberVO.memId }">info</a></li>
			<li><a href="#" onclick="fn_logout()">logout</a></li>
			
<%--     스프링 시큐리티로 이제 AD인지 확인 가능하다                    
			<c:forEach items="${memberVO.userRoleList}" var="role"> 
				<c:if test="${role.userRole eq 'AD'  }">
                  <li><a href="${pageContext.request.contextPath }/member/memberList" >members</a></li>
				</c:if>
			</c:forEach>
 --%>
			<!-- 너 관리자냐 아니냐~ taglib security:authorize 등록해서 사용 -->
			<security:authorize access="hasAuthority('AD')">
				<li><a href="${pageContext.request.contextPath }/member/memberList" >members</a></li>
			</security:authorize>
		</ul>
	</li>

</ul>