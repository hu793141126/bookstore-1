<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mengmei/functions" prefix="mm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>前台body</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/body_jsp.css">
  </head>
  
  <body> 
    <div id="content">
    	<div id="category">
    	    <div id="ctitle"><a href="${pageContext.request.contextPath}/client/IndexServlet">全部分类</a></div>
    	    <ul>
    		    <c:forEach items="${categorys}" var="category">
    		        <li><i class="icon-bookmark-empty"></i>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/client/IndexServlet?category_id=${category.id}">${category.name}</a></li>
    		    </c:forEach>
    	    </ul>
    	</div>
    	<div id="bookandpagenum">
    	    <c:if test="${!empty(page)}">
		    	<div id="books">
		    		<c:forEach items="${page.list}" var="book">
		    		    <div id="book">
		    			    <div id="bookimage">
		    				    <img src="${pageContext.request.contextPath}/images/${mm:getSubPathAndName(book.image)}">
		    			    </div>
		    			    <div id="bookinfo">
		    				    <ul>
		    				        <li>${book.name}</li>
		    				        <li>${book.author}</li>
		    				        <li>${book.price}</li>
		    				        <li>${book.description}</li>
		    				        <li>
		    				        	<a href="${pageContext.request.contextPath}/client/BuyServlet?method=add2cart&bookid=${book.id}" target="link">加入购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    				        	<a href="${pageContext.request.contextPath}/client/BuyServlet?method=buy&bookid=${book.id}">购买</a>
		    				        </li>
		    				    </ul>
		    			    </div>
		    			</div>
		    		</c:forEach>
		    	</div>
	    		<div id="pagenum">
	    			<%@ include file="/public/paging.jsp"%>
	    		</div>
    	    </c:if>
    	</div>
    </div>
    <div style="clear:both;"></div>
  </body>
</html>
