package com.xt8.service.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Mood;
import com.xt8.model.User;
import com.xt8.service.MoodService;
import com.xt8.service.UserService;

public class TestMoodService {
	
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private MoodService moodServ = (MoodService) ctx.getBean("moodService");
	private UserService userServ = (UserService) ctx.getBean("userService");

	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByUserWithPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByUsersWithPage() {
		List<User> users = new ArrayList<User>();
		User user = userServ.findById(161);
		users.add(user);
		List<Mood> moods = moodServ.findByUsersWithPage(users, 0, 20, "time");
		System.out.println(moods.size());
	}

	@Test
	public void testDeleteMood() {
		fail("Not yet implemented");
	}

}
