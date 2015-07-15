package cn.mengmei.service;

import java.util.List;

import cn.mengmei.domain.Book;
import cn.mengmei.domain.Category;
import cn.mengmei.domain.Page;

public interface BusinessService {

	/**添加分类**/
	void addCategory(Category c);

	/**查找分类**/
	Category findCategory(String id);

	/**得到所有分类**/
	List<Category> getAllCategory();

	/**添加图书**/
	void addBook(Book b);

	/**查找图书**/
	Book findBook(String id);

	/**得到一页图书**/
	Page getPageData(String url, String pageNum);

	/**这个方法重载**/
	/**得到一页图书**/
	Page getPageData(String category_id, String url, String pageNum);

}