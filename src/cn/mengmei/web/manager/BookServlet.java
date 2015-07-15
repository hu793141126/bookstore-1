package cn.mengmei.web.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.mengmei.domain.Book;
import cn.mengmei.domain.Page;
import cn.mengmei.domain.Category;
import cn.mengmei.service.impl.BusinessServiceImpl;
import cn.mengmei.utils.WebUtils;


public class BookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String method = request.getParameter("method");
			switch (method) {
				case "add":
					add(request,response);
					break;
				case "addUI":
					addUI(request,response);
					break;
				case "delete":
					delete(request,response);
					break;
				case "updateUI":
					updateUI(request,response);
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



	private void addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BusinessServiceImpl service = new BusinessServiceImpl();
		List<Category> categorys = service.getAllCategory();
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/manager/addbook.jsp").forward(request, response);
	}



	private void add(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			Book book = doUpload(request, response);
			if(book!=null){
				BusinessServiceImpl service = new BusinessServiceImpl();
				service.addBook(book);
				request.setAttribute("message", "添加成功!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败，请稍后再试!");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private Book doUpload(HttpServletRequest request, HttpServletResponse response){
		Book book = new Book();
		book.setId(WebUtils.makeUUID());
		
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(20); //设置缓冲区的大小为20KB，如果上传文件超过20KB，则不使用缓冲区来保存，使用临时文件来保存。默认为10KB。
			factory.setRepository(new File(this.getServletContext().getRealPath("/images/temp"))); //设置临时文件保存位置。
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); //解决上传文件名和文件数据的中文乱码问题
			upload.setFileSizeMax(1048576); //设置单个文件上传不能超过1048576个字节(byte)＝1MB。
			
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(book, name, value);
					System.out.println(name+" = "+value);
				}else{
					String fileName = item.getName();
					System.out.println("fileName:" + fileName);
					
					if(fileName==null || fileName.trim().equals("")){
						continue; //continue是结束单次循环，break是结束整个循环体。
					}
					
					//根据检查文件名后缀来控制上传文件的类型
					if((!fileName.endsWith(".jpg")) && (!fileName.endsWith(".png")) && (!fileName.endsWith(".gif"))){
						request.setAttribute("message", "只能上传 .jpg 、 .png 和 .gif 格式的图片");
						return null;
					}
					
					InputStream in = item.getInputStream();
					
					fileName = fileName.substring(fileName.lastIndexOf("/")+1);
					String realFileName = WebUtils.makeImageFileName(fileName);
					String name = item.getFieldName();
					BeanUtils.setProperty(book, name, realFileName);
					System.out.println(name + " = " + realFileName);
					
					String savePath = this.getServletContext().getRealPath("/images");
					String realSavePath = WebUtils.makeImageRealPath(realFileName, savePath);
					System.out.println("realSavePath:" + realSavePath);
					
					OutputStream out = new FileOutputStream(realSavePath+"/"+realFileName);
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete(); //确保在关闭流后,删除临时文件。
				}
			}
			return book;
		}catch(FileSizeLimitExceededException e){
			e.printStackTrace();
			request.setAttribute("message", "上传文件超过了1MB的最大允许大小。");
			return null;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String pageNum = request.getParameter("pageNum");  //当前页的页码。
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			String url = request.getContextPath()+"/"+this.getServletName();
			System.out.println("处理listPageData请求的url: " + url);
			Page page = service.getPageData(url, pageNum);
			
			request.setAttribute("page", page);
			request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "对不起获取图书列表失败，请稍后重试！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	
	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	
	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}



	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
