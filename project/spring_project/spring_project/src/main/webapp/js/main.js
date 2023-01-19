// 제이쿼리 래디 >> 첫 시작시!!
$(document).ready(function(){

  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  });

  /* 포커스에서 벗어날 때 틀리면 안의 글자 삭제 */
  // .parent() >> 부모찾기
  // .childer().eq(0) >> 하위 요소의 0번째 >> eq는 몇번째
  $('.form-control').focusout(function(){

    if($(this).parent().find('.invalid-feedback').is(':visible')){
      $(this).val('');
    }
  });
  
  
	// 출석창 none추가
	$("#attendanceDiv").attr('style', "display:none;");
	// 차트창 nonte추가
	$("#chartDiv").attr('style', "display:none;");
  
  
});


// 로드되면 바텀 창 바뀜
window.onload = function () {

	
    // 바텀 바 로드 // 타이틀 버튼 // 로그인 버트
    let btn = document.getElementById('bottom_bar');
    let mbt = document.getElementById('main_button');
    let bottomBut = document.getElementById('bottom_button');
    btn.style.width = '100%';
    btn.style.height = '60px';
    btn.style.backgroundColor = 'rgb(0, 50, 110)';
    mbt.style.right = '38px';
    
    
    changeOpacity('main_button', 1);
    changeOpacity('title_name', 1);
    changeOpacity('main_gifs', 1);
    changeOpacity('bottom_button', 1);

    // 이미지 로드
    // 함수 포문과 재귀함수 등등 써서 최대한 줄이려 했지만 실패함
    setTimeout("imgRotateId('main_title_img',-20,0.8,240);", 3500); setTimeout("imgRotateId('main_title_img',90,0.8,70);", 4200); setTimeout("imgRotateId('main_title_img',-10,1.5,200);", 4800); setTimeout("imgRotateId('main_title_img',15,0.8,170);", 6000); setTimeout("imgRotateId('main_title_img',0,1,190);", 6500);
    setTimeout('imgRotateId("gif1",-15,3,500);', 1000); setTimeout('imgRotateId("gif1",0,2,500)', 2800); setTimeout('imgRotateId("gif1",0,2,500)', 4600);
    setTimeout('imgRotateId("gif2",-10,2,500);', 1000); setTimeout('imgRotateId("gif2",30,2,300)', 2800); setTimeout('imgRotateId("gif2",0,2,500)', 4600);
    setTimeout('imgRotateId("gif3",-26,1,500);', 1000); setTimeout('imgRotateId("gif3",15,1,110)', 1800); setTimeout('imgRotateId("gif3",0,1,500)', 2600);
    setTimeout('imgRotateId("gif4",24,3,500);', 1000); setTimeout('imgRotateId("gif4",0,2,500)', 3800); setTimeout('imgRotateId("gif4",0,1,500)', 5600);
    setTimeout('imgRotateId("gif5",20,2,500);', 1000); setTimeout('imgRotateId("gif5",-20,1,330)', 2800); setTimeout('imgRotateId("gif5",0,2,500)', 3600);
    setTimeout('imgRotateId("gif6",-20,2,500);', 1000); setTimeout('imgRotateId("gif6",15,2,400)', 2800); setTimeout('imgRotateId("gif6",0,1,500)', 3600);
    setTimeout('imgRotateId("gif7",-15,1,500);', 1000); setTimeout('imgRotateId("gif7",30,1,140)', 1800); setTimeout('imgRotateId("gif7",0,2,500)', 2600);
    setTimeout('imgRotateId("gif8",-30,4,500);', 1000); setTimeout('imgRotateId("gif8",0,2,500)', 4800); setTimeout('imgRotateId("gif8",0,1,500)', 6600);
    setTimeout('imgRotateId("gif9",-25,2,500);', 1000); setTimeout('imgRotateId("gif9",25,1,220)', 2800); setTimeout('imgRotateId("gif9",0,2,500)', 3600);
    
    

}


//  =========================================== 메인의 gif 파일들 동작
let mainImg = document.getElementsByClassName('main_img');
let marginTop = [-200, -420, -280, -520, -500, -220, -270, -530, -600, -220, -800, -400];

// -80 <  x  < 400 , x > 400
// 시간에 따라 반복 동작
// 6초후 멈춤


// 필요한 변수들 선언
// true 일때 up / false일때 down
let unDownImg = true;


// 각도를 조정하는 함수를 만들자
// 피라미터로 get요소를 받고, 각도를 받는다
// 한번
// 아이디로 돌리기
// 여기에 마진값도 주자
function imgRotateId(p_string, p_deg, p_second, p_margin) {
    let getEle = document.getElementById(p_string);
    getEle.style["transition-duration"] = p_second + "s";
    getEle.style["rotate"] = p_deg + "deg";
    getEle.style["margin-top"] = p_margin + "px";
}

