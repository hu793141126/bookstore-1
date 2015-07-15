<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/mengmei/functions" prefix="mm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'showorder.jsp' starting page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/body_jsp.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/listbook_jsp.css"/>
	<style type="text/css">
		div{
			margin-left: 135px;
		}
		h3{
			margin-top: 15px;
			margin-left: -35px;
		}
		#title{
			margin-left: 100px;
		}
		#deliver{
			margin-left:100px;
			width:80px;
			height:35px;
			background-color: #FF4401;
			text-align: center;
			line-height: 35px;
		}
		#deliver:HOVER{
			background-color: #F22D00;
		}
		#deliver a{
			color: white;
			font-size: 18px;
			font-family:sans-serif;
			border-bottom:0px;
			text-decoration:none; 
		}
	</style>
  </head>
  
  <body>
	  <div>
		  <h3>订单明细</h3>
		  <br>
		  订单编号：${order.id}<br>
		  订单时间：${order.ordertime}<br>
		  订单状态：
		  <c:if test="${fn:contains(order.state,'false')}">
		      未发货
		  </c:if>
		  <c:if test="${fn:contains(order.state,'true')}">
		      已发货
		  </c:if>
	  </div>
	  <br>
	  <div>
    		收货人信息：${order.user.username}&nbsp;&nbsp; ${order.user.cellphone}&nbsp;&nbsp; ${order.user.address}<br>
      </div>
      <br>
    <div id="title">商品列表:</div>
    <table>
	    <tr height="30px" align="center" bgcolor="#EEE">
	    	<td colspan="2">商品信息</td>
	    	<td>数量（件）</td>
	    	<td>金额（元）</td>
	    </tr>
	    <c:forEach items="${order.orderItems}" var="orderItem">
		    <tr>
		    	<td><img src="${pageContext.request.contextPath}/images/${mm:getSubPathAndName(orderItem.book.image)}"></td>
		    	<td>书名：${orderItem.book.name}&emsp;&emsp;
		    		作者：${orderItem.book.author}
		    	</td>
		    	<td align="center">${orderItem.quantity}</td>
		    	<td align="center">${orderItem.price}</td>
		    </tr>	
	    </c:forEach>
	    <tr bgcolor="#CCC" height="35px"><td colspan="5" align="right"><span style="font-size: large;">合计：<span style="color:#FF4401;">${order.price}</span>（元）</td></tr>
    </table>
    <br>
    <c:if test="${fn:contains(order.state,'false')}">
    	<div id="deliver"><a href="${pageContext.request.contextPath}/manager/DeliverServlet?order_id=${order.id}">发货</a></div>
    </c:if>
    <br><br>
  </body>
</html>
