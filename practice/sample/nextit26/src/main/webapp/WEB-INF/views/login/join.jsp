<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<title>NextIT</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/nextit_log.jpg" />
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/join.css">
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
    
	/* $(function(){}) 에 처리  */   
	$("input, select").click(function(){
		$(this).next("span").remove();
	});
	$("input").change(function(){
		if($(this).val() == ""){
			//alert("234");
			$(this).nextAll("label").removeAttr("style");
		}
	});
    
});


window.setTimeout(function(){
	if($("#memId").val() !=""){
		$("label[for='memId']").css({"top": "35px", "font-size":"13px", "color":"#166cea"});
	}
	if($("#memName").val() !=""){
		$("label[for='memName']").css({"top": "90px", "font-size":"13px", "color":"#166cea"});
	}
	if($("#memPass").val() !=""){
		$("label[for='memPass']").css({"top": "145px", "font-size":"13px", "color":"#166cea"});
	}
	if($("#memZip").val() !=""){
		$("label[for='memZip']").css({"top": "245px", "font-size":"13px", "color":"#166cea"});
	}
	if($("#memMail").val() !=""){
		$("label[for='memMail']").css({"top": "450px", "font-size":"13px", "color":"#166cea"});
	}
	if($("#memHp").val() !=""){
		$("label[for='memHp']").css({"top": "500px", "font-size":"13px", "color":"#166cea"});
	}
},500);


let idCheck = false;


function join(){
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
    
/*
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
    
    */
    
    /* async : false를 쓰면 비동기가 제어가 된다..
        동기형식으로 바꿔줌
       setTimeOut, ajax, 애드이벤트 등은 다른쪽에 보냈다가
        동기가 다 끝나면 하나씩 실행되므로
        비동기 ajax를 동기형식으로 해줘야 한다
     */
    $.ajax({
		 url:"<c:url value='/join/idCheck'/>"
		,type:"post"
		,data:{"memId" : $("#memId").val() }
		,async : false
		,success: function(data) {
			console.log("아이디 체크 : ", data);
			
			if(data){
				// alert("사용가능한 아이디 입니다!");
				idCheck = true;
			}else{
				// $("#memId").val("");
				console.log("이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요");
			}
			
		}
		,error: function() {
			alert("error")
		}
	});
    
    if(!idCheck){
    	$("$memId").val("");
    	alert("이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요");
    	return;
    }
    
    
	let f = document.loginForm;
	console.log("f", f	);
	
    f.action="${pageContext.request.contextPath}/member/memberRegister";
    f.submit();
    
};

// 아이디 체크 비동기 함수
function fn_checkId() {
	// alert("fn_checkId");
	console.log("memId : ", $("#memId").val());
	
	$.ajax({
		 url:"<c:url value='/join/idCheck'/>"
		,type:"post"
		,data:{"memId" : $("#memId").val() }
		,success: function(data) {
			console.log("아이디 체크 : ", data);
			
			if(data){
				alert("사용가능한 아이디 입니다!");
			}else{
				$("#memId").val("");
				alert("이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요")
			}
			
		}
		,error: function() {
			alert("error")
		}
	});
	
	
}
</script>


<style type="text/css">
span{
	color: white;
	display: block;
	width:260px;
	text-align:left;
}
#memId+span{
	position:absolute;
	top:60px;
	right:-300px;
}
#memName+span{
	position:absolute;
	top:110px;
	right:-300px;
}
#memPass+span{
	position:absolute;
	top:160px;
	right:-300px;
}
#memZip+span{
	position:absolute;
	top:250px;
	right:-300px;
}
#memBir+span{
	position:absolute;
	top:410px;
	right:-300px;
}
#memMail+span{
	position:absolute;
	top:460px;
	right:-300px;
} 
#memHp+span{
	position:absolute;
	top:505px;
	right:-300px;
}
#memJob+span{
	position:absolute;
	top:570px;
	right:-300px;
}
#memHobby+span{
	position:absolute;
	top:620px;
	right:-300px;
}
/*버튼 css 입히기  */
#check_id{
	position: absolute;
	top: 55px;
	left: 350px;
	width: 50px;
	height: 20px;	
    color: #fff;
    border: none;
    border-radius: 5px;
    background-color: #166cea;
}
.profile_image{
	width: 300px; 
	height: 300px; 
	background-color: lightgray;
	position: absolute;
	left: 450px;ㅋ
}
 
