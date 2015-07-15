package cn.mengmei.web.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Order;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class OrderDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String order_id = request.getParameter("order_id");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		Order order = service.findOrder(order_id);
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
