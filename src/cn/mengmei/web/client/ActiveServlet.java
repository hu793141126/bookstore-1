package cn.mengmei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.exception.NoRepeatActiveException;
import cn.mengmei.service.impl.BusinessServiceImpl;

public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String verifyCode = request.getParameter("verifyCode");
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.active(verifyCode);
			request.setAttribute("message", "激活成功！");
			
		}catch(RuntimeException e){
			e.printStackTrace();
			request.setAttribute("message", "不可重复激活！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
