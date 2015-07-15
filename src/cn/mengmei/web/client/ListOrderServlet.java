package cn.mengmei.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Order;
import cn.mengmei.domain.User;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class ListOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user==null){
			String oriServlet = "/"+this.getServletName();
			System.out.println(oriServlet);
			request.setAttribute("oriServlet", oriServlet);
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}else{
			BusinessServiceImpl service = new BusinessServiceImpl();
			String state = request.getParameter("state");
			if(state==null || state.trim().equals("")){
				List<Order> list = service.listUserOrders(user);
				request.setAttribute("list", list);
			}else{
				List<Order> list = service.listUserStateOrders(user,Boolean.parseBoolean(state));
				request.setAttribute("list", list);
			}
			request.getRequestDispatcher("/client/listorder.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
