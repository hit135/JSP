<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%-- ${replyPagingVO } --%>
<c:forEach items="${replyPagingVO.replyList}" var="reply">
	<div class="replyList_row" data-reNo="${reply.reNo }">
		<div><span class="reply_span"></span></div>
		<div>${reply.reMemId}</div>
		<div>${reply.reRegDate}</div>
		<c:if test="${reply.reMemId == memberVO.memId }">
			<div class="reply_button">
				<button type="button" name="btn_reply_edit">수정</button>
				<button type="button" name="btn_reply_delete">삭제</button>
			</div>
		</c:if>
		<div class="reply_content">
			<pre>${reply.reContent}</pre>
		</div>
	</div>
</c:forEach>
 <div class="div_paging">
	<ul class="pagination">
		<c:if test="${replyPagingVO.firstPage gt 10 }">
            <li><a href="#none" onclick="fn_paging('${replyPagingVO.firstPage-1 }')">&laquo;</a></li>
        </c:if> 
              
		<c:if test="${replyPagingVO.curPage ne 1 }">
			<li><a href="#none" onclick="fn_paging('${replyPagingVO.curPage-1 }')">&lt;</a></li>
		</c:if>
              
		<c:forEach begin="${replyPagingVO.firstPage }" end="${replyPagingVO.lastPage }" step="1" var="i"> 
			<c:if test="${replyPagingVO.curPage ne i}">
				<li><a href="#none" onclick="fn_paging('${i}')">${i}</a></li>
			</c:if>
			<c:if test="${replyPagingVO.curPage eq i }">
				<li class="curPage_a">${i}</li>
			</c:if>
		</c:forEach>
             
		<c:if test="${replyPagingVO.lastPage ne replyPagingVO.totalPageCount }">
			<li><a href="#none" onclick="fn_paging('${replyPagingVO.curPage +1 }')">&gt;</a></li>
			<li><a href="#none" onclick="fn_paging('${replyPagingVO.lastPage +1 }')">&raquo;</a></li>
		</c:if>
	</ul>
</div>


<script type="text/javascript">
let lastPage = "${replyPagingVO.lastPage}";
console.log(parseInt(lastPage));
if( parseInt(lastPage)> 1 ){
	console.log("1보다큼");
	$("#reply_list_div").css("min-height", "800px");
}
</script>


