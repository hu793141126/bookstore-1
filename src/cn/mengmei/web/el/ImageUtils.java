package cn.mengmei.web.el;

import cn.mengmei.utils.WebUtils;

public class ImageUtils {
	
	public static String getSubPathAndName(String imageName){
		return WebUtils.getImageSubPath(imageName) + "/" + imageName;
	}
	
}
