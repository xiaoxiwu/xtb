package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.Goods;
import com.xt8.model.GoodsUserWant;
import com.xt8.model.User;

public interface GoodsUserWantService extends BasicService {
	
	public GoodsUserWant insertGoodsUserWant(GoodsUserWant goodsUserWant);
	
	public GoodsUserWant findById(Serializable id);
	
	public List<GoodsUserWant> findByGoods(Goods goods);
	
	public List<GoodsUserWant> findByUser(User user);
	
	public GoodsUserWant findByUserAndGoods(User user,Goods goods);
	
	public void deleteGoodsUserWant(GoodsUserWant goodsUserWant);
	
	public List<User> findWantedUserByGoodsBySort(Goods goods,EvaluateIndex eIndex);
	
	public Integer findyWantedUserNumByGoods(Goods goods);
	
	public void SetWantedUserForGoods(User user,Goods goods);

}
