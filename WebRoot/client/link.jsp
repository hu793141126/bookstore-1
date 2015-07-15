<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>前台头</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/body_jsp.css">
    <style type="text/css">
    	#link{
    		background-color: #EAE9EA;
    		padding-left: 20px;
    		padding-right: 20px;
    		height: 100%;
    	}
    	#link div{
    		text-align: center;
    		padding-left:13px;
    		padding-right:13px;
    		font-size: 15px;
    		margin-top:4px;
    	}
    	#link a{
    		color: black;
    		text-decoration: none;
    		border: 0;
    	}
    	#link a:HOVER{
    		color: #C40000;
    	}
    	#home,#login,#register,#logout,#userInfo{
    		float: left;
    	}
    	#order,#cart{
    		float: right;
    	}
    </style>
  </head>
  
  <body>
    <div id="link">
		
		<div id="order">
		    <a href="${pageContext.request.contextPath}/client/ListOrderServlet" target="body">我的订单</a>
		</div>
		
		<div id="cart">
		    <a href="${pageContext.request.contextPath}/client/listcart.jsp" target="body">
		    	<i style="color:#C40000;" class="icon-shopping-cart"></i> 购物车
		    	<c:if test="${!empty(cart)}">
		    		<span style="color:#C40000;">${cart.count}</span> 件
		    	</c:if>
		    </a>
	    </div>
	    
	    
	    
	    <div id="home">
	    	<a href="${pageContext.request.contextPath}/client/IndexServlet" target="body"><i style="color:#C40000;" class="icon-home"></i> 书店首页</a>
	    </div>
	    
		
		<c:choose>
		    <c:when test="${empty(user)}">
				<div id="login">
			    	<a href="${pageContext.request.contextPath}/client/login.jsp" target="body">请登陆</a>
			    </div>
			    <div id="register">
			    	<a href="${pageContext.request.contextPath}/client/register.jsp" target="body">注册</a>
			    </div>
		    </c:when>
	    	<c:otherwise>
	    		<div id="userInfo">您好, ${user.username}</div>
	    		<div id="logout">
	    			<a href="${pageContext.request.contextPath}/client/LogOutServlet" target="window.parent">退出</a>
	    		</div>
	    	</c:otherwise>
	    </c:choose>
	    
	    
	    <div style="clear:both;"></div>
    </div>
  </body>
</html>
