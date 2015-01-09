package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;




import com.xt8.model.UserAdvice;
import com.xt8.service.UserAdviceService;

@Transactional
public class UserAdviceServiceImpl extends BasicServiceImpl implements UserAdviceService {

	@Override
	public UserAdvice insertUserAdvice(UserAdvice userAdvice) {
		// TODO Auto-generated method stub
		Serializable id = super.save(userAdvice);
		return findById(id);
	}

	@Override
	public UserAdvice findById(Serializable id) {
		// TODO Auto-generated method stub
		return (UserAdvice) super.findById(UserAdvice.class, id);
	}

	@Override
	public List<UserAdvice> findAllAdvice() {
		// TODO Auto-generated method stub
		
	//	String hql = "from UserAdvice bean";
		return null;
	}


}
