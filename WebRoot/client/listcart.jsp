<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>listcart.jsp</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/Font-Awesome-3.2.1/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/body_jsp.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css&js/listbook_jsp.css"/>
	<style type="text/css">
		table{
			margin-left: 125px;
			margin-top: 0px;
		}
		td{
			height: 35px;
			border-bottom: 1px solid #F5F5F5;
		}
		h3{
			height: 58px;
			line-height: 58px;
			margin-left: 125px;
		}
		#buy{
			background-color: #FF4401;
			border: 0;
		}
		#buy:HOVER{
			background-color: #F22D00;
		}
		#buy a{
			color: white;
			font-size: 21px;
			font-family:sans-serif;
			border-bottom: 0px;
		}
	</style>
	
	
	<script type="text/javascript">
		function decrease(id,button) {
			var value = document.getElementById("input"+id).value;
			if(value>1){
				document.getElementById("input"+id).value--;
				value = document.getElementById("input"+id).value;
				window.location.href = "${pageContext.request.contextPath}/client/SetQuantityServlet?bookid="+id+"&quantity="+value;
				if(value<=99){
					document.getElementById("in"+id).disabled=null;
				}
			}else{
				 button.disabled="disabled";
			}
		}
		
		function increase(id,button) {
			var value = document.getElementById("input"+id).value;
			if(value<=99){
				document.getElementById("input"+id).value++;
				value = document.getElementById("input"+id).value;
				window.location.href = "${pageContext.request.contextPath}/client/SetQuantityServlet?bookid="+id+"&quantity="+value;
				if(value>1){
					document.getElementById("de"+id).disabled=null;
				}
			}else{
				 button.disabled="disabled";
			}
		}
		
		function setQuantity(bookid, input, oldValue) {
			if(input.value>=1 && input.value<=100){
				if(confirm("您确定改为"+input.value+"本吗？")){
					window.location.href = "${pageContext.request.contextPath}/client/SetQuantityServlet?bookid="+bookid+"&quantity="+input.value;
				}else{
					input.value = oldValue;
				}
			}else{
				alert("必须输入1-100之间的整数！");
				input.value = oldValue;
			}
		}
		
		function clearCart() {
    		if(confirm("您确定要清空购物车吗？")){
				window.location.href = "${pageContext.request.contextPath}/client/ClearCartServlet";
				window.parent.link.location="${pageContext.request.contextPath}/client/link.jsp";
			}
		}
		
		function deleteBook(bookid) {
			if(confirm("您确定要删除这本书吗？")){
				window.location.href = "${pageContext.request.contextPath}/client/DeleteServlet?bookid="+bookid;
				window.parent.link.location="${pageContext.request.contextPath}/client/link.jsp";
			}
		}
	</script>
</head>

<body style="text-align: center;">
  <h3 align="left"><i style="color:#FF6801;" class="icon-shopping-cart"></i> 购物车</h3>
  <c:if test="${!empty(cart.map)}">
  <table cellspacing="0">
	<tr height="28px">
		<th colspan="2">商品信息</th>
		<th>单价(元)</th>
		<th>数量</th>
		<th>金额(元)</th>
		<th>操作</th>
	</tr>
    <c:forEach var="me" items="${cart.map}">
    <tr>
		<td style="padding-left:16px;">${me.value.book.name}</td>
		<td>作者：${me.value.book.author}</td>
		<td align="center">${me.value.book.price}</td>
		<td align="center" width="150px">
			<input type="button" id="de${me.key}" value="-" onclick="decrease('${me.key}', this)">
			<input type="text" id="input${me.key}" size="4" value="${me.value.quantity}" onchange="setQuantity('${me.key}', this, '${me.value.quantity}')">
			<input type="button" id="in${me.key}" value="+" onclick="increase('${me.key}', this)">
		</td>
		<td width="188px" align="center">${me.value.price}</td>
		<td align="center" width="102px"><a href="javascript:deleteBook('${me.key}')">删除</a></td>
	</tr>
    </c:forEach>
    <tr height="45px" bgcolor="#E5E5E5">
    	<td colspan="4" align="left" style="padding-left: 16px;">
    		<input type="button" value="清空购物车" onclick="clearCart()">
    	</td>
    	<td align="center" style="font-family:sans-serif;">
    		合计：<span style="color:#FF4401; font-family:serif;">¥</span>&nbsp;<span style="color:#FF4401; font-size:x-large;">${cart.price}</span>
    	</td>
    	<td id="buy" align="center">    		
    		<a href="${pageContext.request.contextPath}/client/OrderServlet">结&nbsp;算</a>
    	</td>
    </tr>
  </table>
  </c:if>
  <c:if test="${empty(cart.map)}">
  	您还没有购买任何东西！
  </c:if>
  
  
  </body>

</html>


