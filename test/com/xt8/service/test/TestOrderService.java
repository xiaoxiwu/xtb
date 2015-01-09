package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Goods;
import com.xt8.model.Order;
import com.xt8.model.User;
import com.xt8.service.GoodsService;
import com.xt8.service.OrderService;
import com.xt8.service.UserService;
import com.xt8.util.GenerateOrderNumber;

public class TestOrderService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
	private OrderService orderService = (OrderService) ctx.getBean("orderService");
	private UserService  userService = (UserService) ctx.getBean("userService");
	
	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertOrder() {
		//fail("Not yet implemented");
		
		Goods goods = goodsService.findById(1);
		User user = userService.findById(1);
		
		String orderNumber = GenerateOrderNumber.getOrderNumber();  //产生订单编号       
		Order order = new Order(orderNumber,goods,user);
		order.setOrderTime(new Date());
		order.setDescription("so lonely");
		orderService.insert(order);
	}

	@Test
	public void testFindByOrderNumber() {
		
	//	Order order = orderService.findByOrderNumber(1111111111);
		
		//System.out.println(order.getDescription());
	}

}
