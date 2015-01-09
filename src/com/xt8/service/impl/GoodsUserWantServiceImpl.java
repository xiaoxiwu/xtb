package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.Goods;
import com.xt8.model.GoodsUserLike;
import com.xt8.model.GoodsUserWant;
import com.xt8.model.User;
import com.xt8.service.GoodsUserWantService;

@Transactional
public class GoodsUserWantServiceImpl extends BasicServiceImpl implements
		GoodsUserWantService {

	@Override
	public GoodsUserWant insertGoodsUserWant(GoodsUserWant goodsUserWant) {
		// TODO Auto-generated method stub
		Serializable id = super.save(goodsUserWant);

		return findById(id);
	}

	@Override
	public GoodsUserWant findById(Serializable id) {

		return (GoodsUserWant) super.findById(GoodsUserWant.class, id);

	}

	@Override
	public List<GoodsUserWant> findByGoods(Goods goods) {
		// TODO Auto-generated method stub
		String hql = "from GoodsUserWant bean where bean.goods = ?";
		Object[] params = { goods };
		List<GoodsUserWant> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<GoodsUserWant> findByUser(User user) {
		// TODO Auto-generated method stub
		String hql = "from GoodsUserWant bean where bean.user = ?";
		Object[] params = { user };
		List<GoodsUserWant> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public GoodsUserWant findByUserAndGoods(User user, Goods goods) {
		// TODO Auto-generated method stub
		String hql = "from GoodsUserWant bean where bean.user=? and bean.goods=?";
		Object[] params = { user, goods };

		List<GoodsUserWant> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void deleteGoodsUserWant(GoodsUserWant goodsUserWant) {
		// TODO Auto-generated method stub
		super.delete(goodsUserWant);
	}

	@Override
	public List<User> findWantedUserByGoodsBySort(Goods goods, EvaluateIndex eIndex) {
		// TODO Auto-generated method stub
		String hql = "select bean1.user "
				+ "from GoodsUserWant bean1,UserEvaluateIndex bean2 "
				+ "where bean1.user=bean2.user "
				+ "and bean1.goods=? and bean2.indexSort=?"
				+ "order by bean1.id asc,bean2.score desc";
		Object[] params = { goods, eIndex };
		List<User> list = super.executeQuery(hql, params);

		return list;
	}

	@Override
	public void SetWantedUserForGoods(User user, Goods goods) {
		// TODO Auto-generated method stub

		GoodsUserWant goodsUserWant = findByUserAndGoods(user, goods);

		if (null == goodsUserWant) {
			goodsUserWant = new GoodsUserWant(goods, user);
			insertGoodsUserWant(goodsUserWant);
		} else {
			deleteGoodsUserWant(goodsUserWant);
		}
	}

	@Override
	public Integer findyWantedUserNumByGoods(Goods goods) {
		// TODO Auto-generated method stub
		   List<GoodsUserWant> list = findByGoods(goods);
		   
		   if(null == list || list.isEmpty())
		   {
			   return 0;
		   }
		   
		   return list.size();
	}


}
