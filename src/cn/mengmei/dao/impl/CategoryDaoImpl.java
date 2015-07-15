package cn.mengmei.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.mengmei.dao.CategoryDao;
import cn.mengmei.domain.Category;
import cn.mengmei.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	public void add(Category c){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object[] params = {c.getId(), c.getName(), c.getDescription()};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public Category find(String id){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category where id=?";
			return (Category) qr.query(sql, id, new BeanHandler(Category.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getAll(){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category";
			return (List<Category>) qr.query(sql, new BeanListHandler(Category.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
