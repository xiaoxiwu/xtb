package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Setting;
import com.xt8.service.SettingService;
import com.xt8.service.UserSettingService;

public class TestSettingService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private SettingService settingService = (SettingService) ctx.getBean("settingService");
	
	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertSettingCategory(){
		Setting setting = new Setting("是否接收发送快件请求");
		settingService.insertSettingCategory(setting);
	}

}
