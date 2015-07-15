package cn.mengmei.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	
	Map<String,CartItem> map = new LinkedHashMap();
	private double price;
	private int count;
	
	public int getCount() {
		this.count = this.map.size();
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void add(Book book){
		CartItem item = map.get(book.getId());
		if(item==null){
			item = new CartItem();
			item.setBook(book);
			item.setQuantity(1);
			map.put(book.getId(), item);
		}else{
			item.setQuantity(item.getQuantity() + 1);
		}
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getPrice() {
		double totalprice = 0;
		for(Map.Entry<String,CartItem> me : map.entrySet()){
			double itemPrice = me.getValue().getPrice();
			totalprice += itemPrice;
		}
		this.price = new BigDecimal(totalprice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();;
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
