package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;










import com.xt8.model.Goods;
import com.xt8.model.GoodsUserLike;
import com.xt8.model.GoodsUserWant;
import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserEvaluateIndex;
import com.xt8.service.GoodsUserLikeService;
import com.xt8.service.SettingService;

@Transactional
public class GoodsUserLikeServiceImpl extends BasicServiceImpl implements GoodsUserLikeService {

	
	@Override
	public GoodsUserLike insertGoodsUserLike(GoodsUserLike goodsUserLike) {
		// TODO Auto-generated method stub
		Serializable id = super.save(goodsUserLike);
		
		return findById(id);
	}
	
	@Override
	public GoodsUserLike findById(Serializable id) {
		
		return (GoodsUserLike) super.findById(GoodsUserLike.class, id);
		
	}

	@Override
	public List<GoodsUserLike> findByGoods(Goods goods) {
		// TODO Auto-generated method stub
		String hql = "from GoodsUserLike bean where bean.goods = ?";
		Object[] params = {goods};
		List<GoodsUserLike> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}

	@Override
	public List<GoodsUserLike> findByUser(User user) {
		// TODO Auto-generated method stub
		String hql = "from GoodsUserLike bean where bean.user = ?";
		Object[] params = {user};
		List<GoodsUserLike> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}

	@Override
	public GoodsUserLike findByUserAndGoods(User user, Goods goods) {
		// TODO Auto-generated method stub
		String hql = "from GoodsUserLike bean where bean.user=? and bean.goods=?";
		Object[] params = {user,goods};
		
		List<GoodsUserLike> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}

	@Override
	public void deleteGoodsUserLike(GoodsUserLike goodsUserLike) {
		// TODO Auto-generated method stub
		super.delete(goodsUserLike);
	}

	@Override
	public List<User> findLikeUserByGoods(Goods goods) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public void SetLikeUserForGoods(User user, Goods goods) {
		// TODO Auto-generated method stub
		GoodsUserLike goodsUserLike = findByUserAndGoods(user,goods);
		
		if(null == goodsUserLike)
		{
			goodsUserLike = new GoodsUserLike(goods,user);
			insertGoodsUserLike(goodsUserLike);
		}
		else
		{
			deleteGoodsUserLike(goodsUserLike);
		}
	}

	@Override
	public Integer findyLikeUserNumByGoods(Goods goods) {
		// TODO Auto-generated method stub

	   List<GoodsUserLike> list = findByGoods(goods);
	   
	   if(null == list || list.isEmpty())
	   {
		   return 0;
	   }
	   
	   return list.size();
	}

}
