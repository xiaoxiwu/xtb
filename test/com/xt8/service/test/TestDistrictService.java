package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.City;
import com.xt8.model.District;
import com.xt8.model.Province;
import com.xt8.service.DistrictService;

public class TestDistrictService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private DistrictService districtService = (DistrictService) ctx.getBean("districtService");
	
	@Test
	public void testFindByIdSerializable() {
		
		District dist = districtService.findById(150421);
		City city = dist.getCity();
		Province province = city.getProvince();
		if(null != city && dist != null && province != null)
		{
			System.out.println(province.getProvinceName()+" "+city.getCityName()+" "+dist.getDistName());
		}
	}

}
