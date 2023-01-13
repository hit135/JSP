<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/memberList.css">
<link href="${pageContext.request.contextPath }/realgrid/realgrid-style.css" rel="stylesheet" />
<style>
#realgrid {
  width: 1080px;
  height: 500px;
}
.div_board_write{
  top: -20px;
}
.div_board_write {
    width: 150px;
    left: 490px;
}
</style>
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
                <h1>회원 목록</h1>
            </div>
			<c:if test="${bnf ne null or de ne null}">
				<div class="alert alert-warning">
					목록을 불러오지 못하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850
				</div>	
				<div class="div_button">
					<input type="button" onclick="history.back();" value="뒤로가기">
				</div>
			</c:if>
            <c:if test="${bnf eq null and de eq null}">
            	<div class="div_search">
					<form name="search" action="${pageContext.request.contextPath}/member/memberList" method="post">
						<input type="hidden" name="curPage" value="${searchVO.curPage}">
						<input type="hidden" name="rowSizePerPage" value="${searchVO.rowSizePerPage}">
						<div>
							<label for="id_searchType">검색</label>
							&nbsp;&nbsp;
							<select id="id_searchType" name="searchType">
								<option value="ID" ${searchVO.searchType eq "ID" ? "selected='selected'" : ""}>아이디</option>
								<option value="NM" ${searchVO.searchType eq "NM" ? "selected='selected'" : ""}>이름</option>
								<option value="HP" ${searchVO.searchType eq "HP" ? "selected='selected'" : ""}>휴대폰</option>
							</select>
							<input type="text" name="searchWord" value="${searchVO.searchWord }" placeholder="검색어">
							&nbsp;&nbsp;&nbsp;&nbsp;	
							
							<label for="id_searchJob">직업</label>
							&nbsp;&nbsp;
							<select id="id_searchJob" name="searchJob">
								<option value="">-- 전체 --</option>
								<c:forEach items="${jobList}" var="jobCode">
									<option value="${jobCode.commCd}" ${searchVO.searchJob eq jobCode.commCd ? "selected='selected'" : "" } >${jobCode.commNm}</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<label for="id_searchHobby">취미</label>
							&nbsp;&nbsp;
							<select id="id_searchHobby" name="searchHobby">
								<option value="">-- 전체 --</option>
								<c:forEach items="${hobbyList}" var="hobbyCode">
									<option value="${hobbyCode.commCd}" ${searchVO.searchHobby eq hobbyCode.commCd ? "selected='selected'" : "" }>${hobbyCode.commNm}</option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<button type="submit">검 색 </button>
							<button type="button" id="id_btn_reset" >초기화</button>
						</div>
					</form>
				</div>  	
            	
            	<div class="rowSizePerPage">
					<div>
						전체 ${searchVO.totalRowCount } 건 조회
						<select id="id_rowSizePerPage" name="rowSizePerPage">
							<c:forEach begin="10" end="50" step="10" var="i">
								<option value="${i }" ${searchVO.rowSizePerPage eq i ? "selected='selected'" : "" }>${i }</option>
							</c:forEach>
						</select>
					</div>
				</div>
            	
            	<!-- 그리드 -->
				<div id = "realgrid"></div>
	
	
	              <!-- paging -->
	            <div class="div_paging">
	              
	                <div class="div_board_write">
	                		<input type= "button" onclick ="fn_commit();" value="수정">	
	                		<input type= "button" onclick ="fn_delete();" value="삭제">	
	                		<input type= "button" onclick ="fn_excelExport();" value="엑셀">	
	                </div>
	              
	              
	            </div>
           	</c:if>
        </div>
    </div>
    
<!-- RealGrid -->
<script src="${pageContext.request.contextPath }/realgrid/realgrid-lic.js"></script>
<script src="${pageContext.request.contextPath }/realgrid/realgrid.2.6.0.min.js"></script>
<script src="${pageContext.request.contextPath }/realgrid/jszip.min.js"></script>
<script type="text/javascript">
/* let data = [
    {
		memId: "nextit11",
		memName: "넥스트아이티11",
		memHp: "01088976516",
		memJob: "은행원",
		memHobby: "등산",
		memMileage: "0",
		memJoinDate: "2021-01-16",
    },
    {
		memId: "nextit12",
		memName: "넥스트아이티12",
		memHp: "01088976516",
		memJob: "은행원",
		memHobby: "등산",
		memMileage: "0",
		memJoinDate: "2021-01-16",
    }
];
 */
