package cn.mengmei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.User;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = service.login(username,password);
		
		if(user!=null){
			if(user.isState()){
				request.getSession().setAttribute("user", user);
				String oriServlet = request.getParameter("oriServlet");
				if(oriServlet!=null && !oriServlet.trim().equals("")){
					System.out.println(oriServlet);
					request.getRequestDispatcher(oriServlet).forward(request, response);
				}else{
					request.getRequestDispatcher("/client/IndexServlet").forward(request, response);
				}
			}else{
				request.setAttribute("message", "您的账号还未激活，请您前去邮箱激活！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("error", "用户名或密码错误!");
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}
		
		
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
