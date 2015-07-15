package cn.mengmei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Book;
import cn.mengmei.domain.Cart;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class BuyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
			String bookid = request.getParameter("bookid");
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			Book book = service.findBook(bookid);
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(cart==null){
				cart = new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			service.buyBook(cart,book);
			
			String method = request.getParameter("method");
			switch(method){
				case "add2cart":
					/*String pageNum = request.getParameter("pageNum");
					String category_id = request.getParameter("category_id");
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("category_id", category_id);*/
					request.getRequestDispatcher("/client/link.jsp").forward(request, response);
					break;
				case "buy":
					request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
					break;
				default:
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "购买失败，请稍后再试!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
