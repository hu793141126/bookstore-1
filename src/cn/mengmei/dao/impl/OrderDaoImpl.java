package cn.mengmei.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.mengmei.dao.OrderDao;
import cn.mengmei.domain.Book;
import cn.mengmei.domain.Order;
import cn.mengmei.domain.OrderItem;
import cn.mengmei.domain.User;
import cn.mengmei.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {

	/*
	public class Order {
		private String id;
		private String ordertime;
		private boolean state;
		private double price;
		
		private User user;
		private Set<OrderItem> orderItems = new HashSet();
	*/
	
	/*
	public class OrderItem {
		private String id;
		private int quantity;
		private double price;
		private Book book;
		
		private String order_id;
	*/
	
	public void add(Order order){
		try{
			//1.把order的基本信息保存到orders表
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into orders(id,ordertime,state,price,user_id) values(?,?,?,?,?)";
			Object[] params = {order.getId(),order.getOrdertime(),order.isState(),order.getPrice(),order.getUser().getId()};
			qr.update(sql, params);
			
			//2.把order中的订单保存到orderitem表
			Set<OrderItem> items = order.getOrderItems();
			for(OrderItem item : items){
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),item.getOrder_id()};
				qr.update(sql, params);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Order find(String order_id){
		try{
			//1.找出订单的基本信息
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where id=?";
			Order order = (Order) qr.query(sql, order_id, new BeanHandler(Order.class));
			
			//2.找出订单属于哪个客户
			sql = "select user.* from user,orders where orders.id=? and user.id=orders.user_id";
			User user = (User) qr.query(sql, order_id, new BeanHandler(User.class));
			
			//3.找出订单中所有的订单项
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = (List<OrderItem>) qr.query(sql, order_id, new BeanListHandler(OrderItem.class));
			for(OrderItem item : list){
				sql = "select book.* from book,orderitem where orderitem.id=? and book.id=orderitem.book_id";
				Book book = (Book) qr.query(sql, item.getId(), new BeanHandler(Book.class));
				item.setBook(book);
			}
			Set<OrderItem> orderItems = new HashSet();
			orderItems.addAll(list);
			
			order.setUser(user);
			order.setOrderItems(orderItems);
			return order;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Order> getAll(boolean state){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list = (List<Order>) qr.query(sql, state, new BeanListHandler(Order.class));
			for(Order order : list){
				sql = "select user.* from user,orders where orders.id=? and user.id=orders.user_id";
				User user = (User) qr.query(sql, order.getId(), new BeanHandler(User.class));
				order.setUser(user);
			}
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Order> getAll(User user){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where user_id=?";
			List<Order> list = (List<Order>) qr.query(sql, user.getId(), new BeanListHandler(Order.class));
			for(Order order : list){
				sql = "select orderitem.* from orderitem,orders where orders.id=? and orderitem.order_id=orders.id";
				List<OrderItem> items = (List<OrderItem>) qr.query(sql, order.getId(), new BeanListHandler(OrderItem.class));
				for(OrderItem item : items){
					sql = "select book.* from book,orderitem where orderitem.id=? and book.id=orderitem.book_id";
					Book book = (Book) qr.query(sql, item.getId(), new BeanHandler(Book.class));
					item.setBook(book);
				}
				Set<OrderItem> set = new HashSet();
				set.addAll(items);
				order.setOrderItems(set);
			}
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Order> getAll(User user, boolean state){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where user_id=? and state=?";
			Object[] params = {user.getId(),state};
			List<Order> list = (List<Order>) qr.query(sql, params, new BeanListHandler(Order.class));
			for(Order order : list){
				sql = "select orderitem.* from orderitem,orders where orders.id=? and orderitem.order_id=orders.id";
				List<OrderItem> items = (List<OrderItem>) qr.query(sql, order.getId(), new BeanListHandler(OrderItem.class));
				for(OrderItem item : items){
					sql = "select book.* from book,orderitem where orderitem.id=? and book.id=orderitem.book_id";
					Book book = (Book) qr.query(sql, item.getId(), new BeanHandler(Book.class));
					item.setBook(book);
				}
				Set<OrderItem> set = new HashSet();
				set.addAll(items);
				order.setOrderItems(set);
			}
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Order order){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update orders set state=? where id=?";
			Object[] params = {order.isState(), order.getId()};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
