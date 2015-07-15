package cn.mengmei.utils;

import java.io.File;
import java.util.UUID;

public class WebUtils {
		
	public static String makeUUID(){
		return UUID.randomUUID().toString();
	}
	
	public static String makeImageFileName(String fileName){
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		return UUID.randomUUID().toString() + "." + ext;
	}
	
	public static String makeImageRealPath(String fileName, String savePath){
		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;
		
		String dir = savePath + "/" + dir1 + "/" + dir2;
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs(); //创建多级目录，用此方法。
		}
		return dir;
	}
	
	public static String getImageSubPath(String fileName){
		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;
		return dir1 + "/" + dir2;
	}
	
}
