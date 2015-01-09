package com.xt8.service.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Friends;
import com.xt8.model.User;
import com.xt8.service.FriendsService;
import com.xt8.service.UserService;
import com.xt8.util.Logic;

public class TestFriendsService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private FriendsService friendsServ = (FriendsService) ctx
			.getBean("friendsService");

	@Test
	public void testOriginalSql() throws SQLException {
		String sql = "select * from tb_user t where t.userId>? and t.nickName like ?";
		Object[] params = { new Integer(0), "Ten" };
		List<User> list = friendsServ
				.patternQueryFriendsByPage(1, "A", 1, 20);
		for (User u : list) {
			p(u.getPhoneNumber() + "\t" + u.getNickName());
		}
	}

	@Test
	public void testSave() {
		String hql = "from User bean where bean.userId>? and bean.userId<?";
		Object[] params = { 10, 40 };
		List<User> list = friendsServ.executeQuery(hql, params);
		Integer uid = 104;
		for (User u : list) {
			Friends friends = new Friends(uid, u.getUserId(), new Date());
			Friends iFriends = new Friends(u.getUserId(), uid, new Date());
			friendsServ.save(friends);
			friendsServ.save(iFriends);
		}
	}

	private void p(Object obj) {
		System.out.println(obj);
	}

}
