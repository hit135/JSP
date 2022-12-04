<%@page import="kr.or.nextit.code.service.CommCodeServiceImpl"%>
<%@page import="kr.or.nextit.code.service.ICommCodeService"%>
<%@page import="kr.or.nextit.code.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="../images/nextit_log.jpg" />
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/join.css">
<script>

$(function(){
	$("#memId").click(function(){
    	$(this).next().removeClass("warning");
    });   
    $("#memName").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memPass").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memPassCheck").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memZip").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memAdd1").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memBir").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memMail").click(function(){
    	$(this).next().removeClass("warning");
    });
    $("#memHp").click(function(){
    	$(this).next().removeClass("warning");
	});
    
});


function join(){
    //alert("join");
    
	/* let memId = $("#memId");
	let memPass = $("#memPass");
	let memPassCheck = $("#memPassCheck");
	console.log("memId : "+ memId.val() );
 
	if( memId.val()==""){
		alert("ID를 입력해주세요");
		memId.next("label").addClass("warning");
		return;
	}else if(memPass.val()==""){
		alert("패스워드를 입력해주세요");
		memPass.next("label").addClass("warning");
		return;
	}else if(memPassCheck.val()==""){
		alert("패스워드 재확인 값을 입력해주세요");
		memPassCheck.next("label").addClass("warning");
		return;
	}
	
	let checkBlan = /\s/g;
	if( checkBlan.test(memId.val() ) ){
		alert("ID에 공백 존재합니다. 다시 입력해주세요");
		memId.val("");
		memId.next("label").addClass("warning");
		return;
	}if( checkBlan.test(memPass.val() ) ){
		alert("패스워드에 공백 존재합니다. 다시 입력해주세요");
		memPass.val("");
		memPass.next("label").addClass("warning");
		return;
	}
	
	// 영문, 글자수 4~10 
	let checkWord = /^\w{4,10}$/;
	if( ! checkWord.test(memId.val() ) ){
		alert("ID에 영문, 숫자 조합 최소 4자이상 10글자 이하 이어야 합니다.");
		memId.val("");
		memId.next("label").addClass("warning");
		return;
	}else if( ! checkWord.test(memPass.val() ) ){
		alert("패스워드는 영문, 숫자 조합 최소 4자이상 10글자 이하 이어야 합니다.");
		memPass.val("");
		memPass.next("label").addClass("warning");
				
		memPassCheck.val("");
		memPassCheck.next("label").addClass("warning");
		return;
	}
	//패스워드 재확인 비교 검증	
	if( memPass.val() != memPassCheck.val()){
		alert("재확인 패스워드가 올바르지 않습니다. 다시 입력해주세요");
		memPassCheck.val();
		memPassCheck.next("label").addClass("warning");
		return;
	} */
	
	
	//1차 값 우뮤 검증
	let memId = $("#memId");
    let memName = $("#memName");
    let memPass = $("#memPass");
    let memPassCheck = $("#memPassCheck");
    let memZip = $("#memZip");
    let memAdd1 = $("#memAdd1");
    let memBir = $("#memBir");
    let memMail = $("#memMail");
    let memHp = $("#memHp");
    

	 if( memId.val()==""){
    	alert("ID를 입력해주세요."); 
    	memId.next("label").addClass("warning");
        return;
    }else if( memName.val() =="" ){
    	alert("아름을 입력해주세요"); 
    	memName.next("label").addClass("warning");
        return;
    }else if( memPass.val() =="" ){
    	alert("패스워드를 입력해주세요."); 
    	memPass.next("label").addClass("warning");
        return;
    }else if( memPassCheck.val() =="" ){
    	alert("패스워드 재확인 값을 입력해주세요"); 
    	memPassCheck.next("label").addClass("warning");
        return;
    }else if( memZip.val() =="" ){
    	alert("우편변호를 입력해주세요"); 
    	memZip.next("label").addClass("warning");
        return;
    }else if( memAdd1.val() =="" ){
    	alert("주소를 입력해주세요"); 
    	memAdd1.next("label").addClass("warning");
        return;
    }else if( memBir.val() =="" ){
    	alert("생일을 입력해주세요"); 
    	memBir.next("label").addClass("warning");
        return;
    }else if( memMail.val() =="" ){
    	alert("메일을 입력해주세요"); 
    	memMail.next("label").addClass("warning");
        return;
    }else if( memHp.val() =="" ){
    	alert("휴대폰번호를 입력해주세요"); 
    	memHp.next("label").addClass("warning");
        return;
    }   

//2차 아이디, 이름, 패스워드 공백 검증
    let checkBlank = /\s/g;
    console.log("checkBlan: ", checkBlank.test( memId.val() ) );
    
    if( checkBlank.test( memId.val()) ){
    	alert("ID에 공백이 존재합니다. 다시 입력해주세요"); 
    	memId.val("");
    	memId.next("label").addClass("warning");
        return;
    }else if(checkBlank.test( memName.val()) ){
    	alert("이름에 공백이 존재합니다. 다시 입력해주세요");
    	memName.val("");
    	memName.next("label").addClass("warning");
        return;
    }else if(checkBlank.test( memPass.val()) ){
    	alert("패스워드에 공백이 존재합니다. 다시 입력해주세요"); 
    	memPass.val("");    	
    	memPass.next("label").addClass("warning");
        return;
    }    
    
//3차 아이디, 패스워드  글자수 및 영문, 숫자, 한글제외 검증 
    let checkWord = /^\w{4,10}$/;
    console.log("checkWord: ", checkWord.test( memId.val()) );  
    if( ! checkWord.test( memId.val()) ) {
    	alert("ID는 영문,숫자 조합 최소 4글자 이상 10글자 이하 이어야 합니다."); 
    	memId.val("");
    	memId.next("label").addClass("warning");
    	return;
    }else if( ! checkWord.test( memPass.val()) ){
    	alert("패스워드는 최소 4글자 이상  10글자 이하 이어야 합니다."); 
    	memPass.val("");    	
    	memPassCheck.val("");
    	memPass.next("label").addClass("warning");
    	memPassCheck.next("label").addClass("warning");
        return;
    }
    
//4차  패스워드 와 재확인 패스워드 비교 검증
    if( memPass.val() != memPassCheck.val()){
    	alert("재확인 패스워드가 올바르지 않습니다. 다시 입력해주세요")
    	memPassCheck.val("");
    	memPassCheck.next("label").addClass("warning");
    	return;
    }

//5차  우편번호 검증
    let checkMemZip = /^\d{5}$/;
    console.log("checkMemZip: ", checkMemZip.test( memZip.val()) ); 
    if( ! checkMemZip.test( memZip.val()) ){
    	alert("우편번호는 숫자로 5글자 입니다. 다시 입력해주세요"); 
    	memZip.val("");    	
    	memZip.next("label").addClass("warning");
        return;
    }

//6차  메일검증
    let checkMemMail = /^[-_a-zA-z0-9]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,4}$/i;
    console.log("checkMemMail: ", checkMemMail.test(memMail.val()) );
    if( ! checkMemMail.test(memMail.val()) ){
    	alert("이메일 양식이 틀립니다. 다시 입력해주세요"); 
    	memMail.val("");    	
    	memMail.next("label").addClass("warning");
        return;
    }
    
