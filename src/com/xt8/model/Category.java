package com.xt8.model;

import java.io.Serializable;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; // 表的主键
	private Integer categoryId; // 类别编号
	private String categoryName; // 类别名
	private Category parent; // 父类别ID；
	// private Integer parentId;
	private String description; // 类别描述

	public Category() {

	}

	public Category(Integer categoryId, String categoryName, Category parent,
			String description) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.parent = parent;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}
	
	public JSONObject toSimpleJSON() {
		
		String[] attrs = {"id","categoryName","description"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("parent", parent == null ? null : parent.toSimpleJSON());
		return json;
	}


		/*public JSONObject toSimpleJSON() {
		String[] attrs = { "id", "categoryId", "categoryName" };
		JSONObject json = Common.toJSON(this, attrs);
		json.put("parent", (this.getParent() == null) ? null : this.getParent()
				.toSimpleJSON());
		return json;
	}
*/
}