.upload {
	width: 300px;
	height: 250px;
	/* background-color: antiquewhite; */
}
li {
  list-style: none;
}
.profile_image img{
  width: 300px;
  height: 250px;
}
.image-preview {
	width: 300;
	height: 250px;
	background-color: #9b9b9d;
	position: absolute;
	top: 0px;
}
.profile_image input{
	width: 200px;
	margin-top: 15px;
}
/* 메일 css */
#mailAuth{
	position: absolute;
    top: 465px;
    left: 350px;
    width: 55px;
    height: 20px;
    color: #fff;
    border: none;
    border-radius: 5px;
    background-color: #166cea;
}
</style>


</head>
<body>
    <section class="login_form">
        <h1>NextIT</h1> 
       <%--  <form name="loginForm" method="post"/> --%>
       <!-- 화면에서 받아주기 modelAttribute="member" -->
       <!-- commandName은 이전버전! -->
       <!-- <form name="loginForm" method="post" commandName="member"/> -->
        <form:form name="loginForm" method="post" modelAttribute="member" enctype="multipart/form-data">
        	
        	<!-- 사용자 프로필 업로드 -->
        	<div class="profile_image">
        		<div class="upload"></div>
      			<!-- accept="image/*" >> 이미지 관련된 파일만 올릴 수 있다 -->
      			<input type="file" class="real-upload" name="profilePhoto"
      			 		accept="image/*" required="required">
      			 <ul class="image-preview"></ul>
        	</div>
        	
        	
        
            <div class="int-area">
                <!-- <input type="text" id="memId" name="memId" value="" autocomplete="off" required="required"> -->
                <form:input path="memId" autocomplete="off"/>
                <form:errors path="memId"></form:errors>
                <label for=memId>USER ID</label>
                <!-- ID체크 버튼 -->
                <button type="button" id="check_id" name="check_id" onclick="fn_checkId()">ID확인</button>
                
            </div>
            <div class="int-area">
                <!-- <input type="text" id="memName" name="memName" value="" autocomplete="off" required="required"> -->
                
                <form:input path="memName" autocomplete="off"/>
                <form:errors path="memName"></form:errors>
                <label for=memName>USER NAME</label>
            </div>
            
            
            <div class="int-area">
                <!-- <input type="password" id="memPass" name="memPass" value="" autocomplete="off" required="required"> -->
                
                <form:password path="memPass" autocomplete="off"/>
                <form:errors path="memPass"></form:errors>
                <label for=memPass>PASSWORD</label>
            </div>
            <div class="int-area">
                <input type="password" id="memPassCheck" name="memPassCheck" value=""  autocomplete="off" required="required">
                <label for=memPassCheck>RECONFIRM PASSWORD</label>
            </div>
            
            
            <div class="int-area">
                <!-- <input type="text" id="memZip" name="memZip" value="" autocomplete="off" required="required"> -->
                
                <form:input path="memZip" autocomplete="off"/>
                <form:errors path="memZip"></form:errors>                
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
                <input type="date" id="memBir" name="memBir" value="${member.memBir }" autocomplete="off" required="required">
                
                <form:errors path="memBir"></form:errors>   
                <label for=memBir>BIRTHDAY</label>
            </div> 
            <div class="int-area">
                <!-- <input type="text" id="memMail" name="memMail" value="" autocomplete="off" required="required"> -->
                
                <form:input path="memMail" autocomplete="off"/>
                <form:errors path="memMail"></form:errors>                  
                <label for=memMail>E-MAIL</label>
                
                	<!-- 이메일 인증위한 버튼 -->
                	<button type="button" id="mailAuth">mail인증</button>
            </div> 
            <div class="int-area">
                <!-- <input type="text" id="memHp" name="memHp" value="" autocomplete="off" required="required"> -->
                
                <form:input path="memHp" autocomplete="off"/>
                <form:errors path="memHp"></form:errors>                   
                <label for=memHp>PHONE</label>
            </div> 
            <div class="int-area int-area-select">
<%--				<select name="memJob" required="required">
						<option value="">-- 직업 선택 --</option>
							<c:forEach items="${jobList }" var="job">
								<option value="${job.commCd }">${job.commNm }</option>
							</c:forEach>
					</select> --%>
					<form:select path="memJob">
						<form:option value="">-- 직업 선택 --</form:option>
						<form:options items="${jobList }" itemLabel="commNm" itemValue="commCd"/>
					</form:select>
					<form:errors path="memJob"></form:errors>
				<label for=memJob>JOB</label>	
            </div>
            <div class="int-area int-area-select">
