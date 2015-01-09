package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.User;
import com.xt8.model.UserCommAddr;

public interface UserCommAddrService extends BasicService {
	
	public UserCommAddr insertUserCommAddr(UserCommAddr userCommAddr);
	
	public UserCommAddr findById(Serializable id);
	
	public List<UserCommAddr> findByUser(User user);

	public void deleteUserCommAddr(UserCommAddr userCommAddr);
}
