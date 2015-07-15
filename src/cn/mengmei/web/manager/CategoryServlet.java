package cn.mengmei.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Category;
import cn.mengmei.service.impl.BusinessServiceImpl;
import cn.mengmei.utils.WebUtils;

//提供对Category增删改查的请求的处理。
public class CategoryServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
			String method = request.getParameter("method");
			switch (method) {
				case "add":
					add(request,response);
					break;
				case "delete":
					delete(request,response);
					break;
				case "update":
					update(request,response);
					break;
				case "list":
					list(request,response);
					break;
				default:
					request.setAttribute("message", "不支持此类操作!");
					request.getRequestDispatcher("/message.jsp").forward(request, response);
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "操作失败，请稍后再试!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			Category c = new Category();
			c.setId(WebUtils.makeUUID());
			c.setName(name);
			c.setDescription(description);
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.addCategory(c);
			
			request.setAttribute("message", "添加成功!");
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "添加失败!");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> list = service.getAllCategory();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