// 클래스로 돌리기
function imgRotateClass(p_string, p_deg, p_second) {
    let getEle = document.getElementsByClassName(p_string);
    for (let i = 0; i < getEle.length; i++) {
        let randInt = Math.ceil(Math.random() * 20);
        getEle[i].style["transition-duration"] = p_second + "s";
        getEle[i].style["rotate"] = p_deg + "deg";

    }
}

// 불투명도 바꾸기
function changeOpacity(p_id, p_num) {
    let getEle = document.getElementById(p_id);
    getEle.style["opacity"] = p_num;
}
// display  바꾸기
function displayNone(p_id, p_string) {
    let getEle = document.getElementById(p_id);
    getEle.style["display"] = p_string;
}

// 홈 버튼 함수
function homeBut() {
    window.open( getContextPath() + '/', '_self');
}

// 홈 버튼의 name버튼
function fn_nameOver() {
	// alert("fn_nameOver");
	$("#showMemberPoint").removeAttr('style', "display:none;");
}
function fn_nameOut() {
	// alert("fn_nameOut");
	$("#showMemberPoint").attr('style', "display:none;");
}

// 홈 바텀의 로그인 버튼
function fn_registe() {
    // 메인 이미지들
    changeOpacity('main_contents', 0);
    imgRotateId('main_contents', 0, 1, -650);
    setTimeout('displayNone("main_contents", "None");', 1000);
    let c_reg = document.getElementById('c_registe');
    let s_form = document.getElementById('sign_form');
    c_reg.style['position'] = 'static';
    s_form.style['right'] = '25%';
    s_form.style['top'] = '50%';
    s_form.style['bottom'] = '0px';
    s_form.style['opacity'] = '1';
    s_form.style['width'] = '470px';
    s_form.style['height'] = '450px';
}
// 로그인 버튼 눌렀을 시 condition 동적인 움직임
function fn_registeCon(){
    changeOpacity('c_condition', 0);
    setTimeout("fn_closeCon()", 1000);
    setTimeout('displayNone("b_condition", "none");', 2500); 
}

// 로그인 버튼 애니메이션으로 나오기
function fn_registe_ani() {
    changeOpacity("carouselExampleControls", 0);
    changeOpacity("policyNum", 0);
    setTimeout("closePolicy();", 1400);

    displayNone('sign_form', 'block');
    setTimeout("fn_registe()", 800);
    // 출석창 사라지기
    noneAttend();
    // 차트창 사라지기
    noneChart();
}
// 로그인 버튼 애니메이션으로 들어가기
function fn_registe_close(){
    let c_reg = document.getElementById('c_registe');
    let s_form = document.getElementById('sign_form');
    s_form.style['right'] = '100px';
    s_form.style['transition-duration'] = '1.5s';
    s_form.style['top'] = '100%';
    s_form.style['bottom'] = '10%';
    s_form.style['opacity'] = '0';
    s_form.style['width'] = '10px';
    s_form.style['height'] = '70px';
    setTimeout("displayNone('sign_form','none');", 800);
}



// 로그인 화면 sign in/ sing up
function resetClass(element, classname) {
    element.classList.remove(classname);
}

function fn_signUn() {
    let form = document.getElementsByClassName("form")[0];
    resetClass(form, "signin");
    resetClass(form, "reset");
    form.classList.add("signup");
    document.getElementById("submit-btn").innerText = "Sign Up";
    $("#memName").attr( 'required' );
    $("#memPassCheck").attr( 'required' );
}

function fn_signIn() {
    let form = document.getElementsByClassName("form")[0];
    resetClass(form, "signup");
    resetClass(form, "reset");
    form.classList.add("signin");
    document.getElementById("submit-btn").innerText = "Sign In";
    $("#memName").val("");
    $("#memName").removeAttr( 'required' );
    $("#memPassCheck").removeAttr( 'required' );
}

// 회원가입, 로그인
// signUp, signIn 버튼 클릭
function fn_signUp_btn() {
	// validation 제이쿼리

	if($("#submit-btn").html() == "Sign Up"){
		if($("#memPass").val() != $("#memPassCheck").val() ){
			$("#memPassCheck").val("");
			$("#memPassCheck").focus();
			alert("비밀번호 확인이 틀렸습니다!\n" +
					"비밀번호를 다시한번 확인해주세요!")
		}
	}else if($("#submit-btn").html() == "Sign In"){
		// alert("Sign In");
	}
}



