package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Goods;
import com.xt8.model.GoodsUserLike;
import com.xt8.model.Setting;
import com.xt8.model.User;

public interface GoodsUserLikeService extends BasicService {
	
	public GoodsUserLike insertGoodsUserLike(GoodsUserLike goodsUserLike);
	
	public GoodsUserLike findById(Serializable id);
	
	public List<GoodsUserLike> findByGoods(Goods goods);
	
	public List<GoodsUserLike> findByUser(User user);
	
	public GoodsUserLike findByUserAndGoods(User user,Goods goods);
	
	public void deleteGoodsUserLike(GoodsUserLike goodsUserLike);
	
	public List<User> findLikeUserByGoods(Goods goods);
	
	public void SetLikeUserForGoods(User user,Goods goods);

	public Integer findyLikeUserNumByGoods(Goods goods);
}
