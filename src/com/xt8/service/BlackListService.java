package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.BlackList;
import com.xt8.model.Province;
import com.xt8.model.User;

public interface BlackListService extends BasicService {
	
	public BlackList insertBlackList(BlackList blackList);
	
	public BlackList findById(Serializable id);

	public BlackList findByTwoUser(User user, User blackUser);
	
	public void removeUserFromBlackList(BlackList blackList);
}
