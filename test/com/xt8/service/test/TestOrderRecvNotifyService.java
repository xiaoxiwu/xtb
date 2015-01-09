package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.City;
import com.xt8.model.Order;
import com.xt8.model.OrderRecvNotify;
import com.xt8.model.Province;
import com.xt8.service.CityService;
import com.xt8.service.GoodsService;
import com.xt8.service.OrderRecvNotifyService;
import com.xt8.service.OrderService;
import com.xt8.service.ProvinceService;
import com.xt8.service.UserService;

public class TestOrderRecvNotifyService {


	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
	private OrderService orderService = (OrderService) ctx.getBean("orderService");
	private UserService  userService = (UserService) ctx.getBean("userService");
	private OrderRecvNotifyService orderRecvNotifyService = (OrderRecvNotifyService) ctx.getBean("orderRecvNotifyService");
	private ProvinceService provinceService = (ProvinceService) ctx.getBean("provinceService");
	private CityService cityService = (CityService) ctx.getBean("cityService");
	
	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertOrderRecvNotify() {
	//	fail("Not yet implemented");
		Order order = orderService.findByOrderNumber("14111514310002");
		OrderRecvNotify orderRecvNotify = new OrderRecvNotify(order);
		//orderRecvNotifyService.insertOrderRecvNotify(orderRecvNotify);
	}

	@Test
	public void testFindByOrder() {
		Order order = orderService.findById(1);
		OrderRecvNotify orderRecvNotify = orderRecvNotifyService.findByOrder(order);
		System.out.println(orderRecvNotify.getRecvDetailAddr());
	}
	
	@Test
	public void testUpdateOrderRecvNotify() {
		
	/*	Order order = orderService.findByOrderNumber(1111111111);
		OrderRecvNotify orderRecvNotify = orderRecvNotifyService.findByOrder(order);
		City city = cityService.findById(230300);
		Province province = city.getProvince();
		
		orderRecvNotify.setRecvProvince(province);
		orderRecvNotify.setRecvCity(city);
		
		orderRecvNotifyService.updateOrderRecvNotify(orderRecvNotify); */
	}

}
