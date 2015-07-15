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
		.bank{
			margin-left: -35px;
		}
	</style>
  </head>
  
  <body>
	  <div>
		  <h3>订单信息</h3>
		  <br>
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
    <br><br>
    <div>
    	<div class="bank">选择银行</div>
    	<br>
    	<form action="${pageContext.request.contextPath}/client/PayServlet" method="post">
    		<input type="hidden" name="p2_Order" value="${order.id}">
    		<input type="hidden" name="p3_Amt" value="${order.price}">
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="工商银行" src="${pageContext.request.contextPath}/images/bank/icbc.jpg" width="154px" height="33px">&nbsp;&nbsp;
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="农业银行" src="${pageContext.request.contextPath}/images/bank/abc.jpg" width="154px" height="33px">&nbsp;&nbsp;
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="中国银行" src="${pageContext.request.contextPath}/images/bank/boc.jpg" width="154px" height="33px">&nbsp;&nbsp;
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="建设银行" src="${pageContext.request.contextPath}/images/bank/ccb.jpg" width="154px" height="33px"> <br><br>
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="招商银行" src="${pageContext.request.contextPath}/images/bank/cmbc.jpg" width="154px" height="33px">&nbsp;&nbsp;
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="中国邮政" src="${pageContext.request.contextPath}/images/bank/post.jpg" width="154px" height="33px">&nbsp;&nbsp;
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="交通银行" src="${pageContext.request.contextPath}/images/bank/boco.jpg" width="154px" height="33px">&nbsp;&nbsp;
    		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><img alt="民生银行" src="${pageContext.request.contextPath}/images/bank/ms.jpg" width="154px" height="33px"> <br><br>
    		<input type="submit" value="支付" class="bank"><br>
    	</form>
	 </div>
  </body>
</html>
