package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Province;
import com.xt8.service.ProvinceService;


public class TestProvinceService {

	
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private ProvinceService provinceService = (ProvinceService) ctx.getBean("provinceService");

	
	@Test
	public void testFindByIdSerializable() {
		
		Province province = provinceService.findById(370000);
		
		if(null != province)
		{
			System.out.println(province.getProvinceName());
		}
		
	}

}
