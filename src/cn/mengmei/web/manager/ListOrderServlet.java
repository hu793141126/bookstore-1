package cn.mengmei.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Order;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class ListOrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String state = request.getParameter("state");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Order> list = service.listOrder(state);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
