package com.xt8.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Express;
import com.xt8.model.ExpressSite;
import com.xt8.model.Image;
import com.xt8.service.ExpressSiteService;

public class TestExpressSiteService {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private ExpressSiteService expressSiteServiceImpl = (ExpressSiteService) ctx
			.getBean("expressSiteService");

	@Test
	public void testInsert() {
		ExpressSite expSite = new ExpressSite();
		expSite.setExpressId(3);
		expSite.setRegisterTime(new Date());
		expSite.setHeadTel("15869741152");
		expSite.setRemark("Test");
		expSite.setSiteAddress("大学城");
		expSite.setSiteCoverArea("大学城");
		expSite.setSiteHead("张茂");
		expSite.setSiteName("大学城");
		Image passportImage=new Image();
		passportImage.setImageId(1);
		expSite.setPassportImage(passportImage);
		
		ExpressSite expSite1 = expressSiteServiceImpl.insertExpressSite(expSite);
		System.out.println(expSite1.getId());
	}
}
