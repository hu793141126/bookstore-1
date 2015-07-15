<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/mengmei/functions" prefix="mm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'listorder.jsp' starting page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/body_jsp.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/listbook_jsp.css"/>
  	<style type="text/css">
		div{
			margin-left: 100px;
		}
		h3{
			margin-top: 15px;
		}
		td{
			border-bottom: 1px solid #DDD;
		}
	</style>
  </head>
  
  <body>
  	<div><h3>我的订单</h3></div>
  	
  	<table style="border: 0px; margin-top: -20px;">
  		<tr>
  			<td align="right" style="border: 0px;">
	  			<a href="${pageContext.request.contextPath}/client/ListOrderServlet?state=false">未发货</a>
	  			&nbsp;&nbsp;|&nbsp;&nbsp;
	  			<a href="${pageContext.request.contextPath}/client/ListOrderServlet?state=true">已发货</a>
  			</td>
  		</tr>
  	</table>
  	
    <c:forEach items="${list}" var="order">
	    <table id="list" cellspacing="0">
		    <tr bgcolor="#DDA" height="35px">
		    	<td colspan="2">订单编号：${order.id}</td>
		    	<td>&emsp;下单时间：${order.ordertime}</td>  	
		     	<td>订单状态：
		     		<span style="color:#FF4401;"><c:if test="${fn:contains(order.state,'false')}">
			      		未发货
			  		</c:if>
			  		<c:if test="${fn:contains(order.state,'true')}">
			      		已发货
			  		</c:if></span>
			  	</td>
		    </tr>
		    <tr height="30px" align="center" bgcolor="#EEE">
		    	<td colspan="2">商品信息</td>
		    	<td>数量 (件)</td>
		    	<td>金额 (元)</td>
		    </tr>
	     	<c:forEach items="${order.orderItems}" var="orderItem">
		     	<tr>
		     		 <td><img src="${pageContext.request.contextPath}/images/${mm:getSubPathAndName(orderItem.book.image)}"></td>
		     		 <td>书名：${orderItem.book.name}&emsp;
		     		 作者：${orderItem.book.author}</td>
		     		 <td align="center">${orderItem.quantity}</td>    		 
		     		 <td align="center">${orderItem.price}</td>
		     		 
		     	</tr> 		 
	     	</c:forEach>
	     	<tr bgcolor="#DDA" height="35px">
	     		<td colspan="3" align="right">
	     			<c:if test="${fn:contains(order.state,'false')}">
			      		<p><a href="#"  style="color:#000;border-bottom:0px;">取消订单</a></p>
			  		</c:if>
	     		</td>
	     		<td align="center">
	     		 	订单合计：${order.price}（元）
	     		</td>
	     	</tr>
	    </table>
    	<br><br>
    </c:forEach>
  </body>
</html>
