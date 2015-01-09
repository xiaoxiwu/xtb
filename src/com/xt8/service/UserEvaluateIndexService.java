package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.User;
import com.xt8.model.UserEvaluateIndex;

public interface UserEvaluateIndexService extends BasicService {
	
	public UserEvaluateIndex insertUserEvaluateIndex(UserEvaluateIndex userEvaluateIndex);
	
	public UserEvaluateIndex findById(Serializable id);
	
	public List<UserEvaluateIndex> findByUser(User user);
	
	public void updateUserEvaluateIndex(UserEvaluateIndex userEvaluateIndex);
	
	public UserEvaluateIndex findByUserAndIndex(User user,EvaluateIndex evaluateIndex);

}
