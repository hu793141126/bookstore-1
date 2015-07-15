package cn.mengmei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Cart;
import cn.mengmei.exception.CartNotFoundException;
import cn.mengmei.service.BusinessService;
import cn.mengmei.service.impl.BusinessServiceImpl;

public class ClearCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		
		try {
			service.clearCart(cart);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		} catch (CartNotFoundException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
