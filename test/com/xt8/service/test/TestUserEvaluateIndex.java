package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.User;
import com.xt8.model.UserEvaluateIndex;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.UserEvaluateIndexService;
import com.xt8.service.UserService;

public class TestUserEvaluateIndex {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private EvaluateIndexService evaluateIndexService = (EvaluateIndexService) ctx.getBean("evaluateIndexService");
	private UserEvaluateIndexService userEvaluateIndexService = (UserEvaluateIndexService) ctx.getBean("userEvaluateIndexService");
	private UserService  userService = (UserService) ctx.getBean("userService");
	
	@Test
	public void testInsertUserEvaluateIndex() {
		//fail("Not yet implemented");
		User user = userService.findById(3);
		EvaluateIndex evaluateIndex= evaluateIndexService.findById(4);
		
		UserEvaluateIndex userEvaluateIndex = new UserEvaluateIndex(user,evaluateIndex);
		userEvaluateIndex.setUpdatedTime(new Date());
		userEvaluateIndex.setScore(5.5);
		
		userEvaluateIndexService.insertUserEvaluateIndex(userEvaluateIndex);
		
	}

	@Test
	public void testFindByIdSerializable() {
	//	fail("Not yet implemented");
		UserEvaluateIndex userEvaluateIndex = userEvaluateIndexService.findById(1);
		System.out.println(userEvaluateIndex.getScore());
	}

	@Test
	public void testFindByUser() {
		//fail("Not yet implemented");
		User user = userService.findById(10);
		List<UserEvaluateIndex> list = userEvaluateIndexService.findByUser(user);
		
		if(null != list)
		{
			System.out.println(list.get(0).getScore());
		}
	}

	@Test
	public void testUpdateUserEvaluateIndex() {
		//fail("Not yet implemented");
		UserEvaluateIndex userEvaluateIndex = userEvaluateIndexService.findById(1);
		userEvaluateIndex.setRank(10);
		
		userEvaluateIndexService.updateUserEvaluateIndex(userEvaluateIndex);
	}

	@Test
	public void testFindByUserAndIndex() {
	//	fail("Not yet implemented");
		User user = userService.findById(10);
		EvaluateIndex evaluateIndex= evaluateIndexService.findById(1);
		
		UserEvaluateIndex userEvaluateIndex = userEvaluateIndexService.findByUserAndIndex(user, evaluateIndex);
		
		if(null != userEvaluateIndex){
			System.out.println(userEvaluateIndex.getRank());
		}
	}

}
