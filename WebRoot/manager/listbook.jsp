<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/mengmei/functions" prefix="mm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>listbook.jsp</title>
    <style type="text/css">
    	@IMPORT url("${pageContext.request.contextPath}/css&js/listbook_jsp.css");
    </style>
  </head>
  
  <body>
    <c:choose>
	  <c:when test="${requestScope.page.totalRecords!=0}">
		<div style="height: 422px">
		  <table cellspacing="6px">
			<tr>
			  <th>封面</th>
			  <th>书名</th>
			  <th>作者</th>
			  <th>售价</th>
			  <th>描述</th>
			  <th>所属分类</th>
			  <th>操作</th>
			</tr>
			<c:forEach var="book" items="${requestScope.page.list}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/${mm:getSubPathAndName(book.image)}"></td>
					<td>${book.name}</td>
					<td>${book.author}</td>
					<td>${book.price}</td>
					<td>${mm:subString(fn:escapeXml(book.description),15)}</td>
					<td>${mm:categoryId2Name(book.category_id)}</td>
					<td align="center">
						<a href="${pageContext.request.contextPath}/BookServlet?method=updateUI&id=${book.id}">修改</a>
						<a href="javascript:doDelete('${book.id}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		  </table>
		</div>
			
		<div id="pageNum">
		  <%@ include file="/public/paging.jsp"%>
		</div>
	  </c:when>
	  
	  <c:otherwise>
  	  	图书列表为空！
  	  </c:otherwise>
	</c:choose>
  </body>
  <script type="text/javascript">
  	function doDelete(id){
  		var b = window.confirm("您确认要删除吗？");
  		if(b){
  			window.location.href = "${pageContext.request.contextPath}/BookServlet?method=delete&id="+id;
  		}
  	}
  </script>
  </body>
</html>