let fields = [
	{
	  fieldName: "memId",
	  dataType: "text"
	},
	{
	  fieldName: "memName",
	  dataType: "text"
	},
	{
	  fieldName: "memHp",
	  dataType: "text"
	},
	{
	  fieldName: "memJob",
	  dataType: "text"
	},
	{
	  fieldName: "memHobby",
	  dataType: "text"
	},
	{
	  fieldName: "memMileage",
	  dataType: "number"
	},
	{
	  fieldName: "memJoinDate",
	  dataType: "datetime",
	  datetimeFormat: "yyyy-MM-dd",
	  amText: "오전",
	  pmText: "오후"
	}
];
 
let columns = [
	{
	  name: "memId",
	  fieldName: "memId",
	  width: "155",
	  header: {
	    text: "아이디"
	  }
	},
	{
	  name: "memName",
	  fieldName: "memName",
	  width: "200",
	  header: {
	    text: "이름"
	  }
	},
	{
	  name: "memHp",
	  fieldName: "memHp",
	  width: "150",
	  header: {
	    text: "휴대폰"
	  },
	  styleName: "right-column"
	},
	{
	  name: "memJob",
	  fieldName: "memJob",
	  width: "150",
      //드롭다운  (가이드-> 그리드편집 -> 기본편집기(https://docs.realgrid.com/guides/editing/editors#main-content))
	  lookupDisplay: true,
	  values: ["JB01", "JB02", "JB03", "JB04", "JB05", "JB06", "JB07", "JB08", "JB09"],
	  labels: ["주부", "은행원", "공무원", "축산업", "회사원", "농업", "자영업", "학생", "교사"],
	  editor: {
		    "type": "dropdown",
		    "dropDownCount": 4,
		    "domainOnly": true,
		    "textReadOnly": true
	  },		
	  header: {
	    text: "직업"
	  }
	},
	{
	  name: "memHobby",
	  fieldName: "memHobby",
	  width: "100",
	  //드롭다운  (가이드-> 그리드편집 -> 기본편집기(https://docs.realgrid.com/guides/editing/editors#main-content))
	  lookupDisplay: true,
	  values: ["HB01", "HB02", "HB03", "HB04", "HB05", "HB06", "HB07", "HB08", "HB09", "HB10", "HB11", "HB12", "HB13", "HB14"],
	  labels: ["서예", "장기", "수영", "독서", "당구", "바둑", "볼링", "스키", "만화", "낚시", "영화감상", "등산", "개그", "카레이싱"],
	  editor: {
		    "type": "dropdown",
		    "dropDownCount": 4,
		    "domainOnly": true,
		    "textReadOnly": true
	  },
	  header: {
	    text: "취미"
	  }
	},
	{
	  name: "memMileage",
	  fieldName: "memMileage",
	  width: "100",
	  header: {
	    text: "마일리지"
	  }
	},
	{
	  name: "memJoinDate",
	  fieldName: "memJoinDate",
	  width: "150",
	  header: {
	    text: "가입일자"
	  }
	}
];
/* let container, provider, gridView;
document.addEventListener('DOMContentLoaded', function () {
	container = document.getElementById('realgrid');
	provider = new RealGrid.LocalDataProvider(false);  //(데이터를 담기 위한) LocalDataProvider 클래스. DataProviderBase 를 상속한다.
	gridView = new RealGrid.GridView(container);	//(데이터를 보여주기 )GridView 클래스, GridBase 의 자식 클래스이다.
	gridView.setDataSource(provider);		// setDataSource() 함수를 통해 GridView와 DataProvider가 연결됩니다.
	
	
	provider.setFields(fields); //필드생성
	gridView.setColumns(columns); //컬럼생성
	
	//데이터 채우기
	provider.setRows(data); 	
	gridView.refresh();		
	
	gridView.displayOptions.rowHeight = 30;	//행 높이 조절			 	
	gridView.displayOptions.syncGridHeight="always"; //행개수에따라 높이맞춤 			
	gridView.columnByName("memId").editable = false; //memId는 수정 불가			
	gridView.columnByName("memJoinDate").editable = false; //memJoinDate는 수정불가		
	gridView.checkBar.exclusive = false; //false인경우 체크박스 , true인경우 라디오버튼	
	
});
 */
 let container, provider, gridView;
 document.addEventListener('DOMContentLoaded', function () {
 	container = document.getElementById('realgrid');
 	provider = new RealGrid.LocalDataProvider(false);  //(데이터를 담기 위한) LocalDataProvider 클래스. DataProviderBase 를 상속한다.
 	gridView = new RealGrid.GridView(container);	//(데이터를 보여주기 )GridView 클래스, GridBase 의 자식 클래스이다.
 	gridView.setDataSource(provider);		// setDataSource() 함수를 통해 GridView와 DataProvider가 연결됩니다.
 	
 	
 	provider.setFields(fields); //필드생성
 	gridView.setColumns(columns); //컬럼생성
 	
 	//데이터 채우기
 	//provider.setRows(data); 	
 	//gridView.refresh();		
 	
 	gridView.displayOptions.rowHeight = 30;	//행 높이 조절			 	
 	gridView.displayOptions.syncGridHeight="always"; //행개수에따라 높이맞춤 			
 	gridView.columnByName("memId").editable = false; //memId는 수정 불가			
 	gridView.columnByName("memJoinDate").editable = false; //memJoinDate는 수정불가		
 	gridView.checkBar.exclusive = false; //false인경우 체크박스 , true인경우 라디오버튼	
 	
 	
 	// 수정버튼 클릭시 커밋을 호출 시키면서 아래 코드가 실행됨
 	provider.onRowUpdating = function(provider, row) {
 		let result = confirm("수정하시겠습니까?")
 		if(result){
 			let item = gridView.getEditingItem(); // 현재 편집 중인 행 정보와 값을 가져옵니다.
 			console.log("item.values.memMileage", typeof(item.values["memMileage"]) );
 			console.log("item.values.memMileage", isNaN(item.values["memMileage"]) );
 		 
 			if (item) {
 				if ( isNaN( item.values["memMileage"])   ) {  //isNaN 은 숫자이면 false를 리턴
 					setTimeout(function() {
 					  alert("Mileage 입력값은 숫자이어야 합니다.");
 					}, 10);
 					return false; // false를 리턴하면 DataProvider에 저장되지 않습니다.
 				}
 			}
 			return true;
 		}else{
 			return false;
 		}
	};
	
	// provider.onRowUpdating = function(provider, row)이 처리되면 아래 코드가 실행
	provider.onRowUpdated = function(provider, row) {
		let rowData = provider.getJsonRow(row);
		console.log("rowData :", rowData);
		console.log("json_rowData :", JSON.stringify(rowData));
		
		//1차로 데이터 보내기전에 서버단과 연결해보기
		$.ajax({
			url: "<c:url value='/member/memberGridUpdate'/>"
			,type: "post"
			,data: JSON.stringify(rowData)
			,contentType : "application/json; charset=utf-8;"
			,success : function(data){
				// alert("success");
				console.log("data : ", data);
				if(data){
					alert("수정되었습니다.")
					gridView.commit();	// 그리드에서 편집을 완료할 때에는 gridView.commit()를 호출히야된다
				}else{
					alert("수정 실패했습니다. 전산실에 문의 부탁드립니다. 042-719-8850")
				}
			}
			,error : function(e){
				 console.log("error : ", e.status());
				 alert("수정 실패하였습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
			}
		});
	};	
 	
 	
 });
$(function(){
	fn_memberGrid();
});
let sf = $("form[name='search']");
function fn_memberGrid() {
	console.log("sf.serialize():" , sf.serialize());
	
	/* let sendData = [];
	sendData.push($("input[name='curPage']").val());
	sendData.push($("input[name='rowSizePerPage']").val());
	sendData.push($("input[name='searchType']").val());
	sendData.push($("input[name='searchWord']").val());
	sendData.push($("input[name='searchJob']").val());
	sendData.push($("input[name='searchHobby']").val());
	console.log("sendData:" , sendData); */
	
	$.ajax({
		url: "<c:url value='/member/memberGrid'/>"
		,type:"post"
		//,data: {"sendData": sendData}
		, data: sf.serialize()
		,success:function(data){
			//alert("success");
			console.log("data:" , data);
			
			let gridData = data.memberList;
			
			provider.setRows(gridData);
			gridView.refresh();
			$(".div_paging > ul").remove(); // 새로 그리기위해 지워줌
			let paging_str = '';
			paging_str +=		'<ul class="pagination">';
			if(data.firstPage > 10){
				paging_str +='		<li><a href="javascript:fn_paging(' + (data.firstPage-1) +')">&laquo;</a></li> ';
			}
			if(data.curPage != 1){
				paging_str +='		<li><a href="javascript:fn_paging(' + (data.curPage-1) +')">&lt;</a></li> ';
			}
			for(let i = data.firstPage; i<= data.lastPage; i++){
				if(data.curPage != i){
					paging_str +='	<li><a href="javascript:fn_paging(' + (i) +')"> '+ i +'</a></li> ';
				}else{
					paging_str +='	<li class="curPage_a">'+ i +'</li> ';
				}
			}
			if(data.lastPage != data.totalPageCount ){
				paging_str +='		<li><a href="javascript:fn_paging(' + (data.curPage+1) +')">&gt;</a></li> ';
				paging_str +='		<li><a href="javascript:fn_paging(' + (data.lastPage+1) +')">&raquo;</a></li> ';
			}
			paging_str +='		</ul> ';
			$(".div_paging").prepend(paging_str);  //엑셀 버튼 앞에 위치시키기위해서 prepand 사용
		}
		,error:function(){
			alert("에러가 발생했습니다. 전산실에 문의 부탁드립니다. 042-719-8850");
			console.log("e.status : ", e.status);
		}
	});
}

// 페이징 처리
function fn_paging(curPage) {
	console.log("curPage :", curPage);
	$("input[name='curPage']").val(curPage);
	fn_memberGrid();
}

// 목록 개수
$("#id_rowSizePerPage").change(function() {
	sf.find("input[name='curPage']").val(1)
	sf.find("input[name='rowSizePerPage']").val( $(this).val() )
	
	fn_memberGrid();
});

// 리셋 버튼
$('#id_btn_reset').click(function() {
	sf.find("select[name='searchType'] option:eq(0)").attr("selected", "selected");	
	sf.find("select[name='searchJob'] option:eq(0)").prop("selected", "selected");
	sf.find("select[name='searchHobby'] option:eq(0)").prop("selected", "selected");
	sf.find("input[name='searchWord']").val("");
	sf.find("input[name='curPage']").val(1);
	sf.find("input[name='rowSizePerPage']").val(10);
	$("#id_rowSizePerPage").val(10);
	
	// sf.submit();
	fn_memberGrid();
});  

// 수정 버튼
function fn_commit() {
	console.log("fn_commit");
	
	// 수정하려면 그리드뷰라는 것을 실행하면 된다
	gridView.commit();
	
}

// 삭제 버튼
function fn_delete() {
	console.log("fn_delete");
	
	// 그리드에서 이렇게 쓰라고 명시해둠
	// 그리드에서 체크해둔것을 알 수 있음
	let checkedRow = gridView.getCheckedRows();
	console.log("checkedRow : ", checkedRow);
	
	if( !checkedRow.length > 0){
		alert("선택된 행이 없습니다. 삭제하고자 하는 행을 선택해주세요");
		return;
	}
	
	let memId_arr = []
	for(let i = 0; i < checkedRow.length; i++){
		// provider.getValues(checkedRow[i]) 하면 그 행의 밸류들을 배열로 받음
		let rows = provider.getValues(checkedRow[i]);
		// rows[0]이 memId 이므로 0번째 뽑음
		memId_arr.push(rows[0]);
	}
	console.log("memId_arr : ", memId_arr);
	
	$.ajax({
		url : "<c:url value='/member/memberGridMultiDelete'/>"
		,type:"post"
		,data: {memIdArr : memId_arr}
		,success: function(data) {
			console.log("data : ", data);
			
			if(data){
				alert("회원 정보가 삭제되었습니다.");
				fn_memberGrid();
			}else{
				alert("회원 정보 삭제에 실패하였습니다. 전산실에 문의 부탁드립니다. 042)719-8850");
			}
		}
		,error: function(e) {
			console.log("e.status : ", e.status);
			alert("회원 정보 삭제에 실패하였습니다. 전산실에 문의 부탁드립니다. 042)719-8850");
		}
	});
}

function fn_excelExport() {
	console.log("fn_excelExport");
	
	//그리드 데이터 액셀 내보내기 https://docs.realgrid.com/guides/excels/excel-export
	let excelType = "false"; //MS Excel 2007(.xlsx)
	let showProgress = "false"; //프로그래스바 표시 여부  (기본값: false)
	let indicator = "default"; //indicator(기본값:default)
	let header = "default"; //header(기본값:default)
	let footer = "default"; //fotter  포함 (기본값:default)

	gridView.exportGrid({
		 type: "excel",
		 target: "local",
		 fileName: "NextIT회원목록.xlsx",
		 lookupDisplay: true,				//드롭다운 label값으로 표기
		 showProgress: showProgress,
		 progressMessage: "엑셀 Export중입니다.",
		 indicator: indicator,
		 header: header,
		 footer: footer,
		 compatibility: excelType,
		 done: function () {  //내보내기 완료 후 실행되는 함수
			 //alert("done excel export")
		 }
	 });
	
}




</script>