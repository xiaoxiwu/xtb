package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Goods;
import com.xt8.model.GoodsImage;
import com.xt8.model.Image;
import com.xt8.service.GoodsImageService;
import com.xt8.service.GoodsService;
import com.xt8.service.ImageService;

public class TestGoodsImageService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private GoodsImageService goodsImageService = (GoodsImageService) ctx.getBean("goodsImageService");
	private ImageService imageService = (ImageService) ctx.getBean("imageService");
	private GoodsService goodsService = (GoodsService) ctx.getBean("goodsService");
	
	@Test
	public void testInsertImage() {
		//fail("Not yet implemented");
		Image image = imageService.findById(1);
		Goods goods = goodsService.findById(1);
		
		GoodsImage goodsImage = new GoodsImage(goods,image);
		goodsImageService.insert(goodsImage);
	}

	@Test
	public void testFindByIdSerializable() {
//		fail("Not yet implemented");
		GoodsImage goodsImage = goodsImageService.findById(1);
		if(null != goodsImage){
			System.out.println(goodsImage.getId());
		}
	}

	@Test
	public void testFindByGoods() {
		//fail("Not yet implemented");
		Goods goods = goodsService.findById(1);
		/*GoodsImage goodsImage = goodsImageService.findByGoods(goods);
		if(null != goodsImage){
			System.out.println(goodsImage.getId());
		} */
	}

	@Test
	public void testFindImageByGoods() {
		fail("Not yet implemented");
	}

}
