package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.BlackList;
import com.xt8.model.MoodAuthorization;
import com.xt8.model.Province;
import com.xt8.model.User;

public interface MoodAuthorizationService extends BasicService {
	
	public MoodAuthorization insertMoodAuthorization(MoodAuthorization moodAuthorization);
	
	public MoodAuthorization findById(Serializable id);

	public MoodAuthorization findByTwoUser(User userHost, User userLimited);
	
	public void removeUserFromMoodAuthorization(MoodAuthorization moodAuthorization);
}
