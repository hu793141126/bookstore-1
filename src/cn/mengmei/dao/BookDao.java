package cn.mengmei.dao;

import java.util.List;

import cn.mengmei.domain.Book;

public interface BookDao {

	void add(Book b);

	Book find(String id);

	List<Book> getPageData(int startIndex, int pageSize);

	int getTotalRecords();

	List<Book> getPageData(String category_id, int startIndex, int pageSize);

	int getTotalRecords(String category_id);

}