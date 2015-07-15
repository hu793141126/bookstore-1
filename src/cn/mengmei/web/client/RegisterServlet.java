package cn.mengmei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.User;
import cn.mengmei.service.impl.BusinessServiceImpl;
import cn.mengmei.utils.SendMail;
import cn.mengmei.utils.WebUtils;


public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String cellphone = request.getParameter("cellphone");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			User user = new User();
			user.setId(WebUtils.makeUUID());
			user.setUsername(username);
			user.setPassword(password);
			user.setCellphone(cellphone);
			user.setEmail(email);
			user.setAddress(address);
			user.setVerifyCode(WebUtils.makeUUID()+WebUtils.makeUUID());
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.register(user);
			
			//开启一个新的线程，给用户发送激活邮件！
			SendMail send = new SendMail(user);
			send.start();
						
			request.setAttribute("message", "恭喜您，注册成功，我们已经向您的邮箱发送了一封激活邮件！");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "该用户已存在！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
