<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台首页左侧边栏</title>
  	<style type="text/css">
  		body{
    		background-color: #F5F5F5;
    	}
		table{
			text-align: center;
			margin-left: 7px;
		}
		
		table td{
			border: thin groove;
			width:128px;
		}
		
		a{
			text-decoration:none;
			color:#654;
		}
		a:HOVER{
			color:#000;
		}
		
		td div{
			display:none;
			background-color:#FFF;
			border-top: thin dashed #CCC;
		}
		
		.close{
			display:none;
		}
		
		.open{
			display:block;
		}
		
	</style>

	
	<script type="text/javascript">
		//各管各
		function list1(){
			var aNode = event.srcElement;
			var tdNode = aNode.parentNode;
			var divNode = tdNode.getElementsByTagName("div")[0];
			if(divNode.className == "open"){
				divNode.className = "close";
			}else{
				divNode.className = "open";	
			}
		}
		
		//一个开其他关
		function list(){
			var aNode = event.srcElement;
			var tdNode = aNode.parentNode;
			var divNode = tdNode.getElementsByTagName("div")[0];
			
			var tableNode = document.getElementsByTagName("table")[0];
			var divNodes = tableNode.getElementsByTagName("div");
			for(var x=0; x<divNodes.length; x++){
				if(divNodes[x] == divNode){
					if(divNode.className == "open"){
						divNode.className = "close";
					}else{
						divNode.className = "open";	
					}
				}else{
					divNodes[x].className = "close";
				}
			}
			
		}
	</script>
  </head>

  <body>
    <table cellspacing="15px;">
	  <tr>
    	<td>
            <a href="javascript:void(0)" onclick="list1()" target="body">分类管理</a>
            <div>
            	<a href="${pageContext.request.contextPath}/manager/addcategory.jsp" target="body">添加分类</a><br>
    			<a href="${pageContext.request.contextPath}/manager/CategoryServlet?method=list" target="body">查看分类</a>
            </div>
        </td>
      </tr>
      <tr>
    	<td>
            <a href="javascript:void(0)" onclick="list1()"  target="body">图书管理</a>
            <div>
            	<a href="${pageContext.request.contextPath}/manager/BookServlet?method=addUI" target="body">添加图书</a><br>
    			<a href="${pageContext.request.contextPath}/manager/BookServlet?method=list" target="body">查看图书</a>
            </div>
        </td>
    </tr>
    <tr>
    	<td>
            <a href="javascript:void(0)" onclick="list1()" target="body">订单管理</a>
            <div>
            	<a href="${pageContext.request.contextPath}/manager/ListOrderServlet?state=false" target="body">未发货订单</a><br>
    			<a href="${pageContext.request.contextPath}/manager/ListOrderServlet?state=true" target="body">已发货订单</a>
            </div>
        </td>
    </tr>
    </table>
  </body>
</html> 
