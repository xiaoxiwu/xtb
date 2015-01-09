package com.xt8.service.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Image;
import com.xt8.model.User;
import com.xt8.service.ImageService;
import com.xt8.service.UserService;

public class TestImageService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private ImageService imageService = (ImageService) ctx.getBean("imageService");
	private UserService userService = (UserService) ctx.getBean("userService");
	
	@Test
	public void testFindByIdSerializable() {
		//fail("Not yet implemented");
		Image image = imageService.findById(1);
		if(null!= image)
		{
			System.out.println(image.getImageName());
		}
	}

	@Test
	public void testInsertImage() {
	//	fail("Not yet implemented");
		User user = userService.findById(10);
		Image image  = new Image(user,"hello","http://121.1.1.10/hello.jpg");
		image.setUpTime(new Date());
		
		imageService.insert(image);
	}

}
