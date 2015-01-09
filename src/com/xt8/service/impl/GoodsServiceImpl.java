package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Category;
import com.xt8.model.City;
import com.xt8.model.Goods;
import com.xt8.model.Province;
import com.xt8.model.User;
import com.xt8.service.GoodsService;

import static com.xt8.util.Constants.*;
@Transactional
public class GoodsServiceImpl extends BasicServiceImpl implements GoodsService {

	@Override
	public Goods findById(Serializable id) {
		return (Goods) super.findById(Goods.class, id);
	}

	@Override
	public List<Goods> findByName(String name,Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Goods bean where bean.name like ?";
		Object[] params = { "%"+name+"%" };
		
		return super.executeQueryByPage(hql, params, pageIndex, pageSize);

	}

	@Override
	public Goods insert(Goods goods) {
		return (Goods) super.saveAndReturn(Goods.class, goods);
	}

	@Override
	public void deleteGoods(Goods goods) {
		// TODO Auto-generated method stub
		// 删除物品,GoodsImage里的图片和image里的都应该删除
	//	goods.setIfDel(1);
		//super.update(goods);
		super.delete(goods);

	}

	@Override
	public void modifyName(Goods goods, String name) {
		// TODO Auto-generated method stub
		goods.setName(name);
		super.update(goods);
		
	}

	@Override
	public void modifyCategory(Goods goods, Category category) {
		// TODO Auto-generated method stub
		goods.setCategory(category);
		super.update(goods);
		
	}

	@Override
	public void modifyAddr(Goods goods, Province province, City city) {
		// TODO Auto-generated method stub
		goods.setProvince(province);
		goods.setCity(city);
		super.update(goods);
		
	}

	@Override
	public void modifyPostageType(Goods goods, Integer postageType) {
		// TODO Auto-generated method stub
		
		goods.setPostageType(postageType);
		super.update(goods);
		
	}

	@Override
	public void modifyTag(Goods goods, String tag) {
		// TODO Auto-generated method stub
		goods.setTag(tag);
		super.update(goods);
		
	}

	@Override
	public void modifyDecription(Goods goods, String description) {
		// TODO Auto-generated method stub
		goods.setDescription(description);
		super.update(goods);
		
	}

	@Override
	public List<Goods> findByUser(User user,Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Goods bean where bean.owner = ?";
		Object[] params = { user };
		
		return super.executeQueryByPage(hql, params, pageIndex, pageSize);
	}

	@Override
	public List<Goods> findByCategory(Category category, Integer pageIndex,
			Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Goods bean where bean.category = ?";
		Object[] params = { category };
		
		return super.executeQueryByPage(hql, params, pageIndex, pageSize);
	}

}
