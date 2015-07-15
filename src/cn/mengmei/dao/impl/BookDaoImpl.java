package cn.mengmei.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.mengmei.dao.BookDao;
import cn.mengmei.domain.Book;
import cn.mengmei.utils.JdbcUtils;

public class BookDaoImpl implements BookDao {
	
	@Override
	public void add(Book b){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into book(id,name,author,price,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object[] params = {b.getId(),b.getName(),b.getAuthor(),b.getPrice(),b.getImage(),b.getDescription(),b.getCategory_id()};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public Book find(String id){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where id=?";
			return (Book) qr.query(sql, id, new BeanHandler(Book.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Book> getPageData(int startIndex, int pageSize){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book order by name limit ?,?";
			Object[] params = {startIndex,pageSize};
			return (List<Book>) qr.query(sql, params, new BeanListHandler(Book.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int getTotalRecords(){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from book";
			long count = (Long) qr.query(sql, new ScalarHandler("count(*)"));
			return (int)count;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	
	/**以下两个方法重载**/
	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Book> getPageData(String category_id, int startIndex, int pageSize){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where category_id=? order by name limit ?,?";
			Object[] params = {category_id,startIndex,pageSize};
			return (List<Book>) qr.query(sql, params, new BeanListHandler(Book.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int getTotalRecords(String category_id){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from book where category_id=?";
			long count = (Long) qr.query(sql, category_id, new ScalarHandler("count(*)"));
			return (int)count;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