// 로그인 화면 Close
function fn_close() {
    // 로그인 화면 제어
    fn_registe_close();

    // 이미지 제어
    displayNone("main_contents", "block");
    if(!unDownImg){
        setTimeout("changeOpacity('main_contents',1);", 1500);
        setTimeout("imgRotateId('main_contents', 0, 1, 0);", 300);
        comeback_Img();
    }else{
        setTimeout("changeOpacity('main_contents',1);", 500)
        setTimeout("imgRotateId('main_contents', 0, 1, 0);", 500)
    }
}

// 메인의 이미지들 제자리 찾아가는 함수
function comeback_Img(){
    let img_Ele = document.getElementById('main_contents');
    displayNone("main_contents", "block");
    img_Ele.style['transition-duration'] = "1.5s";
    setTimeout("changeOpacity('main_contents',1);", 1300); 
    setTimeout("imgRotateId('main_title_img',540,1,1200);", 100);setTimeout("imgRotateId('main_title_img',0,1,190);", 1100);
    setTimeout('imgRotateId("gif1",360,1,1000)', 100);setTimeout('imgRotateId("gif1",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif2",-180,1,1000)', 100);setTimeout('imgRotateId("gif2",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif3",720,1,1000)', 100);setTimeout('imgRotateId("gif3",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif4",-0,1,1000)', 100);setTimeout('imgRotateId("gif4",0,2,500)', 1100);
    setTimeout('imgRotateId("gif5",420,1,1000)', 100);setTimeout('imgRotateId("gif5",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif6",-200,1,1000)', 100);setTimeout('imgRotateId("gif6",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif7",50,1,1000)', 100);setTimeout('imgRotateId("gif7",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif8",-260,1,1000)', 100);setTimeout('imgRotateId("gif8",0,2,500)', 1100); 
    setTimeout('imgRotateId("gif9",180,1,1000)', 100);setTimeout('imgRotateId("gif9",0,2,500)', 1100);
}

// 메인의 이미지들 떨어지는 함수
function mainImgDown(){
    imgRotateId('main_title_img',180,1,190); setTimeout("imgRotateId('main_title_img',200,1,1000);", 1000);
    setTimeout('imgRotateId("gif1",15,2,1000)', 1000); 
    setTimeout('imgRotateId("gif2",30,2,1000)', 1000); 
    setTimeout('imgRotateId("gif3",45,1,1000)', 1000); 
    setTimeout('imgRotateId("gif4",0,2,1000)', 1000);
    setTimeout('imgRotateId("gif5",10,1,1000)', 1000); 
    setTimeout('imgRotateId("gif6",25,2,1000)', 1000); 
    setTimeout('imgRotateId("gif7",35,1,1000)', 1000); 
    setTimeout('imgRotateId("gif8",10,2,1000)', 1000); 
    setTimeout('imgRotateId("gif9",5,1,1000)', 1000);
    setTimeout("changeOpacity('main_contents',0);", 1000); 
    setTimeout('displayNone("main_contents", "none");',1800);
    unDownImg = false;
}

// 상위 고정 버튼들
// condition 버튼!

// condition 창 제어 함수
function fn_showCon(){
    let b_con = document.getElementById('b_condition');
    b_con.style['width'] = '1280px'
    b_con.style['height'] = '640px'
    b_con.style['top'] = '16%'
    b_con.style['right'] = '18%'
    b_con.style['transition-duration'] = '2s'
    changeOpacity('b_condition', 1)
    
}
// condition 창 닫기
function fn_closeCon(){
    let b_con = document.getElementById('b_condition');
    b_con.style['width'] = '10px'
    b_con.style['height'] = '10px'
    b_con.style['top'] = '0%'
    b_con.style['right'] = '25%'
    b_con.style['transition-duration'] = '1.5s'
    changeOpacity('b_condition', 0)
}
// condition 버튼 클릭
function fn_condition(){
	$("#c_condition").show("fast");
    // police 버튼 사라지기
    changeOpacity("carouselExampleControls", 0);
    changeOpacity("policyNum", 0);
    setTimeout("closePolicy();", 1400);
    // 출석창 사라지기
    noneAttend();
    // 차트창 사라지기
    noneChart();
    // 이미지 다운 
    mainImgDown();
    setTimeout('displayNone("b_condition", "block");', 1700);
    setTimeout('fn_showCon()',1800);
    setTimeout("changeOpacity('c_condition', 1)", 3000);

}

// 상위의 X버튼
function fn_exit(){
    fn_registeCon()
    comeback_Img();
}

// 조건의 입장 버튼
function over_entry(){
    let enter_img = document.getElementById('enter_img');
    enter_img.src = getContextPath()+"/image/search_white.png";
}

function out_entry(){
    let enter_img = document.getElementById('enter_img');
    enter_img.src = getContextPath()+"/image/search_black.png";
}

// 전역에 정책 조건 넣기
let ageInfo = "";
let empmSttsCn = "";
let accrRqisCn = "";
let majrRqisCn = "";

