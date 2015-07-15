<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'register.jsp' starting page</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath}/client/RegisterServlet" method="post" target="window.parent">
    	用户名：<input type="text" name="username"><br>
    	密码：<input type="password" name="password"><br>
    	手机：<input type="text" name="cellphone"><br>
    	邮箱：<input type="text" name="email"><br>
    	收货地址：<input type="text" size="50" name="address"><br>
    	<input type="submit" value="注册">
    </form>
  </body>
</html>
