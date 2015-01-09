package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.User;

public interface UserService extends BasicService {
	public User findById(Serializable id);
	
	public User insert(User user);

	public User checkUser(final User user);

	public User findByApiKey(final String apiKey);
	
	public User findByPhone(final String phoneNumber);
	
	public User findByHtNumber(final String HtNumber);

}