// 정책 조건 입력후 클릭
function go_policy(){
    // alert("go_poicy");
    // $("#b_condition").hide(2000);
    // 셀렉박스 값들 받아오기
    let emp = $("#emp_select");
    let age = $("#age_input");
    let edu = $("#edu_select");
    let maj = $("#maj_select");
    // console.log(emp.val())
    // console.log("age val : ", age.val())
    // 비어있지 않으면
    if(emp.val() != "" && age.val() != "" && edu.val() != "" && maj.val() != ""){
        // 조건인 값들 넣어주고
    	empmSttsCn = emp.val();
    	accrRqisCn = edu.val();
    	majrRqisCn = maj.val();
    	ageInfo = age.val();
        policyAjax(1, 5, empmSttsCn, accrRqisCn, majrRqisCn, ageInfo);
        
        // 창 사라지기
    	$("#c_condition").hide("fast");
        fn_closeCon()
    	// 정책창 나타나기
        setTimeout('displayNone("p_policy", "block");',500); 
        setTimeout("movePolicy()", 600);
        
        // 모든 작업이 끝나면 검색조건창 초기화
        $("#emp_select option:eq(0)").prop("selected", true);
        $("#age_input").val("");
        $("#edu_select option:eq(0)").prop("selected", true);
        $("#maj_select option:eq(0)").prop("selected", true);

    }else{
        alert("모든 항목에 입력해주세요!")
        
    }
    // console.log('$("#emp_select").val() : ', $("#emp_select").val());
}

// police 버튼 관련 !!
// police 버튼 나오기
function movePolicy(){
    let p_pol = document.getElementById('p_policy');
    changeOpacity("carouselExampleControls", 1);
    changeOpacity("policyNum", 1);
    p_pol.style['transition-duration'] = '1.5s';
    p_pol.style['width'] = 'auto';
    p_pol.style['top'] = '230px';
    p_pol.style['right'] = '0%';
    p_pol.style['opacity'] = '1';
}


// police 버튼 들어가기
function closePolicy(){
    let p_pol = document.getElementById('p_policy');
    p_pol.style['transition-duration'] = '1s';
    p_pol.style['width'] = '1px';
    p_pol.style['top'] = '0px';
    p_pol.style['right'] = '-1600px';
    p_pol.style['opacity'] = '0';
    displayNone("p_policy", "none");
}

function fn_policy(){
	// 정책 조건 창 초기화
	resetPolicy()
    // 메인에서 바로 policy 버튼
    mainImgDown();
    setTimeout('displayNone("p_policy", "block");',1500); 
    setTimeout("movePolicy()", 1600);
    // condition 에서 policy 버튼
    fn_registeCon();
    // login 화면에서 policy 버튼
    setTimeout("fn_registe_close();",500);
    // 출석창 사라지기
    noneAttend();
    // 차트창 사라지기
    noneChart();
    
}

// 공공 API 가지고 오기
// card-body 클래스
let card_body = document.getElementsByClassName('card-body');
// console.log("card_body : ",card_body);
// card-body 에 값들 집어 넣기
function get_card_body(){
}

// js contextPath
function getContextPath() {
	let hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};


// 화면 로딩시 아작스로 정책 가져오기
$(document).ready(function(){
    policyAjax(1, 5, empmSttsCn, accrRqisCn, majrRqisCn, ageInfo);
});

// 전역에 policyData 선언
let j_policy_data;

// 비동기 페이지 로딩
function fn_policyNum(e) {
	// console.log("$(e).data('curpage') : ", $(e).data("curpage"));

	// data에 저장한 값 받아오기
	let curPage = $(e).data("curpage");
	let rowSizePerPage = $(e).data("rowsizeperpage");
	
	policyAjax(curPage, rowSizePerPage, empmSttsCn, accrRqisCn, majrRqisCn, ageInfo);
}

