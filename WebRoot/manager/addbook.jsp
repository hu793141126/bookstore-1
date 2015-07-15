<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addBook.jsp</title>
  </head>
  
  <%--
    private String name;
	private String author;
	private double price;
	private String image; //储存图片的名称，位置是WebRoot下images文件夹固定不会变。
	private String description;
	private String category_id;
  --%>
  
  <body>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/manager/BookServlet?method=add" method="post">
    	图书名称：<input type="text" name="name"><br>
    	图书作者：<input type="text" name="author"><br>
    	图书售价：<input type="text" size="16" name="price"> 元<br>
    	封面图：&emsp;<input type="file" name="image"><br>
    	图书描述：<textarea rows="5" cols="30" name="description"></textarea><br>
    	图书分类：<select name="category_id">
    			<c:forEach items="${categorys}" var="category">
    				<option value="${category.id}">${category.name}</option>
    			</c:forEach>
    			</select><br>
    	<input type="submit" value="添加图书">
    </form>
  </body>
</html>
