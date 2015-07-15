package cn.mengmei.dao;

import java.util.List;

import cn.mengmei.domain.Category;

public interface CategoryDao {

	void add(Category c);

	Category find(String id);

	List<Category> getAll();

}