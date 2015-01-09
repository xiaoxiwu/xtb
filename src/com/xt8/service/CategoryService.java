package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Category;

public interface CategoryService extends BasicService {
	public Category findById(Serializable id);

	public Category findByName(String name);

	public Category findByCategoryId(Integer categoryId);

	public Category getRootCategory();

	public Category insertCategory(Integer categoryId, String name,
			Category parent, String description);

	public List<Category> findByByParent(Category parent);

}
