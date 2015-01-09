package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.ExpressCorp;
import com.xt8.service.ExpressCorpService;

public class TestExpressCorpService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	
	private ExpressCorpService expressCorpService = (ExpressCorpService) ctx.getBean("expressCorpService");
	@Test
	public void testInsertExpressCorp() {
		//fail("Not yet implemented");
		ExpressCorp expressCorp = new ExpressCorp(100,"申通快递");
		expressCorpService.insertExpressCorp(expressCorp);
		expressCorp = new ExpressCorp(101,"顺丰快递");
		expressCorpService.insertExpressCorp(expressCorp);
	}

	@Test
	public void testFindByIdSerializable() {
	//	fail("Not yet implemented");
		ExpressCorp expressCorp = expressCorpService.findById(1);
		System.out.println(expressCorp.getEcName());
	}

}
