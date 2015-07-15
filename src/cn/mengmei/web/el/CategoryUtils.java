package cn.mengmei.web.el;

import cn.mengmei.domain.Category;
import cn.mengmei.service.impl.BusinessServiceImpl;

public class CategoryUtils {

	public static String categoryId2Name(String id){
		BusinessServiceImpl service = new BusinessServiceImpl();
		Category c = service.findCategory(id);
		return c.getName();
	}
	
}
