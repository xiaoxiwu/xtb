package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Goods;
import com.xt8.model.GoodsUserLike;
import com.xt8.model.User;
import com.xt8.service.GoodsService;
import com.xt8.service.GoodsUserLikeService;
import com.xt8.service.UserService;

public class TestGoodsUserLike {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private UserService userv = (UserService) ctx.getBean("userService");
	private GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
	private GoodsUserLikeService goodsUserLikeService = (GoodsUserLikeService) ctx.getBean("goodsUserLikeService");
	
	@Test
	public void testInsertGoodsUserLike() {
	//	fail("Not yet implemented");
		Goods goods = goodsService.findById(1);
		User user = userv.findById(10);
		
		GoodsUserLike goodsUserLike = new GoodsUserLike(goods,user);
		goodsUserLike.setTime(new Date());
		
		goodsUserLikeService.insertGoodsUserLike(goodsUserLike);
	}

	@Test
	public void testFindByIdSerializable() {
	//	fail("Not yet implemented");
		GoodsUserLike goodsUserLike = goodsUserLikeService.findById(1);
		
		if(null != goodsUserLike)
		{
			System.out.println(goodsUserLike.getTime());
		}
	}

	@Test
	public void testFindByGoods() {
		//fail("Not yet implemented");
		
		Goods goods = goodsService.findById(1);
		List<GoodsUserLike> list = goodsUserLikeService.findByGoods(goods);
		
		System.out.println("the num of like people is "+list.size());
	}

	@Test
	public void testFindByUser() {
	//	fail("Not yet implemented");
		User user = userv.findById(10);
		List<GoodsUserLike> list = goodsUserLikeService.findByUser(user);
		System.out.println("the num of like people is "+list.size());
	}

	@Test
	public void testFindByUserAndGoods() {
	//	fail("Not yet implemented");
		
		Goods goods = goodsService.findById(1);
		User user = userv.findById(10);
		
		GoodsUserLike goodsUserLike = goodsUserLikeService.findByUserAndGoods(user, goods);
		
		System.out.println(goodsUserLike.getTime());
	}

	@Test
	public void testDeleteGoodsUserLike() {
		fail("Not yet implemented");
	}

}
