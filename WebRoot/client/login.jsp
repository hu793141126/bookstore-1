<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/mengmei/functions" prefix="mm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>
    <script type="text/javascript">
    	function submit_refresh(){
    		document.forms[0].submit();
    		window.parent.link.location="${pageContext.request.contextPath}/client/link.jsp";
    	}
    </script>
  </head>
  
  <body>
  ${error}
    <form action="${pageContext.request.contextPath}/client/LoginServlet" method="post">
    用户登陆<br>
    	<input type="hidden" name="oriServlet" value="${requestScope.oriServlet}">
    	用户名：<input type="text" name="username"><br>
    	密码：<input type="text" name="password"><br>
    	<input type="button" value="登陆" onclick="submit_refresh()">
    </form>
  </body>
</html>
