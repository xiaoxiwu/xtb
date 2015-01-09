package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.User;
import com.xt8.service.UserService;
import com.xt8.util.EnableLog;

@Transactional
public class UserServiceImpl extends BasicServiceImpl implements UserService {

	@Override
	public User findById(Serializable id) {
		return (User) super.findById(User.class, id);
	}

	@Override
	public User checkUser(User user) {
		String hql = "from User bean where bean.phoneNumber=?";
		Object[] params = { user.getPhoneNumber() };
		List list = executeQuery(hql, params);
		if (list.size() == 0) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}

	@Override
	public User findByApiKey(String apiKey) {
		String hql = "from User bean where bean.apiKey=?";
		Object[] params = { apiKey };
		List list = executeQuery(hql, params);
		if (list.size() == 0) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}

	@Override
	public User insert(User user) {
		return (User) super.saveAndReturn(User.class, user);
	}

	@Override
	public User findByPhone(String phoneNumber) {
		// TODO Auto-generated method stub

		String hql = "from User bean where bean.phoneNumber=?";
		Object[] params = {phoneNumber};
		List list = executeQuery(hql, params);
		if (list.size() == 0) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}

	@Override
	public User findByHtNumber(String HtNumber) {
		// TODO Auto-generated method stub
		String hql = "from User bean where bean.heartbeatNumber=?";
		Object[] params = {HtNumber};
		List list = executeQuery(hql, params);
		if (list.size() == 0) {
			return null;
		} else {
			return (User) list.get(0);
		}
	}

}