function policyAjax(curPage, rowSizePerPage, empmSttsCn, accrRqisCn, majrRqisCn, ageInfo) {
	$.ajax({
        type : "GET", //전송방식을 지정한다 (POST,GET)
        url : getContextPath() + "/json?curPage="+curPage+"&rowSizePerPage="+rowSizePerPage
                                +"&empmSttsCn="+empmSttsCn+"&accrRqisCn="+accrRqisCn
                                +"&majrRqisCn="+majrRqisCn+"&ageInfo="+ageInfo ,
                                //호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
        dataType : "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
        error : function(){
            alert("통신실패!!!!");
        },
        success : function(data){
            // console.log("통신 데이터 값 : " + data);
        	const j_data = JSON.parse(data);
        	// console.log(j_data);
        	j_policy_data = j_data[0];
        	// console.log("🚀 ~ file: main.js:341 ~ j_policy_data", j_policy_data)
        	const j_num_data = j_data[1];
        	
        	// console.log("j_num_data : ", j_num_data.firstPage);
        	// console.log("j_num_data : ", j_num_data.lastPage);
        	$("#policyBody").empty();
        	$("#policyNum").empty();
        	
        	// 카드 그리기
        	for(let i = 0; i < j_policy_data.length; i++){
        		$("#policyBody").append(`
        				<div class="card" onclick="fn_policyView(this)" data-bizId="${j_policy_data[i]["bizId"]}">
	        				<div class="card-body" >
		        				<h5 class="card-title">${j_policy_data[i]["polyBizSjnm"]}</h5><br>
		        				<h6 class="card-subtitle mb-2 text-muted">정책유형 :
		        				${j_policy_data[i]["plcyTpNm"]}</h6>
		        				<p class="card-text">정책 소개 : ${j_policy_data[i]["polyItcnCn"]}</p>
	        				</div>
        				</div>
							`)
        	}
        	
        	// 번호표 그리기
        	// ajax 받을 때 pagingVO도 받자
        	let numAppendText = "";
        	// 꺽새
        	if(j_num_data.firstPage > 10){
        		numAppendText += "<li class='page-item'><a href='#' class='page-link' data-curPage="+(parseInt(j_num_data.firstPage) - 1) +" data-rowSizePerPage="+j_num_data.rowSizePerPage+" onclick='fn_policyNum(this)'>&laquo;</a></li>"
        	}
        	if(j_num_data.curPage != 1){
        		numAppendText += "<li class='page-item'><a href='#' class='page-link' data-curPage="+(parseInt(j_num_data.curPage) - 1) + " data-rowSizePerPage="+j_num_data.rowSizePerPage+" onclick='fn_policyNum(this)'>&lt;</a></li>"
        	}
        	// 번호
        	for(let i = j_num_data.firstPage; i <= j_num_data.lastPage; i++){
        		if(j_num_data.curPage != i){
        			numAppendText += "<li class='page-item'><a href='#' class='page-link' data-curPage="+i+" data-rowSizePerPage="+j_num_data.rowSizePerPage+" onclick='fn_policyNum(this)'>"+i+"</a></li>"
        		}else if(j_num_data.curPage == i){
        			numAppendText += "<li class='page-item active'><a href='#' class='page-link'>"+i+"</a></li>"
        		}
        	}
        	// 꺽새
        	if(j_num_data.lastPage != j_num_data.totalPageCount){
        		numAppendText += "<li class='page-item'><a href='#' class='page-link' data-curPage="+(parseInt(j_num_data.curPage) + 1) +" data-rowSizePerPage="+j_num_data.rowSizePerPage +" onclick='fn_policyNum(this)'>&gt;</a></li>"
        		numAppendText += "<li class='page-item'><a href='#' class='page-link' data-curPage="+(parseInt(j_num_data.lastPage) + 1) +" data-rowSizePerPage="+j_num_data.rowSizePerPage+" onclick='fn_policyNum(this)'>&raquo;</a></li>"
        	}
        	
      	$("#policyNum").append(numAppendText)

        }
    });
}

// 아작스 통신한거 초기화시키기!!!!!!!!
function resetPolicy() {
	ageInfo = "";
	empmSttsCn = "";
	accrRqisCn = "";
	majrRqisCn = "";
	policyAjax(1,5,empmSttsCn, accrRqisCn, majrRqisCn, ageInfo);
}


// 정책페이지 상세 >> 모달 부르기
function fn_policyView(e) {
    $("#exampleModal").modal("show");
    // console.log("j_policy_data : ",j_policy_data);
    // console.log("$(e).data('bizid') : " + $(e).data('bizid'))
    // id값 잘 가져옴
    // 모달에 값 넣어주기
    for(let i = 0; i < j_policy_data.length; i++){
        if(j_policy_data[i]["bizId"] == $(e).data('bizid')){
            // 타이틀
            $("#exampleModalLabel").html(j_policy_data[i]["polyBizSjnm"]);
            // 내용
            let policyModalBodyHtml = "";
            policyModalBodyHtml += 
            `
            <div id="policyViewBody"><div id="policyViewBodyDiv">
            <div>
	            <ol class="large-numbers">
					<li>정책명 : ${j_policy_data[i]["polyBizSjnm"]}</li>
					<li>정책유형 : ${j_policy_data[i]["plcyTpNm"]}</li>
					<li>정책규모 : ${j_policy_data[i]["sporScvl"]}</li>
					<li>정책소개 : ${j_policy_data[i]["polyItcnCn"]}</li>
					<li>지원규모 : ${j_policy_data[i]["sporScvl"]}</li>
					<li>지원내용 : ${j_policy_data[i]["sporCn"]}</li>
					<li>참여요건 - 연령 : ${j_policy_data[i]["ageInfo"]}</li>
            		<li>참여요건 - 취업상태 : ${j_policy_data[i]["empmSttsCn"]}</li>
            		<li>참여요건 - 학력 : ${j_policy_data[i]["accrRqisCn"]}</li>
					<li>참여요건 - 전공 : ${j_policy_data[i]["majrRqisCn"]}</li>
					<li>신청기관명 : ${j_policy_data[i]["cnsgNmor"]}</li>
		        	<li>신청기간 : ${j_policy_data[i]["rqutPrdCn"]}</li>
            		<li>사이트 링크 : <a href=${j_policy_data[i]["rqutUrla"]}>${j_policy_data[i]["rqutUrla"]}</a></li>
                </ol>
            </div>
            </div></div>
            `
            	// console.log(policyModalBodyHtml);
            	$("#policyModalBody").html(policyModalBodyHtml)
        }

    }
}

