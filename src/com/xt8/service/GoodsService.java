package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Category;
import com.xt8.model.City;
import com.xt8.model.Goods;
import com.xt8.model.Province;
import com.xt8.model.User;

public interface GoodsService extends BasicService {
	public Goods findById(Serializable id);
	
	public List<Goods> findByName(String name,Integer pageIndex,Integer pageSize);
	
	public List<Goods> findByUser(User user,Integer pageIndex,Integer pageSize);
	
	public List<Goods> findByCategory(Category category,Integer pageIndex,Integer pageSize);
	
	public Goods insert(Goods goods);
	
	public void deleteGoods(Goods goods);
	
	public void modifyName(Goods goods,String name);
	
	public void modifyCategory(Goods goods,Category category);
	
	public void modifyAddr(Goods goods,Province province,City city);
	
	public void modifyPostageType(Goods goods,Integer postageType);
	
	public void modifyTag(Goods goods,String tag);
	
	public void modifyDecription(Goods goods,String description);

}
