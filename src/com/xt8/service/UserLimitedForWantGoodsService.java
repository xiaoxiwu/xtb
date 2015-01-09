package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.User;
import com.xt8.model.UserLimitedForWantGoods;


public interface UserLimitedForWantGoodsService extends BasicService {
	
	public UserLimitedForWantGoods insertUserLimitedForWantGoods(UserLimitedForWantGoods userLimitedForWantGoods);
	
	public UserLimitedForWantGoods findById(Serializable id);

	public List<UserLimitedForWantGoods> findByHostuser(User Hostuser);
	
	public UserLimitedForWantGoods findyByHostuserAndLimiteduser(User Hostuser,User Limiteduser);
	
	public List<User> findLimiteduserByHostuser(User Hostuser);
	
	public boolean ifuserhostLimitedByuserlimited(User hostUser,User limitedUser);

	public void deleteUserLimitedForWantGoods(UserLimitedForWantGoods userLimitedForWantGoods);

}
