package com.xt8.service.impl;

import java.util.List;

import com.xt8.model.User;
import com.xt8.model.VerifyFriend;
import com.xt8.service.VerifyFriendService;

public class VerifyFriendServiceImpl extends BasicServiceImpl implements
		VerifyFriendService {

	@Override
	public List<VerifyFriend> findByFstUser(User fstUser) {
		String hql = "from VerifyFriend bean where bean.fstUser=?";
		Object[] params = { fstUser };
		return super.executeQuery(hql, params);
	}

	@Override
	public VerifyFriend findByFstAndSecUser(User fstUser, User secUser) {
		String hql = "from VerifyFriend bean where bean.fstUser=? and bean.secUser=?";
		Object[] params = { fstUser, secUser };
		List<VerifyFriend> list = super.executeQuery(hql, params);
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
