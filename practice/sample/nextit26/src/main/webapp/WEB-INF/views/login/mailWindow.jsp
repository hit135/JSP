<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NextIT</title>
</head>
<body>
인증번호 <input type="text" name="authKey"	value="" class="form-control input-sm">
<br>
<button type="button" id="authKeyCompare">메일 인증키 확인</button>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#authKeyCompare").on("click", function(){
		//alert("authKeyCompare");
		//alert(opener.document.getElementById("memMail").value);
		
		// opener를 써서 부모창의 memMail의 value를 가져왔다
		let mail = opener.document.getElementById("memMail").value;
		let authKey = $("input[name='authKey']").val();
		
		$.ajax({
			url: "<c:url value='/join/authKeyCompare'/>"
			,data : {"mail":  mail , "authKey": authKey }
			,success: function(data){
				//alert(data)
				if(!data){
					alert("인증키가 틀립니다. 다시 확인해 주세요");
				}else{
					alert("인증되었습니다.");
					window.close();
				}
			}
			,error : function(e){
				alert("전산실에 문의 부탁드립니다.");
			}
		});
		
	});
});
</script>
</body>
</html>