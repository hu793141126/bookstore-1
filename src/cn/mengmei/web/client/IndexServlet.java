package cn.mengmei.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mengmei.domain.Category;
import cn.mengmei.domain.Page;
import cn.mengmei.service.impl.BusinessServiceImpl;


public class IndexServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> categorys = service.getAllCategory();
		request.setAttribute("categorys", categorys);
		
		String category_id = request.getParameter("category_id");
		String pageNum = request.getParameter("pageNum");
		String url = request.getContextPath() + "/" + this.getServletName();
		Page page = null;
		
		if(category_id==null || category_id.trim().equals("")){
			page = service.getPageData(url, pageNum);
		}else{
			page = service.getPageData(category_id, url, pageNum);
		}
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
