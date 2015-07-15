package cn.mengmei.web.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PayBackServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String r1_Code	= request.getParameter("r1_Code"); //固定值1表示支付成功
		String r6_Order	= request.getParameter("r6_Order");
		String r3_Amt	= request.getParameter("r3_Amt");
		
		PrintWriter out = response.getWriter();
		if(r1_Code.equals("1")){
			out.print("支付成功!\r\n订单号：" + r6_Order + "\r\n金额：" + r3_Amt);
		}else{
			out.print("支付失败！");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
