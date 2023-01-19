<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
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
</div>

<!-- RealGrid -->
<script src="${pageContext.request.contextPath }/realgrid/realgrid-lic.js"></script>
<script src="${pageContext.request.contextPath }/realgrid/realgrid.2.6.0.min.js"></script>
<script src="${pageContext.request.contextPath }/realgrid/jszip.min.js"></script>
<script type="text/javascript">

// 그리드에 채울 칼럼 설정
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
	  fieldName: "memPoint",
	  dataType: "int"
	},
	{
	  fieldName: "memRole",
	  dataType: "text"
	},
	{
	  fieldName: "rank",
	  dataType: "int"
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
	  name: "memPoint",
	  fieldName: "memPoint",
	  width: "150",
	  header: {
	    text: "포인트"
	  },
	  styleName: "right-column"
	},
	{
	  name: "memRole",
	  fieldName: "memRole",
	  width: "150",
      //드롭다운  (가이드-> 그리드편집 -> 기본편집기(https://docs.realgrid.com/guides/editing/editors#main-content))
	  lookupDisplay: true,
	  values: ["ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"],
	  labels: ["관리자", "매니저", "회원"],
	  editor: {
		    "type": "dropdown",
		    "dropDownCount": 3,
		    "domainOnly": true,
		    "textReadOnly": true
	  },		
	  header: {
	    text: "권한"
	  }
	},
	{
	  name: "rank",
	  fieldName: "rank",
	  width: "100",
	  header: {
	    text: "순위"
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

// 테스트를 위한 데이터
/* let data = [
    {
		memId: "nextit11",
		memName: "넥스트아이티11",
		memPoint: 100,
		memRole: "관리자",
		rank: 2,
		memJoinDate: "2021-01-16"
    },
    {
		memId: "nextit12",
		memName: "넥스트아이티12",
		memPoint: 200,
		memRole: "관리자",
		rank: 1,
		memJoinDate: "2021-01-13"
    }
];

// 그리드에 그리기
let container, provider, gridView;
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



</script>


</body>
</html>