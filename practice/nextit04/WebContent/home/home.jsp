<%@page import="kr.or.nextit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<link rel="stylesheet" type="text/css" href="../css/style_main.css">
<link rel="stylesheet" type="text/css" href="../css/footer.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<%
MemberVO member = (MemberVO)session.getAttribute("memberVO");
if(member == null){
	response.sendRedirect(request.getContextPath()+"/login/login.jsp?msg=none");
}
%>
<script>
window.onload= function(){
    let img_files=[
        "../images/img1.jpg",
        "../images/img2.jpg",
        "../images/img102.jpg",
    ];
    let div_img = document.getElementById("img_div");
    let i =0;
    function fn_change_img(){
        console.log("i::::"+i);
        div_img.style.backgroundImage="url("+img_files[i]+")";
        if(i==2){
            i=0;
        }else{
            i++
        }
        div_img.style.transitionDuration="2s";
        
    }

    setInterval(function(){
      fn_change_img();

    },4000);
}
function fn_logout(){
	//alert("fn_logout");
	let ret = confirm("로그아웃하시겠습니까?");
	if(ret){
		location.href= "${pageContext.request.contextPath}/login/logout.jsp";
	}
}


</script>
</head>
<body>
<div id="wrap">
    <div id="img_div" class="intro_bg">
        <div class="header">
            <!-- hearder s-->
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
                <li><a href="#service">SERVICE</a>
                    <ul>
                        <li><a href="#">menu1</a></li>
                        <li><a href="#">menu2</a></li>
                        <li><a href="#">menu3</a></li>
                    </ul>
                </li>
                <li><a href="#content">CONTENT</a></li>
                <li><a href="${pageContext.request.contextPath }/free/freeList.jsp">FREEBOARD</a></li>
                
              	<li><a href="#">${memberVO.memId }</a>
              			<ul>
              				<li><a href="${pageContext.request.contextPath}/member/memberView.jsp?memId=${memberVO.memId}">info</a></li>
             					<li><a href="#" onclick="fn_logout()">logout</a></li>
             					<!-- 리스트형태이므로 접근하려면 포이치 -->
             					<c:forEach items="${memberVO.userRoleList }" var="role">
             						<c:if test="${role.userRole eq 'AD' }">
	             						<li><a href="${pageContext.request.contextPath }/member/memberList.jsp" >members</a></li>
             						</c:if>
             					</c:forEach>
              			</ul>
              	</li>
                
                
                
            </ul>
            <!-- header e -->


            <!-- 반응형 추가 s -->
            <!-- 3줄 선 그리기  -->
            <input type="checkbox" id="menuicon" />
            <label for="menuicon">
                <span></span>
                <span></span>
                <span></span>
            </label>

            <div class="header_icon">
                <ul class="menu">
                    <li><a href="#">HOME</a></li>
                    <li><a href="#about">ABOUT</a></li>
                    <li><a href="#service">SERVICE</a></li>
                    <li><a href="#content">CONTENT</a></li>
                    <li><a href="#">FREEBOARD</a></li>
                </ul>
            </div>
            <!-- 반응형 추가 e -->

        </div>
        <div class="intro_text">
            <h1>NextIT</h1>
            <H4>넥스트아이티</H4>
        </div>
        
    </div>
    <!--1차 작업 intro_bg e -->


    <!--2차 작업 sub_title_div s -->
    <div class="sub_title_div">
        <ul class="sub_title">
            <li>
                <div>
                    <h2>AA</h2>
                    <p>11</p>
                </div>
            </li>
            <li>
                <div>
                    <h2>BB</h2>
                    <p>22</p>
                </div>
            </li>
            <li>
                <div>
                    <h2>CC</h2>
                    <p>33</p>
                </div>
            </li>
            <li>
                <div>
                    <h2>DD</h2>
                    <p>44</p>
                </div>
            </li>
        </ul>
    </div>
    <!--2차 작업 sub_title_div e -->
    
    <!--3차 작업 main_text0 s -->
    <div class="main_text0">
        <h1 id="about">ABOUT</h1>
        <p>4차 산업혁명 신기술 교육을 선도하는...</p>
        <ul class="icons">
            <li>
                <div class="icon_img">
                    <img src="../images/icon0.svg" alt="이미지가 없습니다.">
                </div>
                <div class="contents1_bold">
                    <p>ABC</p>
                </div>
                <div class="contents2">
                    <p>2323232323232</p>
                </div>
                <div class="more">
                    MORE
                </div>
            </li>
            <li>
                <div class="icon_img">
                    <img src="../images/icon1.svg" alt="이미지가 없습니다.">
                </div>
                <div class="contents1_bold">
                    <p>ABC</p>
                </div>
                <div class="contents2">
                    <p>2323232323232</p>
                </div>
                <div class="more">
                    MORE
                </div>
            </li>
            <li>
                <div class="icon_img">
                    <img src="../images/icon2.svg" alt="이미지가 없습니다.">
                </div>
                <div class="contents1_bold">
                    <p>ABC</p>
                </div>
                <div class="contents2">
                    <p>2323232323232</p>
                </div>
                <div class="more">
                    MORE
                </div>
            </li>
                
        </ul>
    </div>
    <!--3차 작업 main_text0 e -->


    <!--4차 작업 main_text1 s -->
    <div class="main_text1">
        <h1 id="service">SERVICE</h1>
        <div class="contents2">
            <p>5454545454545454545454</p>
        </div>
        <div class="service">
            <div>
                <img src="../images/img101.png" alt="이지미가 존재하지 않습니다.">
            </div>
            <div class="contents3">
                <h2>service content2</h2>
                <p>안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                    안녕하세요 NextIT 입니다. 만나서 반갑습니다.
                </p>
            </div>
        </div>
    </div>  
    <!--4차 작업 main_text1 e -->


    <!--5차 작업 main_text2 s -->
    <div class="main_text2">
        <ul>
            <li>
                <div class="last_bg1">
                    <h1 id="content">CONTENT</h1>
                    <div>
                        <p>NextIT 교육센터입니다.</p>
                    </div>
                    <div class="more2">
                        <p>더 알아보기</p>
                    </div>
                </div>
            </li>
            <li>
                <div class="last_bg2">
                    <h1>CUSTOMER</h1>
                    <div>
                        <p>NextIT 교육센터입니다.</p>
                    </div>
                    <div class="more2">
                        <p>더 알아보기</p>
                    </div>
                </div>
            </li>
            
        </ul>
    </div>
    <!--5차 작업 main_text2 e -->

    
    <!--6차 작업 footer -->
    <footer id="page_footer">
			<%@ include file="/footer/footer.jsp" %>
    </footer>

</div><!-- wrap e -->
</body>
</html>