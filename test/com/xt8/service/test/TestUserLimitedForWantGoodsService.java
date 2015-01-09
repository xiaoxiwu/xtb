package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.User;
import com.xt8.model.UserLimitedForWantGoods;
import com.xt8.service.UserLimitedForWantGoodsService;
import com.xt8.service.UserService;

public class TestUserLimitedForWantGoodsService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userService = (UserService) ctx.getBean("userService");
	private UserLimitedForWantGoodsService userLimitedForWantGoodsService = (UserLimitedForWantGoodsService) ctx.getBean("userLimitedForWantGoodsService");
	
	@Test
	public void testInsertUserLimitedForWantGoods() {
	//	fail("Not yet implemented");
		User userHost = userService.findById(2);
		User userLimited = userService.findById(10);
		UserLimitedForWantGoods userLimitedForWantGoods = new UserLimitedForWantGoods(userHost,userLimited);
		userLimitedForWantGoods.setAddTime(new Date());
		userLimitedForWantGoodsService.insertUserLimitedForWantGoods(userLimitedForWantGoods);
		
	}

	@Test
	public void testFindByIdSerializable() {
	//	fail("Not yet implemented");
		UserLimitedForWantGoods userLimitedForWantGoods = userLimitedForWantGoodsService.findById(1);
		if(null != userLimitedForWantGoods)
		{
			System.out.println(userLimitedForWantGoods.getAddTime());
		}
	}

	@Test
	public void testFindByHostuser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindyByHostuserAndLimiteduser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLimiteduserByHostuser() {
		fail("Not yet implemented");
	}

	@Test
	public void testIfuserhostLimitedByuserlimited() {
	//	fail("Not yet implemented");
		User userHost = userService.findById(10);
		User userLimited = userService.findById(11);
		
		boolean result = userLimitedForWantGoodsService.ifuserhostLimitedByuserlimited(userHost, userLimited);
		if(result)
		{
			System.out.println("yes");
		}
		else
		{
			System.out.println("no");
		}
		
	}

}
