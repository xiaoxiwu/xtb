package com.xt8.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xt8.model.Category;
import com.xt8.service.CategoryService;

public class TestCategoryService {
	
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private CategoryService categoryService = (CategoryService) ctx.getBean("categoryService");

	@Test
	public void testFindByIdSerializable() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByCategoryId() {
		
		Category cate = categoryService.findByCategoryId(100001);
		System.out.println(cate.getDescription());
	}

	@Test
	public void testGetRootCategory() {
		Category root = categoryService.getRootCategory();
		System.out.println(root.getCategoryName());
	}
	
	@Test
    public void testInsertCategory(){
		
		Category root = categoryService.getRootCategory();
		Category cate = null; 
		Category cate1 = null;
		
		cate = categoryService.insertCategory(100000, "书籍", root, "书籍类别");
		cate1 = categoryService.insertCategory(100001, "计算机", cate, "计算机书籍");
		cate1 = categoryService.insertCategory(100002, "经济", cate, "经济书籍");
		
		cate = categoryService.insertCategory(200000, "衣帽服饰", root, "衣服类别");
		cate1 = categoryService.insertCategory(200001, "上衣", cate, "上衣");
		
		cate = categoryService.insertCategory(300000, "生活用品", root, "生活用品");
		cate1 = categoryService.insertCategory(300001, "桌椅", cate, "桌子");
	}
	
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteQueryByPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteUpdate() {
		fail("Not yet implemented");
	}

}
