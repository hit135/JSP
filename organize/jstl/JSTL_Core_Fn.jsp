<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
<style>
   ul > span{
       font-size: large;
       font-weight: bold;
       color: brown;
   }

</style>
</head>
<body>
	<h1>JSTL</h1>
	<hr>
	<ul><span>1. 개요</span>
		<li>JSP Standard Tag Library의 약자로 JSP 표준 라이브러리</li>
		<li>스크립트릿 사용 대신 JSTL + EL의 조합으로 훨씬 간결하고 가독성 좋은 코드를 완성</li>
	</ul>
	<ul><span>2. JSTL 코어 태그</span>
		<li>코어 태그 선언  <\%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%></li>
		<ul>
			<li>1) c:set >> 변수값 할당</li>
			<li>2) c:remove >> 변수값 삭제</li>
			<li>3) c:out >> 출력 >> 사용해야 하는 이유 ! >> 악의적인 코드를 예방할 수 있다</li>
			<li>4) c:if >> 조건문</li>
		</ul>
	</ul>
	<ul><span>3. JSTL 코어 태그 심화</span>
		<li>1) 이프문 <br>
			c:choose , c:when , c:otherwise
		</li>
		<li>2) 반복문<br>
			<\c:forEach items="\${memberList}" var="member"  varStatus="status" begin="3" end="7" step="2"><br>
				시작값 : \${status.begin}<br/>        <!-- 시작값  -->
				끝값: \${status.end}<br/>          <!-- 끝값 -->
				증가값: \${status.step}<br/>         <!-- 증가값 -->
				현재 루프가 처음인지 반환  : \${status.first}<br/>        <!-- 현재 루프가 처음인지 반환  -->
				현재 루프가 마지막인지 반환 : \${status.last}<br/>         <!-- 현재 루프가 마지막인지 반환 -->   
				0부터의 순서: \${status.index}<br/>        <!-- 0부터의 순서-->
				카운트: \${status.count}<br/>        <!-- 카운트--> 
				현재 아이템: \${status.current}<br/>      <!-- 현재 아이템 -->
				﻿
				<p> 순 번 : \${status.count} </p>
				<p> 아이디 : \${member.memId} </p>
				<p> 이름 : \${member.memNm} </p>
			<\/c:forEach>
		</li>
	</ul>
	<ul><span>4. JSTL 함수 태그</span>
		 1) fn:toUpperCase(string) : string을 모두 대문자로 변경 후 리턴<br>

                  2) fn:toLowerCase(string) : string을 모두 소문자로 변경 후 리턴<br>

                  3) fn:length(item) : item이 배열이나 컬렉션이면 요소의 개수를 문자열이면 문자의 개수를 반환<br>

                  4) fn:substring(string, begin, end) : string에서 begin인덱스에서 시작해서 end인덱스에 끝나

                                                                                          부분의 문자열 반환<br>

                  5) fn:substringAfter(string, sbustring) : string에서 substring이 나타나는 이후의 문자열 반환    <br>

                  6) fn:substringBefore(string, sbustring) : string에서 substring이 나타나는 이전의 문자열 반환<br>

                  7) fn:replace(string, before, after) : string내에 있는 before 문자열을 after 문자열로 모두

                                                                                          변경해서 반환<br>

                  8) fn:indexOf(string, sbustring) : string에서 substring이 처음으로 나타나는 인덱스 반환<br>

                  9) fn:startsWith(string, prefix) : string이 prefix로 시작하면 return True<br>

                  10) fn:endsWith(string, suffix) : string이 suffix로 끝나면 return True<br>

                  11) fn:contains(string, sbustring) : string이 substring을 포함하면 return true 반환<br>

                  12) fn:split(string, separator) : string내의 문자열 separetor에 따라 나누어서 배열로 구성해서

                                                                                반환<br>

                  13) fn:join(array, separator) : array요소들을 separator를 구분자로 하여 연결해서 반환<br>

                  14) fn:trim(string) : string앞뒤의 공백을 모두 제거한 후 반환<br>

                  15) fn:containsIgnoreCase(string, sbustring) : 대소문자 관계없이 string이 substring을 포함

                                                                                                            하면 return true 반환<br>
	</ul>
	<ul><span>중요한건 자바로 선언하면서 화면에 구현하려면 힘드니까!<br>
				JSTL + EL문으로 화면을 구현한다</span>
	
	</ul>
</body>
</html>