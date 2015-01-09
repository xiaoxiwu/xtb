package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserSetting;
import com.xt8.service.SettingService;
import com.xt8.service.UserService;
import com.xt8.service.UserSettingService;

public class TestUserSettingService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userv = (UserService) ctx.getBean("userService");
	private SettingService settingService = (SettingService) ctx.getBean("settingService");
	private UserSettingService userSettingService = (UserSettingService) ctx.getBean("userSettingService");
	
	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertUserSetting() {
		//fail("Not yet implemented");
		Setting setting = settingService.findById(1);
		User user = userv.findById(10);
		
		UserSetting userSetting = new UserSetting(user,setting,0);
		userSetting.setUpdatedTime(new Date());
		
		userSettingService.save(userSetting);
		
	
	}

	@Test
	public void testFindByUserAndSetting() {
	//	fail("Not yet implemented");
		Setting setting = settingService.findById(1);
		User user = userv.findById(10);
		
		UserSetting userSetting = userSettingService.findByUserAndSetting(user, setting);
		
		System.out.println(userSetting.getValue()+" by " +userSetting.getUpdatedTime());
	}
	
	@Test
	public void testIfHasSetting()
	{
		User user = userv.findById(10);
		Setting setting = settingService.findById(1);
		
		boolean result = userSettingService.ifOpenThisSetting(user, setting);
		
		if(result)
		{
			System.out.println("already opened");
		}
		else
		{
			System.out.println("already closed");
		}
	}
	


}
