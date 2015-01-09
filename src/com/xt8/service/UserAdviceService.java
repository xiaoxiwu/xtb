package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.UserAdvice;

public interface UserAdviceService extends BasicService {
	
	public UserAdvice insertUserAdvice(UserAdvice userAdvice);
	
	public UserAdvice findById(Serializable id);
	
	public List<UserAdvice> findAllAdvice();

}
