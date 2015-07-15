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
		table tr td{
			text-align: center;
			height: 35px;
			border-bottom: 1px solid #EEE;
		}
	</style>
  </head>
  <%--
  	private User user;
  	private Date ordertime;
	private boolean state;
	private double price;
	private Set<OrderItem> orderItems = new HashSet();
  --%>
  <body>
  	<table>
  		<tr>
  			<th>订单人</th>
  			<th>订单编号</th>
  			<th>下单时间</th>
  			<th>订单状态</th>
  			<th>订单总金额</th>
  			<th>操作</th>
  		</tr>
	    <c:forEach items="${list}" var="order">
	    <tr>
	    	<td>${order.user.username}</td>
	    	<td>${order.id}</td>
	    	<td>${order.ordertime}</td>
	    	<td><c:if test="${fn:contains(order.state,'false')}">
		      		未发货
		  		</c:if>
		  		<c:if test="${fn:contains(order.state,'true')}">
		      		已发货
		  		</c:if>
		  	</td>
	    	<td>${order.price}</td>
	    	<td>
		      	<a href="${pageContext.request.contextPath}/manager/OrderDetailServlet?order_id=${order.id}">查看明细</a>
	    		<a href="">删除</a>
	    	</td>
	    </tr>
	    </c:forEach>
    </table>
  </body>
</html>
