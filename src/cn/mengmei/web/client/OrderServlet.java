package cn.mengmei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import cn.mengmei.domain.Cart;
import cn.mengmei.domain.Order;
import cn.mengmei.domain.User;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class OrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
			User user = (User) request.getSession().getAttribute("user");
			
			if(user==null){
				String oriServlet = "/"+this.getServletName();
				System.out.println(oriServlet);
				request.setAttribute("oriServlet", oriServlet);
				request.getRequestDispatcher("/client/login.jsp").forward(request, response);
				return;
			}
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessServiceImpl service = new BusinessServiceImpl();
			Order order = service.createOrder(user,cart);
			
			request.getSession().setAttribute("cart", null);
			
			request.setAttribute("order", order);
			request.getRequestDispatcher("/client/showorder.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "对不起，生产订单失败，请稍后重试!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
