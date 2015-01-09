package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.EvaluateIndex;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.GoodsService;

public class TestEvaluateIndex {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private EvaluateIndexService evaluateIndexService = (EvaluateIndexService) ctx.getBean("evaluateIndexService");
	
	@Test
	public void testFindByIdSerializable() {
		//fail("Not yet implemented");
		EvaluateIndex evaluateIndex = evaluateIndexService.findById(1);
		System.out.println(evaluateIndex.geteName());
	}

	@Test
	public void testInsertEvaluateIndex() {
	//	fail("Not yet implemented");
		
		EvaluateIndex evaluateIndex =null;
		evaluateIndex = new EvaluateIndex("魅力指数");
		evaluateIndexService.save(evaluateIndex);
		
		evaluateIndex = new EvaluateIndex("影响力指数");
		evaluateIndexService.save(evaluateIndex);
		
		evaluateIndex = new EvaluateIndex("好评指数");
		evaluateIndexService.save(evaluateIndex);
		
		evaluateIndex = new EvaluateIndex("综合指数");
		evaluateIndexService.save(evaluateIndex);
	}

}
