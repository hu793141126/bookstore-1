<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

当前第 ${requestScope.page.pageNum} 页&nbsp;
<c:choose>
	<c:when test="${requestScope.page.pageNum>1}">
		<a href="${requestScope.page.url}?method=list&category_id=${param.category_id}&pageNum=${requestScope.page.pageNum-1}">上一页</a>&nbsp;
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</c:otherwise>
</c:choose>
<c:forEach var="pageNum" begin="${requestScope.page.pageStartNum}" end="${requestScope.page.pageEndNum}" step="1">
	<c:choose>
		<c:when test="${pageNum==requestScope.page.pageNum}">
			${pageNum}
		</c:when>
		<c:otherwise>
			<a href="${requestScope.page.url}?method=list&category_id=${param.category_id}&pageNum=${pageNum}">${pageNum}</a>
		</c:otherwise>
	</c:choose>&nbsp;
</c:forEach>
<c:choose>
	<c:when test="${requestScope.page.pageNum<requestScope.page.pageCount}">
		<a href="${requestScope.page.url}?method=list&category_id=${param.category_id}&pageNum=${requestScope.page.pageNum+1}">下一页</a>&nbsp;
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</c:otherwise>
</c:choose>
<input type="text" size="3" id="pgNum"><input type="button" value="转到" onclick="doSubmit(document.getElementById('pgNum'))">&nbsp;
共 ${requestScope.page.pageCount} 页&nbsp;
共 ${requestScope.page.totalRecords} 条纪录

<script type="text/javascript">
	function doSubmit(input) {
		var pgNum = input.value;
		if (pgNum == null || pgNum == "") {
			alert("请输入页码！");
			return;
		}
		if (!pgNum.match("\\d+")) {
			alert("请输入数字！");
			input.value = "";
			return;
		}
		if (pgNum<1 || pgNum>${requestScope.page.pageCount}) {
			alert("请输入从 1 - " + ${requestScope.page.pageCount} + " 的数字！");
			input.value = "";
			return;
		}
		window.location.href = "${requestScope.page.url}?method=list&category_id=${param.category_id}&pageNum=" + pgNum;
	}
</script>