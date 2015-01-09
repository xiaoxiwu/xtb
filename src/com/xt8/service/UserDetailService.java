package com.xt8.service;

import com.xt8.model.User;
import com.xt8.model.UserDetail;

public interface UserDetailService extends BasicService {

	public UserDetail insert(UserDetail userDetail);
	
	public UserDetail findByUser(User user);

}