//7차   휴대폰 번호 검증
    let checkMemHp = /^[0-9]{10,11}$/;
    if( ! memHp.val().match(checkMemHp)){
    	alert("휴대폰번호는 숫자를 사용하여 10~11 자리 입력 주세요."); 
    	memHp.val("");    	
    	memHp.next("label").addClass("warning");
        return;
    }
 
	let f = document.loginForm;
	console.log("f", f	);
	
    f.action="${pageContext.request.contextPath}/member/memberRegister.jsp";
    f.submit();
    
};
</script>

<%
ICommCodeService codeService = new CommCodeServiceImpl();
List<CodeVO> jobList  = codeService.getCodeListByParent("JB00");
request.setAttribute("jobList", jobList);

List<CodeVO> hobbyList  = codeService.getCodeListByParent("HB00");
request.setAttribute("hobbyList", hobbyList);
%>
 
</head>
<body>
    <section class="login_form">
        <h1>NextIT</h1> 
        <form name="loginForm" method="post">
            <div class="int-area">
                <input type="text" id="memId" name="memId" value="" autocomplete="off" required="required">
                <label for=memId>USER ID</label>
            </div>
            <div class="int-area">
                <input type="text" id="memName" name="memName" value="" autocomplete="off" required="required">
                <label for=memName>USER NAME</label>
            </div>
            <div class="int-area">
                <input type="password" id="memPass" name="memPass" value="" autocomplete="off" required="required">
                <label for=memPass>PASSWORD</label>
            </div>
            <div class="int-area">
                <input type="password" id="memPassCheck" name="memPassCheck" value=""  autocomplete="off" required="required">
                <label for=memPassCheck>RECONFIRM PASSWORD</label>
            </div>
            
            
            <div class="int-area">
                <input type="text" id="memZip" name="memZip" value="" autocomplete="off" required="required">
                <label for=memZip>ZIP CODE</label>
            </div>
            <div class="int-area">
                <input type="text" id="memAdd1" name="memAdd1" value="" autocomplete="off" required="required">
                <label for=memAdd1>ADDRESS 1</label>
            </div> 
            <div class="int-area">
                <input type="text" id="memAdd2" name="memAdd2" value="" autocomplete="off" required="required">
                <label for=memAdd2>ADDRESS 2</label>
            </div> 
            <div class="int-area">
                <input type="date" id="memBir" name="memBir" value="" autocomplete="off" required="required">
                <label for=memBir>BIRTHDAY</label>
            </div> 
            <div class="int-area">
                <input type="text" id="memMail" name="memMail" value="" autocomplete="off" required="required">
                <label for=memMail>E-MAIL</label>
            </div> 
            <div class="int-area">
                <input type="text" id="memHp" name="memHp" value="" autocomplete="off" required="required">
                <label for=memHp>PHONE</label>
            </div> 
            <div class="int-area int-area-select">
               	<select name="memJob" required="required">
						<option value="">-- 직업 선택 --</option>
						<!-- 	<option value="JB01">주부</option>
							<option value="JB02">은행원</option>
							<option value="JB03">공무원</option>
							<option value="JB04">축산업</option>
							<option value="JB05">회사원</option>
							<option value="JB06">농업</option>
							<option value="JB07">자영업</option>
							<option value="JB08">학생</option>
							<option value="JB09">교사</option>					 -->
							
							<c:forEach items="${jobList }" var="job">
								<option value="${job.commCd }">${job.commNm }</option>
							</c:forEach>
					
					
				</select>
				<label for=memJob>JOB</label>	
            </div>
            
            <div class="int-area int-area-select">
               	<select name="memHobby" required="required">
						<option value="">-- 취미 선택 --</option>
				<!-- 		<option value="HB01">서예</option>
						<option value="HB02">장기</option>
						<option value="HB03">수영</option>
						<option value="HB04">독서</option>
						<option value="HB05">당구</option>
						<option value="HB06">바둑</option>
						<option value="HB07">볼링</option>
						<option value="HB08">스키</option>
						<option value="HB09">만화</option>
						<option value="HB10">낚시</option>
						<option value="HB11">영화감상</option>
						<option value="HB12">등산</option>
						<option value="HB13">개그</option>
						<option value="HB14">카레이싱</option>			 -->		
						<c:forEach items="${hobbyList }" var="hobby">
								<option value="${hobby.commCd }">${hobby.commNm }</option>
						</c:forEach>
						
				</select>
				<label for=memHobby>HOBBY</label>	
            </div>
            
            <div class="btn-area">
                <button type="button"  id="btn_join"name="btn_join" onclick="join()">JOIN</button>
            </div>
        </form>    
    </section>
    
</body>
</html>