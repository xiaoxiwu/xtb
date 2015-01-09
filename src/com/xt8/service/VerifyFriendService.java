package com.xt8.service;

import java.util.List;

import com.xt8.model.User;
import com.xt8.model.VerifyFriend;

public interface VerifyFriendService extends BasicService {

	public List<VerifyFriend> findByFstUser(User fstUser);

	public VerifyFriend findByFstAndSecUser(User fstUser, User secUser);

}
