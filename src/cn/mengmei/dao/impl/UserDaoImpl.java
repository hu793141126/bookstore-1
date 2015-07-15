package cn.mengmei.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.mengmei.dao.UserDao;
import cn.mengmei.domain.User;
import cn.mengmei.exception.NoRepeatActiveException;
import cn.mengmei.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {

	/*
	private String id;
	private String username;
	private String password;
	private String address;
	private String cellphone;
	private String email;
	private String verifyCode;
	private boolean state;
	*/
	
	public void add(User user){
		try{
			//将用户基本信息注册到user表里，state默认为false未激活。
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id,username,password,address,cellphone,email) values(?,?,?,?,?,?)";
			Object[] params = {user.getId(),user.getUsername(),user.getPassword(),user.getAddress(),user.getCellphone(),user.getEmail()};
			qr.update(sql, params);
			
			//将激活码verifyCode添加到active表里。
			sql = "insert into active(user_id,verifyCode) values(?,?)";
			params = new Object[]{user.getId(),user.getVerifyCode()};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public User find(String id){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";
			return (User) qr.query(sql, id, new BeanHandler(User.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}
	
	@SuppressWarnings("deprecation")
	public User find(String username, String password){
		try{
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object[] params = {username,password};
			return (User) qr.query(sql, params, new BeanHandler(User.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//激活
	@Override
	public void update(String verifyCode){
		try{
			//查询出user_id从active表里
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select user_id from active where verifyCode=?";
			Object[] arr = (Object[])qr.query(sql, verifyCode, new ArrayHandler());
			if(arr==null){
				throw new NoRepeatActiveException("不能重复激活");
			}
			String user_id = (String)arr[0];
			
			//删除active表里的激活码
			sql = "delete from active where verifyCode=?";
			int effect = qr.update(sql, verifyCode);
			if(effect==0){
				throw new NoRepeatActiveException("不能重复激活");
			}else{
				//将user的state改为true
				sql = "update user set state=? where id=?";
				Object[] params = {true, user_id};
				qr.update(sql, params);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