// 출석버튼 시작!!!!!!!!!!!!!!
function fn_moveAtten() {
	// 달력 목록 가져오기!!!
	calendarInit();
	// alert("fn_moveAtten");
	// 메인에서 바로 attendance 버튼
    mainImgDown();
    // condition 에서 attendance 버튼
    fn_registeCon();
    // login >> attendance 버튼
    fn_registe_close();
    // policy >> attendance 버튼
    changeOpacity("carouselExampleControls", 0);
    changeOpacity("policyNum", 0);
    setTimeout("closePolicy();", 1000);
    // 출석창 나타나기
    setTimeout("blockAttend();", 500);
    // 차트창 사라지기!
    noneChart();
}

// 날짜 정보 가져오기
let date = new Date(); // 현재 날짜(로컬 기준) 가져오기
let utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000); // uct 표준시 도출
let kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
let today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)

let thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
// 달력에서 표기하는 날짜 객체

let currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
let currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
let currentDate = thisMonth.getDate(); // 달력에서 표기하는 일

// kst 기준 현재시간
// console.log(thisMonth);

// 달력에 출석했다! 도장찍을 아작스
function attendCheckAjax(currentMonth) {
	$.ajax({
		  type : "GET"
		, url : getContextPath() + "/attendCheck"
		, dataType : "text"
		, error : function() {
			alert("통신 실패! \n 전산실에 문의해주세요 010-4403-9382");
		}
		, success : function(data) {
			console.log("currentYear : " , currentYear);
			console.log("currentMonth : " , currentMonth);
			console.log("currentDate : " , currentDate);
			
			// console.log(data);
			let attendDates = data.split(",");
			// console.log("attendDates : " , attendDates);
			for(let i = 1; i <= 31; i++ ){
				let tempDate = (((currentMonth + 1) < 10) ? "0" + (currentMonth + 1) : (currentMonth+1) ) + "-" + ((i<10) ? "0"+i : i);
				// console.log("tempDate : " , tempDate);
				for(j = 0; j < attendDates.length; j++){
					if(attendDates[j] == tempDate){
						// 여기서 i값이 출석한 날짜지!
						let currentMonthDate = $(".dates .current");
						// console.log("currentMonthDate : " , currentMonthDate)
				       currentMonthDate[i-1].classList.add('attendComplete');
					}
				}
			}
		}
	});
}



// 달력 시작!
function calendarInit() {

	// 캘린더 렌더링
	renderCalender(thisMonth);
	
	function renderCalender(thisMonth) {
	
	    // 렌더링을 위한 데이터 정리
	    currentYear = thisMonth.getFullYear();
	    currentMonth = thisMonth.getMonth();
	    currentDate = thisMonth.getDate();
	
	    // 이전 달의 마지막 날 날짜와 요일 구하기
	    let startDay = new Date(currentYear, currentMonth, 0);
	    let prevDate = startDay.getDate();
	    let prevDay = startDay.getDay();
	
	    // 이번 달의 마지막날 날짜와 요일 구하기
	    let endDay = new Date(currentYear, currentMonth + 1, 0);
	    let nextDate = endDay.getDate();
	    let nextDay = endDay.getDay();
	
	    // console.log(prevDate, prevDay, nextDate, nextDay);
	
	    // 현재 월 표기
	    $('.year-month').text(currentYear + '.' + (currentMonth + 1));
	
	    // 렌더링 html 요소 생성
	    calendar = document.querySelector('.dates')
	    calendar.innerHTML = '';
	    
	    // 지난달
	    for (let i = prevDate - prevDay + 1; i <= prevDate; i++) {
	        calendar.innerHTML = calendar.innerHTML + '<div class="day prev disable">' + i + '</div>'
	    }
	    // 이번달
	    for (let i = 1; i <= nextDate; i++) {
	        calendar.innerHTML = calendar.innerHTML + '<div class="day current">' + i + '</div>'
	    }
	    // 다음달
	    for (let i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
	        calendar.innerHTML = calendar.innerHTML + '<div class="day next disable">' + i + '</div>'
	    }
	
	    // 오늘 날짜 표기
	    if (today.getMonth() == currentMonth) {
	        todayDate = today.getDate();
	        let currentMonthDate = document.querySelectorAll('.dates .current');
	        currentMonthDate[todayDate -1].classList.add('today');
	    }
	    
	    console.log("currentMonth + 1 이거 왜 두번: ", currentMonth+1);
	    console.log("currentDate  이거 왜 두번 : ", currentDate);
	    // 여기서 출석한 날짜들 도장찍어줘야함
	    // ajax로 mem_sysDate에서 memAttendDate를 받아와야함
	    // 그러고 ,를 기준으로 나눠서 배열 포문 돌려서
	    // attendComplete class추가
	    
	    attendCheckAjax(currentMonth);
	}
	
	// 이전달로 이동
	$('.go-prev').on('click', function() {
	    thisMonth = new Date(currentYear, currentMonth - 1, 1);
	    renderCalender(thisMonth);
	    
	});
	
	// 다음달로 이동
	$('.go-next').on('click', function() {
	    thisMonth = new Date(currentYear, currentMonth + 1, 1);
	    renderCalender(thisMonth); 
	    
	});
	
}

