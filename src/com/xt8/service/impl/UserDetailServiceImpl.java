package com.xt8.service.impl;

import java.util.List;

import com.xt8.model.User;
import com.xt8.model.UserDetail;
import com.xt8.service.UserDetailService;

public class UserDetailServiceImpl extends BasicServiceImpl implements
		UserDetailService {

	@Override
	public UserDetail insert(UserDetail userDetail) {
		return (UserDetail) super.saveAndReturn(UserDetail.class, userDetail);
	}

	@Override
	public UserDetail findByUser(User user) {
		String hql = "from UserDetail bean where bean.user=?";
		Object[] params = { user };
		List<UserDetail> list = super.executeQuery(hql, params);
		if ((null == list) || (0 == list.size())) {
			return null;
		}
		return list.get(0);
	}
}
