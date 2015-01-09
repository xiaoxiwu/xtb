package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Category;
import com.xt8.model.EvaluateIndex;
import com.xt8.model.User;
import com.xt8.model.UserEvaluateIndex;
import com.xt8.model.UserSetting;
import com.xt8.service.UserEvaluateIndexService;

@Transactional
public class UserEvaluateIndexServiceImpl extends BasicServiceImpl implements
		UserEvaluateIndexService {

	@Override
	public UserEvaluateIndex insertUserEvaluateIndex(
			UserEvaluateIndex userEvaluateIndex) {
		// TODO Auto-generated method stub
		Serializable rid = super.save(userEvaluateIndex);
		return findById(rid);
	}

	@Override
	public UserEvaluateIndex findById(Serializable id) {
		// TODO Auto-generated method stub
		return (UserEvaluateIndex) super.findById(UserEvaluateIndex.class, id);
	}

	@Override
	public List<UserEvaluateIndex> findByUser(User user) {
		// TODO Auto-generated method stub
		String hql = "from UserEvaluateIndex bean where bean.user = ?";
		Object[] params = { user };
		List<UserEvaluateIndex> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public void updateUserEvaluateIndex(UserEvaluateIndex userEvaluateIndex) {
		// TODO Auto-generated method stub
		super.update(userEvaluateIndex);

	}

	@Override
	public UserEvaluateIndex findByUserAndIndex(User user,
			EvaluateIndex evaluateIndex) {
		// TODO Auto-generated method stub
		String hql = "from UserEvaluateIndex bean where bean.user=? and bean.indexSort=?";
		Object[] params = { user, evaluateIndex };

		List<UserEvaluateIndex> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
