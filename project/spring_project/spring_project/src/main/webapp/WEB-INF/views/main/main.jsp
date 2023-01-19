<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js">
        // 제이쿼리가 위에 있어야함
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://unpkg.com/chart.js-plugin-labels-dv@3.0.5/dist/chartjs-plugin-labels.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/x2js/1.2.0/xml2json.min.js" integrity="sha512-HX+/SvM7094YZEKOCtG9EyjRYvK8dKlFhdYAnVCGNxMkA59BZNSZTZrqdDlLXp0O6/NjDb1uKnmutUeuzHb3iQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/attendance.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chart.css">
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

</head>
<body>
    <!-- 전체 -->
    <div id="container" style="display: block;">
        <!-- 메인의 gif와 title 이미지 -->
        <div id="main_contents">
            <div id="main_title" class="row">
                <img id="main_title_img" src="${pageContext.request.contextPath}/image/mainTitle.png" alt="">
            </div>
            <div id="main_gifs" class="row main_img_div">
                <div class="p_div_imp" ><img id="gif1" class="main_img" src="${pageContext.request.contextPath}/gif/1.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif2" class="main_img" src="${pageContext.request.contextPath}/gif/3.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif3" class="main_img" src="${pageContext.request.contextPath}/gif/4.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif4" class="main_img" src="${pageContext.request.contextPath}/gif/5.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif5" class="main_img" src="${pageContext.request.contextPath}/gif/6.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif6" class="main_img" src="${pageContext.request.contextPath}/gif/7.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif7" class="main_img" src="${pageContext.request.contextPath}/gif/9.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif8" class="main_img" src="${pageContext.request.contextPath}/gif/10.gif" alt=""></div>
                <div class="p_div_imp" ><img id="gif9" class="main_img" src="${pageContext.request.contextPath}/gif/11.gif" alt=""></div>
            </div>
        </div>
        <!-- 메인에 고정된 버튼, 홈버튼, 바텀바 -->
        <div class="main_fixed">
            <div id="title_name">
                <img src="${pageContext.request.contextPath}/image/title.png" onclick="homeBut()" alt="">
            </div>
            <div id="main_button">
                <ul>
                    <li onclick="fn_condition()">condition</li>
                    <li onclick="fn_policy();get_card_body();">policy</li>
                    <c:if test="${loginMember != null}">
	                    <li onclick="fn_moveAtten()">attendance</li>
	                    <li onclick="fn_moveChart()">chart</li>
	                    <li onclick="location.href='${pageContext.request.contextPath}/members'">members</li>
	                    <li onmouseover="fn_nameOver()" onmouseout="fn_nameOut()">
	                    	<div>${loginMember.memName }</div>
	                    </li>
                    </c:if>
                </ul>
                <ul>
                	<li style="float: right;">
                		<div style="display: none" id="showMemberPoint">
                		<!-- 포인트에 따라 등급 이미지 달라지게! -->
                			<c:choose>
                				<c:when test="${loginMember.memPoint < 500 }">
                					<img id="gradeImage" alt="" src="${pageContext.request.contextPath}/image/grade/lv1.png">
                				</c:when>
                				<c:when test="${loginMember.memPoint <= 500 && loginMember.memPoint < 1000 }">
                					<img id="gradeImage" alt="" src="${pageContext.request.contextPath}/image/grade/lv2.png">
                				</c:when>
                				<c:when test="${loginMember.memPoint <= 1000 && loginMember.memPoint < 1500 }">
                					<img id="gradeImage" alt="" src="${pageContext.request.contextPath}/image/grade/lv3.png">
                				</c:when> 
                				<c:when test="${loginMember.memPoint <= 1500 && loginMember.memPoint < 2000 }">
                					<img id="gradeImage" alt="" src="${pageContext.request.contextPath}/image/grade/lv4.png">
                				</c:when>
                				<c:when test="${loginMember.memPoint >= 2000 }">
                					<img id="gradeImage" alt="" src="${pageContext.request.contextPath}/image/grade/lv5.png">
                				</c:when>    
                			</c:choose>
                			
                			${loginMember.memName}님 point 💰 ${loginMember.memPoint}</div>
                	</li>
                </ul>
            </div>
            <div id="bottom_button">
            	<c:if test="${loginMember == null}">
	                <button type="button" class="btn_login color_login" onclick="fn_registe_ani(); fn_registeCon();">로그인/회원가입</button>
            	</c:if>
            	<c:if test="${loginMember != null}">
            		<form action="${pageContext.request.contextPath }/logout">
	            		<button type="submit" class="btn_login color_login">로그아웃</button>
            		</form>
            	</c:if>
            </div>
            <div id="bottom_bar" ></div>
        </div>
        <!-- 로그인 창 -->
        <div class="container" id="p_registe">
            <div id="c_registe">
                <div id="sign_form" class="form signup">
                    <div class="form-header">
                      <div class="show-signup" onclick="fn_signUn()">Sign Up</div>
                      <div class="show-signin" onclick="fn_signIn()">Sign In</div>
                      <div class="show-close" onclick="fn_close()">Close</div>
                    </div>
                    <div class="arrow"></div>
                    <form action="${pageContext.request.contextPath}/login" 
                     class="needs-validation" id="loginMember" novalidate>
	                    <div class="form-elements">
	                      <div class="form-element">
	                        <input type="text" placeholder="UserId" name="memId" 
	                        	autocomplete="off" required pattern="[a-zA-Z0-9]{3,8}">
								<div class="valid-feedback">
									정상적으로 ID를 입력하셨습니다!
								</div>
								<div class="invalid-feedback">
			                    	ID는 3 ~ 8자리 영문과 숫자의 조합 입니다.
			                  </div>
	                      </div>
	                      <div class="form-element">
	                        <input type="text" placeholder="NickName" autocomplete="off"
	                        	id="memName" name="memName" required pattern="[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]{2,5}">
								<div class="valid-feedback">
									정상적으로 닉네임을 입력하셨습니다!
								</div>	   
								<div class="invalid-feedback">
			                    	이름은 한글 2 ~ 5자리 한글 + 숫자 + 영문입니다.
			                  </div>                     
	                      </div>
	                      <div class="form-element">
	                        <input type="password" placeholder="PassWord" 
	                        	autocomplete="off" id="memPass" name="memPass" required>
								<div class="valid-feedback">
									정상적으로 비밀번호를 입력하셨습니다!
								</div>	   
								<div class="invalid-feedback">
			                    	비밀번호를 입력해주세요!!
			                  </div>   								                       
	                      </div>
	                      <div class="form-element">
	                        <input type="password" placeholder="Confirm Password" 
	                        	autocomplete="off" id="memPassCheck" name="memPassCheck" required>     
	                        	<div class="valid-feedback">
									비밀번호가 같습니다!
								</div>	                   
								<div class="invalid-feedback">
			                    	비밀번호 확인을 입력해주세요!!
			                  </div>   
	                      </div>
	                      <div class="form-element">
	                      <!--  onclick="fn_signUp_btn()" -->
	                        <button type="submit" onclick="fn_signUp_btn()" id="submit-btn">Sign Up</button>
	                      </div>
	                    </div>
                    </form>
                  </div>
            </div>
            

        </div>
        <!-- 조건 창 -->
        <div id="b_condition">
            <div id="c_condition" class="b_cls">
                <p id="exit_img">
                    <img src="${pageContext.request.contextPath}/image/exit.png" onclick="fn_exit()" alt="">
                </p>
                <div>
                    <select name="" id="emp_select" class="form-select select_s" aria-label="Default select example">
                        <option value="">취업상태</option>
                        	<c:forEach items="${empList}" var="emp">
                        		<option value="${emp.conditionVal}">${emp.outVal}</option>
                        	</c:forEach>
                    </select>
                </div>
                <div>
                    <input autocomplete="off" type="text" class="form-control select_m" id="age_input" aria-label="Sizing example input" 
                    aria-describedby="inputGroup-sizing-sm" placeholder="만 나이" aria-label="Default select example">
                </div>
                <div>
                    <select name="" id="edu_select" class="form-select" aria-label="Default select example">
                        <option value="">학력상태</option>
                        	<c:forEach items="${eduList}" var="edu">
                        		<option value="${edu.conditionVal}">${edu.outVal}</option>
                        	</c:forEach>                        
                    </select>
                </div>
                <div>
                    <select name="" id="maj_select" class="form-select select_m" aria-label="Default select example">
                        <option value="">전공</option>
                        	<c:forEach items="${majList}" var="maj">
                        		<option value="${maj.conditionVal}">${maj.outVal}</option>
                        	</c:forEach>                          
                    </select>
                </div>
                <p id="entry" >
                    <img src="${pageContext.request.contextPath}/image/search_black.png" id="enter_img" alt="" 
                    onmouseover="over_entry()" onmouseout="out_entry()" onclick="go_policy()">
                </p>
                    
            </div>
        </div>
        <!-- 정책 창 -->
		<div id="p_policy">
			<div class="row">
				<!-- 슬라이드 쇼 -->
				<div id="carouselExampleControls" class="carousel slide"
					data-bs-ride="carousel">
					<!-- 검색조건버튼 -->
					<div class="row">
						<div class="col-10"></div>
						<button class="col-1 btn_login color_policy" type="button" onclick="resetPolicy()">검색 조건 초기화</button>
					</div>			
					<div class="row"><br></div>		
					<div class="carousel-inner">
						<!-- 슬라이드 쇼 1 -->
						<div class="row">
							<div id="policyBody" class="carousel-item active d-flex justify-content-center">
							</div>
						</div>
						<div class="row"><br><br></div>		
					</div>

				</div>
			</div>
			<!--  paging  -->
			<div class="div_paging">
				<div class="row"><br></div>
				<ul id="policyNum" class="pagination centerPage d-flex justify-content-center">
					<c:if test="${pagingVO.firstPage gt 10 }">
						<li class="page-item"><a href="#" class="page-link"
							data-curPage=${pagingVO.firstPage-1 }
							data-rowSizePerPage=${pagingVO.rowSizePerPage } onclick="fn_policyNum(this)">&laquo;</a></li>
					</c:if>

					<c:if test="${pagingVO.curPage ne 1 }">
						<li class="page-item"><a href="#" class="page-link"
							data-curPage=${pagingVO.curPage-1 }
							data-rowSizePerPage=${pagingVO.rowSizePerPage } onclick="fn_policyNum(this)">&lt;</a></li>
					</c:if>

					<c:forEach begin="${pagingVO.firstPage }"
						end="${pagingVO.lastPage }" step="1" var="i">
						<c:if test="${pagingVO.curPage ne i}">
							<li class="page-item"><a href="#" class="page-link"
								data-curPage=${i }
								data-rowSizePerPage=${pagingVO.rowSizePerPage } onclick="fn_policyNum(this)">${i }</a></li>
						</c:if>
						<c:if test="${pagingVO.curPage eq i }">
							<li class="page-item active"><a href="#" class="page-link">${i }</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${pagingVO.lastPage ne pagingVO.totalPageCount }">
						<li class="page-item"><a href="#" class="page-link"
							data-curPage=${pagingVO.curPage+1  }
							data-rowSizePerPage=${pagingVO.rowSizePerPage } onclick="fn_policyNum(this)">&gt;</a></li>
						<li class="page-item"><a href="#" class="page-link"
							data-curPage=${pagingVO.lastPage+1  }
							data-rowSizePerPage=${pagingVO.rowSizePerPage } onclick="fn_policyNum(this)">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
        <!-- 모달 창 -->
        <div>
            <!-- Modal policy -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick='$("#exampleModal").modal("hide");'>
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body" id="policyModalBody">
                    
                    </div>
                    <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick='$("#exampleModal").modal("hide");'>확인</button>
                    </div>
                </div>
                </div>
            </div>
            
        </div>
        <!-- 출석체크 창 -->
        <div id="attendanceDiv" class="container">
        	<div class="row htmlHeader"></div>
       		<div class="row sec_cal">
			  <div class="cal_nav">
			    <a href="javascript:;" class="nav-btn go-prev">prev</a>
			    <div class="year-month"></div>
			    <a href="javascript:;" class="nav-btn go-next">next</a>
			  </div>
			  <div class="cal_wrap">
			    <div class="days">
			      <div class="day">MON</div>
			      <div class="day">TUE</div>
			      <div class="day">WED</div>
			      <div class="day">THU</div>
			      <div class="day">FRI</div>
			      <div class="day">SAT</div>
			      <div class="day">SUN</div>
			    </div>
			    <div class="dates"></div>
			  </div>
			</div>
        	<div class="row attendanceBtn">
        		<div class="col-5"></div>
        		<div class="col-2">
        			<button class="col-1 btn_login color_attendace" type="button" 
        			onclick="fn_goAttendance()">출석하기</button>
        		</div>
        	</div>
        </div>
        
        <!-- 차트창 -->
        <div id="chartDiv">
        	<div class="row chartHeader"></div>
        	<div class="row chart_total">
        		<div class="col-1"></div>
	        	<div class="col-5 totalTable">
        			<div class="row">
        				<div class="d-flex justify-content-center"><h1>멤버 등수</h1></div>
        				<div class="row blank"></div>
        			</div>
        			<div class="row">
		        		<table class="gradeTable">
		        			<thead>
		        				<tr>
		        					<th>등수</th>
		        					<th>회원이름</th>
		        					<th>포인트</th>
		        					<th>등급</th>
		        				</tr>
		        			</thead>
		        			<tbody  class="gradeTableBody">
		        			</tbody>
		        		</table>
	        		</div>
	        	</div>
	        	<div class="col-1"></div>
	        	<div class="col-5">
	        		<canvas id="myChart" width="6" height="4"></canvas>
	        	</div>
        	</div>
        </div>
        
        


	</div>
</body>
</html>