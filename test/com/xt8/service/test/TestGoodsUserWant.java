package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.Goods;
import com.xt8.model.GoodsUserWant;
import com.xt8.model.User;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.GoodsService;
import com.xt8.service.GoodsUserWantService;
import com.xt8.service.UserService;
import com.xt8.util.Constants;

public class TestGoodsUserWant {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userv = (UserService) ctx.getBean("userService");
	private GoodsService goodsService = (GoodsService) ctx
			.getBean("goodsService");
	private GoodsUserWantService goodsUserWantService = (GoodsUserWantService) ctx
			.getBean("goodsUserWantService");
	private EvaluateIndexService evaluateIndexService = (EvaluateIndexService) ctx
			.getBean("evaluateIndexService");

	@Test
	public void testInsertGoodsUserWant() {
		// fail("Not yet implemented");
		Goods goods = goodsService.findById(1);
		User user = userv.findById(10);

		GoodsUserWant goodsUserWant = new GoodsUserWant(goods, user);
		goodsUserWant.setTime(new Date());

		goodsUserWantService.insertGoodsUserWant(goodsUserWant);
	}

	@Test
	public void testFindByIdSerializable() {
		// fail("Not yet implemented");
		GoodsUserWant goodsUserWant = goodsUserWantService.findById(1);

		if (null != goodsUserWant) {
			System.out.println(goodsUserWant.getTime());
		}
	}

	@Test
	public void testFindByGoods() {
		// fail("Not yet implemented");

		Goods goods = goodsService.findById(1);
		List<GoodsUserWant> list = goodsUserWantService.findByGoods(goods);

		System.out.println("the num of Want people is " + list.size());
	}

	@Test
	public void testFindByUser() {
		// fail("Not yet implemented");
		User user = userv.findById(10);
		List<GoodsUserWant> list = goodsUserWantService.findByUser(user);
		System.out.println("the num of Want people is " + list.size());
	}

	@Test
	public void testFindByUserAndGoods() {
		// fail("Not yet implemented");

		Goods goods = goodsService.findById(1);
		User user = userv.findById(10);

		GoodsUserWant goodsUserWant = goodsUserWantService.findByUserAndGoods(
				user, goods);

		System.out.println(goodsUserWant.getTime());
	}

	@Test
	public void testDeleteGoodsUserWant() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindWantedUserByGoods() {
		EvaluateIndex eIndex = evaluateIndexService.findById(Constants.EINDEX_COMPREHENSIVE_ID);
		Goods goods = goodsService.findById(3);
		List list = goodsUserWantService.findWantedUserByGoodsBySort(goods,eIndex);
		System.out.println(list.size());
	}

}
