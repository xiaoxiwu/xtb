package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.DeliveryLog;
import com.xt8.model.Order;
import com.xt8.service.DeliveryLogService;
import com.xt8.service.OrderService;

public class TestDeliveryLogService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private OrderService orderService = (OrderService) ctx.getBean("orderService");
	private DeliveryLogService deliveryLogService = (DeliveryLogService) ctx.getBean("deliveryLogService");
	@Test
	public void testInsertDeliveryLog() {
		//fail("Not yet implemented");
	//	Order order = orderService.findById(1);
	//	DeliveryLog deLog =  new DeliveryLog(order,"湖南省长沙市开福区");
		//deliveryLogService.insertDeliveryLog(deLog);
	}

	@Test
	public void testFindByIdSerializable() {
		//fail("Not yet implemented");
		DeliveryLog deLog = deliveryLogService.findById(1);
		System.out.println(deLog.getDetail());
	}

}
