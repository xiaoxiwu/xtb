package com.xt8.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Express;
import com.xt8.model.ExpressSite;
import com.xt8.service.ExpressService;

public class TestExpressService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private ExpressService expressServiceImpl = (ExpressService) ctx
			.getBean("expressService");

//	@Test
//	public void testInsert() {
//		Express express = new Express();
//		express.setName("中通快递");
//		express.setContactPerson("张三");
//		express.setTel("15811468956");
//		express.setRemark("测试");
//		Express express1 = expressServiceImpl.insertExpress(express);
//		System.out.println(express1.getId());
//	}
//
//	@Test
//	public void testListExpress() {
//		List<Express> expList = expressServiceImpl.listExpress();
//		for (int i = 0; i < expList.size(); i++) {
//			System.out.println(expList.get(i).getName());
//		}
//	}

	@Test
	public void testFindById() {
		Express express1 = expressServiceImpl.findById(3);
		if (null != express1)
			System.out.println(express1.getName()+"  site:"+((ExpressSite)express1.getExpSiteList().toArray()[0]).getSiteName());
	}

}
