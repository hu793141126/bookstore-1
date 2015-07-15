<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addcategory.jsp</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath}/manager/CategoryServlet?method=add" method="post">
    	分类名称：<input type="text" name="name"><br>
    	分类描述：<textarea rows="5" cols="30" name="description"></textarea><br>
    	<input type="submit" value="添加分类">
    </form>
  </body>
</html>
