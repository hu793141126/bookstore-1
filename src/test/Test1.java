package test;

public class Test1 {

	public static void main(String[] args) {
		System.out.println(getImageSubPath("4f6a5b6d-af6a-49d8-a003-a2630075cfaa.jpg"));
	}
	
	public static String getImageSubPath(String fileName){
		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;
		return dir1 + "/" + dir2;
	}

}
