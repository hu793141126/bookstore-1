package cn.mengmei.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	
	private String id;
	private Date ordertime;
	private boolean state;
	private double price;
	
	private User user;
	private Set<OrderItem> orderItems = new HashSet();
	
	private int size;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public int getSize() {
		this.size = this.orderItems.size();
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
