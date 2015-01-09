package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.City;
import com.xt8.model.Province;
import com.xt8.service.CityService;

public class TestCityService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private CityService cityService = (CityService) ctx.getBean("cityService");
	
	@Test
	public void testFindByIdSerializable() {
		
		City city = cityService.findById(230300);
		Province province = city.getProvince();
		
		if(null != city && province != null)
		{
			System.out.println(province.getProvinceName()+" "+city.getCityName());
		}
	}

}
