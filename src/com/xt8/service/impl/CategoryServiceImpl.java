package com.xt8.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Category;
import com.xt8.service.CategoryService;

@Transactional
public class CategoryServiceImpl extends BasicServiceImpl implements
		CategoryService {

	@Override
	public Category findById(Serializable id) {
		return (Category) super.findById(Category.class, id);
	}

	@Override
	public Category findByName(String name) {
		/*
		 * String hql = "from Category bean where bean.categoryName=?"; Object[]
		 * params = { name }; List<Category> list = super.executeQuery(hql,
		 * params); if (null == list || list.isEmpty()) { return null; } else {
		 * return list.get(0); }
		 */
		return null;
	}

	@Override
	public Category findByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		String hql = "from Category bean where bean.categoryId = ?";
		Object[] params = { categoryId };
		List<Category> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public Category getRootCategory() {
		String hql = "from Category bean where bean.parent=null order by bean.id asc";
		List<Category> list = super.executeQuery(hql, null);
		if (null == list || list.isEmpty()) {
			Category cate = new Category(1, "所有类别", null, "类别根目录");// 根目录从1开始，外键为空
			Serializable rid = super.save(cate);
			return findById(rid);
		} else {
			return (Category) list.get(0);
		}
	}

	@Override
	public Category insertCategory(Integer categoryId, String name,
			Category parent, String description) {
		// TODO Auto-generated method stub

		Category cate = new Category(categoryId, name, parent, description);
		Serializable rid = super.save(cate);
		return findById(rid);
	}

	@Override
	public List<Category> findByByParent(Category parent) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("parent", parent);
		return find(Category.class, conditions);
	}

}
