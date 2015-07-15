<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>前台首页</title>
    <style type="text/css">
	   @import url("${pageContext.request.contextPath}/css&js/body_jsp.css");
    </style>
  </head>
  
 <frameset rows="5%,17%,*" border="1px">
 	<frame  src="${pageContext.request.contextPath}/client/link.jsp" name="link">
 	<frame src="${pageContext.request.contextPath}/client/top.jsp" name="top"> 	
 	<frame src="${pageContext.request.contextPath}/client/IndexServlet" name="body">
 </frameset>
</html>
