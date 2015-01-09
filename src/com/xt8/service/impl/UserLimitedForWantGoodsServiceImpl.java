package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import com.xt8.model.User;
import com.xt8.model.UserLimitedForWantGoods;
import com.xt8.service.UserLimitedForWantGoodsService;

@Transactional
public class UserLimitedForWantGoodsServiceImpl extends BasicServiceImpl implements UserLimitedForWantGoodsService {

	@Override
	public UserLimitedForWantGoods insertUserLimitedForWantGoods(
			UserLimitedForWantGoods userLimitedForWantGoods) {
		// TODO Auto-generated method stub
		Serializable id = super.save(userLimitedForWantGoods);
		return findById(id);
	}

	@Override
	public UserLimitedForWantGoods findById(Serializable id) {
		// TODO Auto-generated method stub
		return (UserLimitedForWantGoods) super.findById(UserLimitedForWantGoods.class, id);
	}

	@Override
	public List<UserLimitedForWantGoods> findByHostuser(User Hostuser) {
		// TODO Auto-generated method stub
		String hql = "from UserLimitedForWantGoods bean where bean.userHost = ?";
		Object[] params = {Hostuser};
		List<UserLimitedForWantGoods> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}

	@Override
	public UserLimitedForWantGoods findyByHostuserAndLimiteduser(User Hostuser,
			User Limiteduser) {
		// TODO Auto-generated method stub
		String hql = "from UserLimitedForWantGoods bean where bean.userHost = ? and bean.userLimited = ?";
		Object[] params = {Hostuser,Limiteduser};
		List<UserLimitedForWantGoods> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public List<User> findLimiteduserByHostuser(User Hostuser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ifuserhostLimitedByuserlimited(User hostUser,
			User limitedUser) {
		// TODO Auto-generated method stub
		UserLimitedForWantGoods userLimitedForWantGoods = findyByHostuserAndLimiteduser(hostUser,limitedUser);
		
		if(null == userLimitedForWantGoods)
			return false;
		else
			return true;
	}

	@Override
	public void deleteUserLimitedForWantGoods(
			UserLimitedForWantGoods userLimitedForWantGoods) {
		// TODO Auto-generated method stub
		super.delete(userLimitedForWantGoods);
		
	}


}
