package com.xt8.service.impl;

import static com.xt8.util.Constants.MAX_RECORDS;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Friends;
import com.xt8.model.User;
import com.xt8.service.FriendsService;
import com.xt8.util.Logic;
import com.xt8.util.StringUtil;

@Transactional
public class FriendsServiceImpl extends BasicServiceImpl implements
		FriendsService {

	@Override
	public Friends findById(Serializable id) {
		return (Friends) super.findById(User.class, id);
	}

	@Override
	public List<User> findBySelfId(Integer id) {
		String hql = "select bean2 from Friends bean1,User bean2 "
				+ "where bean1.selfId=? and bean1.frId=bean2.userId";
		Object[] params = { id };
		return executeQuery(hql, params);
	}

	@Override
	public List<User> findByFrId(Integer id) {
		String hql = "from Friends where frId=?";
		Object[] params = { id };
		return executeQuery(hql, params);
	}

	@Override
	public Friends findBySelfAndFrId(Integer selfId, Integer frId) {
		String hql = "from Friends bean where bean.selfId=? and bean.frId=?";
		Object[] params = { selfId, frId };
		List<Friends> list = super.executeQuery(hql, params);
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<User> patternQueryFriendsByPage(Integer selfId,
			String condition, int pageIndex, int pageSize) throws SQLException {
		String sql = "select t1.userId,t1.nickName,t1.heartbeatNumber,t1.level,"
				+ "t1.avatar,t1.personalizedSignature,t1.gender,t1.state,t1.phoneNumber "
				+ "from tb_user t1,tb_friends t2 "
				+ "where t1.userId=t2.frId and t2.selfId=? "
				+ "and (t1.phoneNumber like ? or t1.heartbeatNumber like ? or t1.nickName like ?)";
		String str = "";
		if (StringUtil.isNullOrBlank(condition)) {
			str = "";
		} else {
			str = condition;
		}
		str += "%";
		Object[] params = { selfId, str, str, str };
		ResultSet rs = super.originalQueryByPage(sql, params, pageIndex,
				pageSize);
		List<User> list = new ArrayList<User>();
		while (null != rs && rs.next()) {
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setNickName(rs.getString("nickName"));
			u.setHeartbeatNumber(rs.getString("heartbeatNumber"));
			u.setLevel(rs.getInt("level"));
			u.setAvatar(rs.getString("avatar"));
			u.setPersonalizedSignature(rs.getString("personalizedSignature"));
			u.setGender(rs.getInt("gender"));
			u.setState(rs.getInt("state"));
			u.setPhoneNumber(rs.getString("phoneNumber"));
			list.add(u);
		}
		rs.close();
		return list;
	}

	@Override
	public List<User> patternQueryFriends(Integer selfId, String condition)
			throws SQLException {
		return patternQueryFriendsByPage(selfId, condition, 1, MAX_RECORDS);
	}

}
