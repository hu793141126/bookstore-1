package cn.mengmei.utils;

public class DaoFactory {
	
	private final static DaoFactory factory = new DaoFactory();
	
	private DaoFactory(){}

	public static DaoFactory getInstance() {
		return factory;
	}

	public <T> T createDao(String classname, Class<T> clazz){
		try {
			return (T) Class.forName(classname).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
