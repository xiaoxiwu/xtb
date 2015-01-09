package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Category;
import com.xt8.model.City;
import com.xt8.model.Goods;
import com.xt8.model.Province;
import com.xt8.model.User;
import com.xt8.service.CategoryService;
import com.xt8.service.CityService;
import com.xt8.service.DistrictService;
import com.xt8.service.GoodsService;
import com.xt8.service.ProvinceService;
import com.xt8.service.UserService;
import com.xt8.timer.ReminderGenerateReceiver;

public class TestGoodsService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
	private CategoryService categoryService = (CategoryService) ctx.getBean("categoryService");
	private ProvinceService provinceService = (ProvinceService) ctx.getBean("provinceService");
	private CityService cityService = (CityService) ctx.getBean("cityService");
	private UserService userv = (UserService) ctx.getBean("userService");
	
	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertGoods() {
		
		Category cate = categoryService.findByCategoryId(100001);
		Province province = provinceService.findById(370000);
		City city = cityService.findById(370200);
		User user = userv.findById(2);
		
		Goods goods = new Goods("C++ Primer",cate,province,city,0,user,1);
		goods.setTag("计算机程序设计 c++");
		goods.setDescription("站在巨人的肩膀上,深入C++编程的世界");
		Date date = new Date();
		goods.setExpireTime(date);
		
		Goods result = goodsService.insert(goods);
		System.out.println(result.getDescription());
	}
	
	@Test
	public void testTimer(){
		Goods resultgoods = goodsService.findById(3);
		new ReminderGenerateReceiver(1, 2 * 3600, resultgoods);
	}

}