<%--            	<select name="memHobby" required="required">
						<option value="">-- 취미 선택 --</option>
						<c:forEach items="${hobbyList }" var="hobby">
								<option value="${hobby.commCd }">${hobby.commNm }</option>
						</c:forEach>
					</select> --%>
					<form:select path="memHobby">
						<form:option value="">-- 직업 선택 --</form:option>
						<form:options items="${hobbyList }" itemLabel="commNm" itemValue="commCd"/>
					</form:select>
					<form:errors path="memHobby"></form:errors>
				<label for=memHobby>HOBBY</label>	
            </div>
            
            <div class="btn-area">
                <button type="button"  id="btn_join"name="btn_join" onclick="join()">JOIN</button>
            </div>
         </form:form>
    </section>
    
   
<script>
// 사용자 이미지 업로드하는 함수 
function getImageFiles(e) {
	const files = e.currentTarget.files;
	const imagePreview = document.querySelector('.image-preview');
	const file = files[0];
	const reader = new FileReader();
	reader.onload =  function(e){  
		const preview = createElement(e, file);
		let imageLiTag = document.querySelector('.image-preview > li');
		if(imageLiTag){
			imagePreview.removeChild(imagePreview.firstElementChild);
		}
		imagePreview.appendChild(preview);
	};
	reader.readAsDataURL(file); 
}

function createElement(e, file) {
	const li = document.createElement('li');
	const img = document.createElement('img');
	img.setAttribute('src', e.target.result); //img.setAttribute('src', reader.result);
	img.setAttribute('data-file', file.name);
	li.appendChild(img);
	return li;
}

const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.upload');

upload.addEventListener('click', function(e){
	realUpload.click();  
});

realUpload.addEventListener('change', getImageFiles);


// 메일버튼 클릭시 이벤트
$("#mailAuth").on("click", function() {
	console.log("#mailAuth");
	
	// 여기서 메일을 확인할 것이다
	let memMail = $("#memMail");
	console.log("memMail : ", memMail.val())
	
	// 메일 검증
    let checkMemMail = /^[-_a-zA-z0-9]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,4}$/i;
    console.log("checkMemMail: ", checkMemMail.test(memMail.val()) );
    if( ! checkMemMail.test(memMail.val()) ){
    	alert("이메일 양식이 틀립니다. 다시 입력해주세요"); 
    	memMail.val("");    	
    	memMail.next("label").addClass("warning");
       return;
    }
	
	
	
	$.ajax({
		url : "<c:url value='/join/mailAuth'/>"
		,type : "post"
		,data : {"mail" : memMail.val()}
		,success : function(data) {
			// alert("success");
			console.log("data : ", data);
			
			// 인증 확인하는 창 띄우기
			let popupWidth = 600;
			let popupHeight = 150;
			let winWidth = document.body.offsetWidth;
			let winHeight = document.body.offsetHeight;
			let popupX = (winWidth/2) - (popupWidth/2);  
			let popupY = (winHeight/2) - (popupHeight/2);

			if(data){
				let myWin = window.open("<c:url value='/join/mailWindow'/>" 
					,"mywin"
					,"left="+popupX+"px,"
					+"top="+popupY+"px, "
					+"width="+popupWidth+"px, "
					+"height="+popupHeight+"px");
			}
		}
		,error : function() {
			alert("error");
		}
		// ajax 처리하는 동안.. 
		,beforeSend: function() {
			let width = 150;
			let height = 150;
			let top = 50;
			let left = 50;
			$('body').append('<div id="div_ajax_load_image" style="position:absolute; top:' 
				+ top + '%; left:' 
				+ left + '%; width:' 
				+ width + 'px; height:' 
				+ height + 'px; z-index:9999;'
				+'background:transparent; '
				+'filter:alpha(opacity=50); '
				+'opacity:alpha*0.5; '
				+'margin:auto; '
				+'padding:0; ">'
				+'<img src="<c:url value ='/images/ajax_loader6.gif'/>"'
				+'style="width:150px; height:150px;"></div>');
		}
		,complete: function() {
			$("#div_ajax_load_image").remove();
		}
	});
	
});

</script>
    
    
</body>
</html>