<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>listcategory.jsp</title>
    <style type="text/css">
    	body{
    		color: #333;
    	}
    	a{
    		color: #876;
    		text-decoration: none;
    		border-bottom: thin solid #876;
    	}
    	a:HOVER{
    		color: #000;
    		border-bottom: thin solid;
    		border-bottom: thin solid;
    	}
    	table{
    		width: 70%;
    		margin-left: 125px;
    		margin-top: 20px;
    		border-top: thin solid;
    		border-bottom: thin solid;
    	}
    	th{
    		border-bottom: thin groove;
    	}
    </style>
  </head>
  
  <body>
	<table cellspacing="6px" cellpadding="2px">
		<tr>
			<th>分类名称</th>
			<th>分类描述</th>
			<th>操作</th>
		</tr>
	  	<c:forEach items="${list}" var="category">
	  		<tr>
	  			<td>${category.name}</td>
	  			<td>${category.description}</td>
	  			<td style="text-align: center;" width="100px">
	  				<a href="${pageContext.request.contextPath}/manager/CategoryServlet?method=update&id=${category.id}">修改</a>&nbsp;&nbsp;
	  				<a href="${pageContext.request.contextPath}/manager/CategoryServlet?method=delete&id=${category.id}">删除</a>
	  			</td>
	  		</tr>
	  	</c:forEach>
	</table>
  </body>
</html>
