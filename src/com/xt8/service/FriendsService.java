package com.xt8.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.xt8.model.Friends;
import com.xt8.model.User;

public interface FriendsService extends BasicService {
	public Friends findById(Serializable id);

	public List<com.xt8.model.User> findBySelfId(Integer id);

	public List<com.xt8.model.User> findByFrId(Integer id);

	public Friends findBySelfAndFrId(Integer selfId, Integer frId);

	public List<User> patternQueryFriendsByPage(Integer selfId,
			String condition, int pageIndex, int pageSize) throws SQLException;

	public List<User> patternQueryFriends(Integer selfId, String condition)
			throws SQLException;

}