// 출석창 나타나기!!
function blockAttend() {
	$("#attendanceDiv").css('display', 'block');
	setTimeout(() => {
		$("#attendanceDiv").css('opacity', '1');
	}, 1000);
}
//출석창 사라지기!!
function noneAttend() {
	$("#attendanceDiv").attr('style', "opacity:0;");
	
	// opacity가 1이 아니면 바로 none
	console.log($("#attendanceDiv").css('opacity'));
	if($("#attendanceDiv").css('opacity') == 0 ){
		$("#attendanceDiv").attr('style', "display:none;");
	}else{
		setTimeout(() => {
			$("#attendanceDiv").attr('style', "display:none;");
		}, 2000);
	}
}

// 출석했는지 확인을 위한 날짜 포맷 변경
let dateFormat = date.getFullYear() + "-"
               + ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
               + (date.getDate() < 9 ? "0" + date.getDate() : date.getDate());
// console.log("dateFormat : " , dateFormat);

// 출석하기 버튼 클릭!
function fn_goAttendance() {
	// alert("fn_goAttendance")
	// 오늘 날짜에 출석 도장!
	if (today.getMonth() == currentMonth) {
	        todayDate = today.getDate();
	        let currentMonthDate = $(".dates .current");
	        currentMonthDate[todayDate -1].classList.add('attendComplete');
	        // console.log("currentMonthDate : ", currentMonthDate);
	    }
	// 비동기로 100p 회원에 추가하기
	$.ajax({
		  type : "GET"
		, url : getContextPath() + "/attendance?dateFormat="+dateFormat
		, dataType : "text"
		, error : function() {
			alert("통신 실패! \n 전산실에 문의해주세요 010-4403-9382");
		}
		, success : function(data) {
			console.log(data);
			if(data == 'fail'){
				alert("오늘은 이미 출석하셨습니다!")
			}else if(data == 'success'){
				alert("출석완료! 100P 획득!")
			}
		}
	});
	
}

// 출석끝!!!!!!!!!!
// 차트시작!!!!!!!!!

// 차트창 나타나기!!
function blockChart() {
	$("#chartDiv").css('display', 'block');
	setTimeout(() => {
		$("#chartDiv").css('opacity', '1');
	}, 1000);
}
// 차트창 사라지기!!
function noneChart() {
	$("#chartDiv").attr('style', "opacity:0;");
	setTimeout(() => {
		$("#chartDiv").attr('style', "display:none;");
	}, 2000);
	console.log("casesChart : " , casesChart);
	if(casesChart != null){
		setTimeout(() => {
			casesChart.destroy();
			$(".gradeTableBody").empty();
		}, 2000);
		
	}
}

// 차트버튼 클릭시!
function fn_moveChart() {
	// 메인 이미지 다운
    mainImgDown();
    // condition 다운
    fn_registeCon();
    // login 다운
    fn_registe_close();
    // policy 다운
    changeOpacity("carouselExampleControls", 0);
    changeOpacity("policyNum", 0);
    setTimeout("closePolicy();", 1000);
    // 출석창 다운
    noneAttend();
    // 차트창 나오기
    setTimeout("blockChart();", 500);
    // ajax통신, 차트, 테이블 그리기
    memberAjax();
}

// 전역에 차트를 지우기 위해 변수 하나 선언
let casesChart;

