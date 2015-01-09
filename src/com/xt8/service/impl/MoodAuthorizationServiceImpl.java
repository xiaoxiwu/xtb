package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;



import com.xt8.model.MoodAuthorization;

import com.xt8.model.User;
import com.xt8.service.MoodAuthorizationService;

@Transactional
public class MoodAuthorizationServiceImpl extends BasicServiceImpl implements MoodAuthorizationService {


	@Override
	public MoodAuthorization findById(Serializable id) {
		return (MoodAuthorization) super.findById(MoodAuthorization.class, id);
	}

	@Override
	public MoodAuthorization insertMoodAuthorization(MoodAuthorization MoodAuthorization) {
		// TODO Auto-generated method stub
		Serializable id = super.save(MoodAuthorization);
		
		return findById(id);
	}

	@Override
	public MoodAuthorization findByTwoUser(User userHost, User userLimited) {
		// TODO Auto-generated method stub

		String hql = "from MoodAuthorization bean where bean.userHost=? and bean.userLimited=?";
		Object[] params = {userHost,userLimited};
		
		List<MoodAuthorization> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}

	@Override
	public void removeUserFromMoodAuthorization(MoodAuthorization MoodAuthorization) {
		// TODO Auto-generated method stub
		super.delete(MoodAuthorization);
	}
}
