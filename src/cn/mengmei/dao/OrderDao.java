package cn.mengmei.dao;

import java.util.List;

import cn.mengmei.domain.Order;
import cn.mengmei.domain.User;

public interface OrderDao {

	void add(Order order);

	Order find(String order_id);

	List<Order> getAll(boolean state);

	List<Order> getAll(User user);

	void update(Order order);

	List<Order> getAll(User user, boolean state);

}