// 차트 그리는 함수!
function drawChart(memberList) {
	// 배열에 넣을 변수
	let lv1 = lv2 = lv3 = lv4 = lv5 = 0;
	let pointData = [];
	// memberList for문 돌려서 등급만큼 배열에 넣기
	for(let i = 0; i < memberList.length; i++){
		if(memberList[i]["memPoint"] < 500){
			lv1++;
		}else if(500 <= memberList[i]["memPoint"] && memberList[i]["memPoint"] < 1000){
			lv2++;
		}else if(1000 <= memberList[i]["memPoint"]   && memberList[i]["memPoint"] < 1500){
			lv3++;
		}else if(1500 <= memberList[i]["memPoint"] && memberList[i]["memPoint"] < 2000){
			lv4++;
		}else if(2000 <= memberList[i]["memPoint"]){
			lv5++;
		}
	}
	// 배열에 집어넣기
	pointData.push(lv1, lv2, lv3, lv4, lv5);
	
	// 차트 그리기
	const ctx = document.getElementById('myChart');
	casesChart = new Chart(ctx, {
	    type: 'pie',    // bar면 막대그래프, line이면 꺾은선그래프, pie 원형그래프, doughnut은 원형그래프에 빵꾸, rader 방사형 그래프
	    data: {
	        // 라벨.. x축 각각의 변수명
	        labels: ['lv5', 'lv4', 'lv3', 'lv2', 'lv1'],
	        datasets: [{
	            label: '멤버 등급 차트',
	            data: pointData,
	            borderWidth: 2,
	            // rgb, 16진수, 컬러명 다 된다!
	            }]
	        },
	    options: {
	        responsive: true,
	        maintainAspectRatio: false,
	        plugins: {
	        	labels: {
	        		render: 'image',
	     	        images:[{
	     	        	src: getContextPath()+"/image/grade/lv1.png",
	     	        	height: 50,
	     	        	width: 50
	     	        },
	     	        {
	     	        	src: getContextPath()+"/image/grade/lv2.png",
	     	        	height: 50,
	     	        	width: 50
	     	        },
	     	        {
	     	        	src: getContextPath()+"/image/grade/lv3.png",
	     	        	height: 50,
	     	        	width: 50
	     	        },
	     	        {
	     	        	src: getContextPath()+"/image/grade/lv4.png",
	     	        	height: 50,
	     	        	width: 50
	     	        },
	     	        {
	     	        	src: getContextPath()+"/image/grade/lv5.png",
	     	        	height: 50,
	     	        	width: 50
	     	        },
	     	        ]
	        	}
	        }
	    }
	});
}

// 테이블 그리는 함수
function drawTable(memberList) {
	for(let i = 0; i < memberList.length; i++){
		$(".gradeTableBody").append(
				`<tr class=pointTr${i}>
		        	<td>${memberList[i]["rank"]}</td>
		        	<td>${memberList[i]["memName"]}</td>
		        	<td>${memberList[i]["memPoint"]}</td>
		        </tr>`
		        );
		if(memberList[i]["memPoint"] < 500){
			$(".pointTr"+i).append(
					`<td><img src=`+getContextPath()+`/image/grade/lv1.png></td>`)
		}else if(500 <= memberList[i]["memPoint"] && memberList[i]["memPoint"] < 1000){
			$(".pointTr"+i).append(
					`<td><img src=`+getContextPath()+`/image/grade/lv2.png></td>`)
		}else if(1000 <= memberList[i]["memPoint"]   && memberList[i]["memPoint"] < 1500){
			$(".pointTr"+i).append(
					`<td><img src=`+getContextPath()+`/image/grade/lv3.png></td>`)
		}else if(1500 <= memberList[i]["memPoint"] && memberList[i]["memPoint"] < 2000){
			$(".pointTr"+i).append(
					`<td><img src=`+getContextPath()+`/image/grade/lv4.png></td>`)
		}else if(2000 <= memberList[i]["memPoint"]){
			$(".pointTr"+i).append(
					`<td><img src=`+getContextPath()+`/image/grade/lv5.png></td>`)
		}
	}
	
}


// 차트와 테이블을 위한 멤버 정보 가져오는 ajax
function memberAjax() {
	$.ajax({
		  type : "GET"
		, url : getContextPath() + "/memberList"
		, dataType : "text"
		, error : function() {
			alert("통신 실패! \n 전산실에 문의해주세요 010-4403-9382");
		}
		, success : function(data) {
			// console.log("data : ", data);
			let memberList = JSON.parse(data);
			console.log("memberList ajax : ", memberList);
			
		    // 차트창 그리기
		    drawChart(memberList);
		    
		    // 테이블 그리기
		    drawTable(memberList);
		    
		}
	});
}


// 여기서부터는 멤버들을 관리하기 위한 페이지
function fn_forwordMembers(){
	location.href =  ""
}





