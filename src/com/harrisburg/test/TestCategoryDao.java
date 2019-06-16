package com.harrisburg.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.harrisburg.dao.CategoryDAO;
import com.harrisburg.entity.Category;

public class TestCategoryDao {
	private static CategoryDAO categoryDao;
	private Category category;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Entertainment");
		category = categoryDao.create(newCat);

		assertTrue(category != null && category.getCategoryId() > 0);

	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Gaming");
		cat.setCategoryId(1);

		category = categoryDao.update(cat);
		assertEquals(cat.getName(), category.getName());
	}

	@Test
	public void testGet() {
		Integer categoryId = 1;
		category = categoryDao.get(categoryId);

		assertNotNull(category);
		assertEquals("CoreJava", category.getName());
	}

	@Test
	public void testDeleteCategory() {
		Integer categoryid = 4;
		categoryDao.delete(categoryid);

		category = categoryDao.get(categoryid);
		assertNull(category);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();

		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		Long count = categoryDao.count();

		assertTrue(count == 2);
	}

	@Test
	public void testFindByName() {
		String name = "Gaming";
		Category category = categoryDao.findByName(name);
		assertNotNull(category);
	}

}
