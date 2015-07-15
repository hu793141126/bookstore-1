package cn.mengmei.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mengmei.dao.BookDao;
import cn.mengmei.dao.CategoryDao;
import cn.mengmei.dao.OrderDao;
import cn.mengmei.dao.UserDao;
import cn.mengmei.domain.Book;
import cn.mengmei.domain.Cart;
import cn.mengmei.domain.CartItem;
import cn.mengmei.domain.Order;
import cn.mengmei.domain.OrderItem;
import cn.mengmei.domain.Page;
import cn.mengmei.domain.Category;
import cn.mengmei.domain.User;
import cn.mengmei.exception.CartNotFoundException;
import cn.mengmei.exception.NumberIllegal;
import cn.mengmei.service.BusinessService;
import cn.mengmei.utils.DaoFactory;
import cn.mengmei.utils.WebUtils;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao cdao = DaoFactory.getInstance().createDao("cn.mengmei.dao.impl.CategoryDaoImpl", CategoryDao.class);
	private BookDao bdao = DaoFactory.getInstance().createDao("cn.mengmei.dao.impl.BookDaoImpl", BookDao.class);
	private UserDao udao = DaoFactory.getInstance().createDao("cn.mengmei.dao.impl.UserDaoImpl", UserDao.class);
	private OrderDao odao = DaoFactory.getInstance().createDao("cn.mengmei.dao.impl.OrderDaoImpl", OrderDao.class);
	
	/**添加分类**/
	@Override
	public void addCategory(Category c){
		cdao.add(c);
	}
	
	/**查找分类**/
	@Override
	public Category findCategory(String id){
		return cdao.find(id);
	}
	
	/**得到所有分类**/
	@Override
	public List<Category> getAllCategory(){
		return cdao.getAll();
	}
	
	/**添加图书**/
	@Override
	public void addBook(Book b){
		bdao.add(b);
	}
	
	/**查找图书**/
	@Override
	public Book findBook(String id){
		return bdao.find(id);
	}
	
	/**得到一页图书**/
	@Override
	public Page getPageData(String url, String pageNum){
		int totalRecords = bdao.getTotalRecords();
		Page page = null;
		if(pageNum==null){
			//就说明用户要看第一页的数据
			page = new Page(totalRecords,1);
		}else{
			page = new Page(totalRecords, Integer.parseInt(pageNum));
		}
		List list = bdao.getPageData(page.getBeginIndex(), page.getPageSize());
		page.setList(list);
		page.setUrl(url);
		return page;
	}

	/**这个方法重载**/
	/**得到一页图书**/
	@Override
	public Page getPageData(String category_id, String url, String pageNum){
		int totalRecords = bdao.getTotalRecords(category_id);
		Page page = null;
		if(pageNum==null){
			//就说明用户要看第一页的数据
			page = new Page(totalRecords,1);
		}else{
			page = new Page(totalRecords, Integer.parseInt(pageNum));
		}
		List list = bdao.getPageData(category_id, page.getBeginIndex(), page.getPageSize());
		page.setList(list);
		page.setUrl(url);
		return page;
	}

	//添加图书到购物车
	public void buyBook(Cart cart, Book book) {
		cart.add(book);
	}
	
	//修改购物项数量
	public void setQuantity(String bookid, String quantity, Cart cart) throws NumberIllegal, CartNotFoundException {
		int num = 0;
		try{
			num = Integer.parseInt(quantity);
		}catch(Exception e){
			throw new NumberIllegal("对不起，您输入的数量不合法！");
		}
		
		if(cart==null){
			throw new CartNotFoundException("您还没有购买任何东西!");
		}
		
		if(num<1 || num>100){
			throw new NumberIllegal("对不起，您必须输入1-100之间的整数！");
		}
		cart.getMap().get(bookid).setQuantity(num);
	}
	
	//清空购物车
	public void clearCart(Cart cart) throws CartNotFoundException{
		if(cart==null){
			throw new CartNotFoundException("您还没有购买任何东西!");
		}
		cart.getMap().clear();
	}
	
	//删除购物项
	public void deleteBook(String bookid, Cart cart)
			throws CartNotFoundException{
		if(cart==null){
			throw new CartNotFoundException("您还没有购买任何东西!");
		}
		cart.getMap().remove(bookid);
	}

	
	//注册
	public void register(User user){
		udao.add(user);
	}
	
	
	//激活
	public void active(String verifyCode){
		udao.update(verifyCode);
	}
	
	//登陆
	public User login(String username, String password) {
		return udao.find(username, password);
	}
	
	

	
	//生成订单
	public Order createOrder(User user, Cart cart){
		if(cart==null){
			throw new RuntimeException("您还没有购买任何东西!");
		}
		Order order = new Order();
		String order_id = WebUtils.makeUUID();
		order.setId(order_id);
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		
		Set<OrderItem> orderItems = new HashSet();
		
		for(Map.Entry<String,CartItem> me : cart.getMap().entrySet()){
			
			CartItem cItem = me.getValue();
			
			OrderItem oItem = new OrderItem();
			
			oItem.setId(WebUtils.makeUUID());
			oItem.setBook(cItem.getBook());
			oItem.setQuantity(cItem.getQuantity());
			oItem.setPrice(cItem.getPrice());
			oItem.setOrder_id(order_id);
			
			orderItems.add(oItem);
		}
		order.setOrderItems(orderItems);
		odao.add(order);
		return order;
	}

	//列出用户的所有订单
	public List<Order> listUserOrders(User user) {
		List<Order> list = odao.getAll(user);
		return list;
	}
	
	//获得指定用户的指定订单状态的订单集合
	public List<Order> listUserStateOrders(User user, boolean state) {
		return odao.getAll(user, state);
	}

	//管理员页面的按状态查看订单
	public List<Order> listOrder(String state) {
		List<Order> list = odao.getAll(Boolean.parseBoolean(state));
		return list;
	}

	//管理员将订单发货
	public void deliver(String order_id) {
		Order order = odao.find(order_id);
		order.setState(true);
		odao.update(order);
	}

	public Order findOrder(String order_id) {
		Order order = odao.find(order_id);
		return order;
	}
	
}
