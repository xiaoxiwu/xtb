package com.xt8.service.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.User;
import com.xt8.service.UserService;
import com.xt8.util.Logic;

public class TestUserService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userv = (UserService) ctx.getBean("userService");

	@Test
	public void testCheckUser() {
		User user = new User();
		user.setPhoneNumber("15574541145");
		User quser = userv.checkUser(user);
		System.out.println(quser.getPlainPassword());
	}

	private String genRandomPhoneNumber() {
		StringBuffer[] baseStrings = { new StringBuffer("152"),
				new StringBuffer("155"), new StringBuffer("150"),
				new StringBuffer("151"), new StringBuffer("188"),
				new StringBuffer("139"), };
		Random r = new Random();
		StringBuffer baseString = baseStrings[r.nextInt(baseStrings.length)];
		// generate 8 digit randomly

		for (int i = 0; i < 8; i++) {
			int d = r.nextInt(10);
			baseString.append(d);
		}
		return baseString.toString();
	}

	private String genRandomName() {
		Random r = new Random();
		StringBuffer baseString = new StringBuffer();
		baseString.append((char) ('A' + r.nextInt(26)));
		for (int i = 0; i < 5; i++) {
			char ch = (char) ('a' + r.nextInt(26));
			baseString.append(ch);
		}
		return baseString.toString();
	}

	@Test
	public void testSave() {
		// 批处理添加多个用户
		int count = 50;
		for (int i = 0; i < count; i++) {
			String phone = genRandomPhoneNumber();
			String nickName = genRandomName();
			User user = new User(phone, "111", nickName, "1234");
			userv.save(user);
		}
	}

	@Test
	public void testUpdate() {
		User user = new User("12289789982", "111", "Texman", "1234");
		user.setPersonalizedSignature("Hello, I am Texman2");
		user.setUserId(1);
		userv.update(user);
	}

	@Test
	public void testDelete() {
		User user = new User();
		user.setUserId(9);
		userv.delete(user);
	}

	@Test
	public void testDeleteAll() {
		Integer[] ids = { new Integer(10), new Integer(11), new Integer(12) };
		Set users = new HashSet();
		for (Integer id : ids) {
			User u = new User();
			u.setUserId(id);
			users.add(u);
		}

		for (Integer id : ids) {
			User u = userv.findById(id);
			users.add(u);
		}
		userv.deleteByBatch(users);
	}

	@Test
	public void testExecuteQuery() {
		String hql = "from User bean where bean.userId>?";
		Object[] params = { new Integer(10) };
		List<User> list = userv.executeQuery(hql, params);
		for (User u : list) {
			System.out.println(u.getNickName());
		}
	}

	@Test
	public void testExecuteQueryByPage() {
		String hql = "from User bean where bean.userId>?";
		Object[] params = { new Integer(10) };
		int currentPage = 1;
		int pageSize = 5;
		List<User> list = userv.executeQueryByPage(hql, params, currentPage,
				pageSize);
		for (User u : list) {
			System.out.println(u.getNickName());
		}
	}

	@Test
	public void testOriginalSql() throws SQLException {
		String sql = "select * from tb_user t where t.userId>? and t.nickName like ?";
		Object[] params = { new Integer(0), "Ten%" };
		ResultSet rs = userv.originalQuery(sql, params);
		p(rs.toString());
	}
	

	@Test
	public void testPatternQuery() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", "185");
		map.put("heartbeatNumber", "155");
		map.put("nickName", "Teng");
		List<User> list = userv.patternQuery(User.class, map, Logic.AND);
		for (User u : list) {
			p(u.getUserId() + "\t" + u.getPhoneNumber());
		}
	}

	@Test
	public void testCount() {
		String hql = "select count(*) from User bean where bean.userId>?";
		Object[] params = { new Integer(0) };
		long resCount = userv.count(hql, params);
		p(resCount);
	}

	private void p(Object obj) {
		System.out.println(obj);
	}

}
