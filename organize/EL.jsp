<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style>
   ul > span{
       font-size: large;
       font-weight: bold;
       color: brown;
   }
   ul > ul > li > div{
       color: blue;
       font-weight: bold;
   }
   ul > ul > div{
       color: blue;
       font-weight: bold;
   }
</style>
</head>
<body>
<h1>JSP</h1>
    <hr>
    <ul><span>1. JSP Directive(디렉티브)</span> 
        <li>디렉티브는 JSP페이지에 대한 설정 정보를 지정할 때 사용</li>
        <li> page language="java" contentType="text/html; charset=utf-8" 이런 형식으로 씀</li>
        <li>contentType >> JSP로 설정할 문서의 타입</li>
        <li>charset >> 언어 형식</li>
        <li>pageEncoding >> 페이지를 인코딩하는 형식 // charset과는 다르다</li>
        <li>errorPage >> 에러페이지 .. 에러페이지는 따로 클래스를 파서 만들어줘야한다 >> 절대경로나 상대경로로 경로 지정해주기!</li>
    </ul>
    <ul><span>2. JSP 처리과정</span>
        <img src="http://127.0.0.1:5500/jsp_practice/jps_image/jsp_image01.jpg" alt="">
        <li>브라우저 >> WAS >> (JSP >> JAVA >> CLASS)</li>
        <li>즉, jsp를 서블릿으로 반환돼서 출력한다(내부적으로) >> 이때 서블릿이 있으면 그 파일을 끌어다 쓰고, 없으면 새로 생성</li>
        <li>(JSP >> JAVA >> CLASS) 이 과정은 WAS가 모두 처리</li>
        <li>브라우저는 HTML을 받아올 뿐이다..</li>
    </ul>
    <ul><span>3. 스크립트 요소(JAVA표현식)</span>
        <li>JSP페이지에서 자바코드를 사용할 수 있는 영역을 의미함</li>
        <ul><br>
            <li><div> 1) 선언부</div>
                메서드, 변수등을 선언함 >> <\%! ... %>
            </li>
            <li><div> 2) 표현식</div>
                자바 코드 실행 >> <\% ... %>
            </li>
            <li><div> 3) 스크립트릿(Scriptlet)</div>
                결과값을 출력 >> <\%= ... %>
            </li>
        </ul>
    </ul>
    <ul><span>4. EL(표현언어)</span>
        <li>JSP의 네 가지 기본 객체(page,request,session,application) 제공</li>
        <li>수치연산, 관계연산, 논리연산 가능</li>
        <li style="color: red ;">jQuery의 "$()"와 EL표현의 "\${}"을 혼동하지 말자!</li>
        <li>JAVA 표현식 대신 EL을 많이 쓰는데 이는 스크립트 요소를 편하게(가독성 좋게) 쓸 수 있기 때문이다 >> 유지 보수에 유리</li>
        <ul style="font-weight: bold;">
            <li >객체에 저장된 값에 접근하는 방법</li>
            <li>${member.no}</li>
            <li>${member["no"]}</li>
        </ul><br>
        <ul><div>1) EL에서 산술 연산자</div>
            <li>\${1 + 2} 덧셈 : ${1 + 2} </li>
            <li>\${"1" + 2} 문자 덧셈 (숫자로 치환) : ${"1" + 2}</li>
            <li>\${"1" + "2"} 문자 덧셈 (숫자로 치환) : ${"1" + "2"}</li>
            <li>\${null + 2} 문자 덧셈 (null도 더해줌) : ${null + 2}</li>
            <li>\${1 - 2} 뺄셈 : ${1 - 2}</li>
            <li>\${10 * 3} 곱셈 : ${10 * 3}</li>
            <li>\${10 / 3} 나눗셈: ${10 / 3}</li>
            <li>\${10 div 3} 나눗셈: ${10 div 3}</li>
            <li>\${10 % 3} 나머지: ${10 % 3}</li>
            <li>\${10 mod 3} 나머지: ${10 mod 3}</li>
        </ul><br>
        <ul><div>2) EL에서 논리 연산자</div>
            <li>\${1==1 && "nextit"=="nextit"} &&(그리고) : ${1==1 &&  "nextit"=="nextit"}</li>
            <li>\${1==1 and "nextit"=="nextit"} and : ${1==1 and  "nextit"=="nextit"}</li>
            <li>\${1==1 || 1!=1} ||(또는) : ${1==1 || 1!=1}</li>
            <li>\${1==1 or 1!=1} or : ${1==1 or 1!=1}</li>
            <li>\${!true } !(아니다) : ${!true }</li>
            <li>\${not false } not : ${not false }</li>
        </ul><br>
        <ul><div>2) EL에서 논리 연산자</div>
            <li>\${1==1 && "nextit"=="nextit"} &&(그리고) : ${1==1 &&  "nextit"=="nextit"}</li>
            <li>\${1==1 and "nextit"=="nextit"} and : ${1==1 and  "nextit"=="nextit"}</li>
            <li>\${1==1 || 1!=1} ||(또는) : ${1==1 || 1!=1}</li>
            <li>\${1==1 or 1!=1} or : ${1==1 or 1!=1}</li>
            <li>\${!true } !(아니다) : ${!true }</li>
            <li>\${not false } not : ${not false }</li>
        </ul><br>
        <ul><div>3) EL에서 비교 연산자</div>
            <li>\${10 == 10} 같다(==) : ${10 == 10}</li>
            <li>\${10 eq 10} eq(==) : ${10 eq 10}</li>
            <li>\${'nextit' == 'nextit'} 문자도 비교연산 가능 : ${'nextit' == 'nextit'}</li>
            <li>\${3 < 10} 부등호도 가능! : ${3 < 10}</li>
            <li>\${3 lt 10} 비교표현식 가능 : ${3 lt 10}</li>
            <li>lt - little(<), le - little or equal, gt - greater(>), ge - greater or equal</li>
        </ul><br>
        <ul><div>4) EL에서 empty</div>
            <li>EL에서 ''은 null값이다</li>
            <% request.setAttribute("memId", null); %>
            <li>${ empty memId} >> memId라는 값을 선언 했을 때, memId가 null이면 true 아니면 false</li>
        </ul><br>
        <ul><div>5) EL에서 삼항연산자 가능!</div>
            <li>\${10 == 10 ? 2000 : 3000 } 앞의 조건식이 true면 2000, 아니면 3000 : ${10 == 10 ? 2000: 3000 }</li>
        </ul>
        <ul><div>6) EL에서 배열, 메소드도 가능</div>
            <li>list01 : ${list01 = [1,2,3,4,5]}<br>
                list01 0번째 : ${list01[0] }<br>
                list01 1번째 : ${list01[1] }<br></li>
            <li>${ member.setMemId("nextit8850") }
                ${ member.setMemPass("0427198850") }
                ${ member.setMemNm("넥스트아이티교육센터") }
                
                memId : ${member.memId }<br>
                memPass : ${member.memPass }<br>
                memNm : ${member.memNm }<br></li>
        </ul>
        
    </ul>
</body>
</html>