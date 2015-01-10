package com.xt8.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Express;
import com.xt8.model.ExpressSite;
import com.xt8.model.Image;
import com.xt8.service.ExpressService;
import com.xt8.service.ExpressSiteService;
import com.xt8.service.ImageService;

public class TestExpressSiteService {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private ExpressSiteService expressSiteServiceImpl = (ExpressSiteService) ctx
			.getBean("expressSiteService");
	private ExpressService expressServiceImpl = (ExpressService) ctx
	.getBean("expressService");
	private ImageService imageServiceImpl = (ImageService) ctx
	.getBean("imageService");

//	@Test
//	public void testInsert() {
//		ExpressSite expSite = new ExpressSite();
//		expSite.setExpress(expressServiceImpl.findById(3));
//		expSite.setRegisterTime(new Date());
//		expSite.setHeadTel("15869741152");
//		expSite.setSiteTel("15811475982");
//		expSite.setRemark("Test");
//		expSite.setSiteAddress("大学城");
//		expSite.setSiteCoverArea("大学城");
//		expSite.setSiteHead("张茂");
//		expSite.setSiteName("大学城");
//		expSite.setPassportImage(imageServiceImpl.findById(1));
//		
//		ExpressSite expSite1 = expressSiteServiceImpl.insertExpressSite(expSite);
//		System.out.println(expSite1.getExpress().getName());
//	}
	@Test
	public void testFindById() {
		ExpressSite expSite1 = expressSiteServiceImpl.findById(3);
		System.out.println(expSite1.getExpress().getName());
	}
}
