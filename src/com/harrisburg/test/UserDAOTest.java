package com.harrisburg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.harrisburg.dao.UserDAO;
import com.harrisburg.entity.Users;

public class UserDAOTest{
	
	
	private static UserDAO userDao;
	private Users user;
	
	@BeforeClass
	public static void setUpClass() {
		userDao = new UserDAO();
		
	}

	@Test
	public void testCreateUser() {
		createUser();
		user = userDao.create(user);
		user = new Users();
		user.setUserId(1);
		user.setEmail("ankit.patel@harrisburgu.edu");
		user.setFullName("Ankit");
		user.setPassword("patel");
		
		user = userDao.create(user);
		
		assertTrue(user.getUserId()>0);
	}
	
	@Test
	@Ignore
	public void testUpadateUser() {
		user = new Users();
		user.setUserId(1);
		user.setEmail("ankit@test.com");
		user.setFullName("Ankit");
		user.setPassword("patel");
		
		user = userDao.update(user);
		String expected = "testUpdateUser";
		String actual =user.getPassword();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getUser() {
		Integer userId = 1;
		Users user = userDao.get(userId);
		
		assertNotNull(user);
	}
	
	@Test
	public void getUserNotFound() {
		Integer userId = 99;
		Users user = userDao.get(userId);
		
		assertNull(user);
	}
	
	@Test
	@Ignore
	public void testDeleteUser() {
		Integer userId = 10;
		userDao.delete(userId);
		
		Users user = userDao.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testGetAllListUser() {
		List<Users> listUsers = userDao.listAll();
		
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCountWithNamedQuery() {
		Long count = userDao.count();
		
		assertTrue(count == 4);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "ankit@ankit.com";
		Users user = userDao.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "test2@test.com";
		String password = "testUpdateUser";
		
		boolean loginResult = userDao.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@AfterClass
	public static void tearDownClass() {
		userDao.close();
	}
	
	private void createUser() {
		
		user = new Users();
		user.setEmail("ankit@newtest.com");
		user.setFullName("patel");
		user.setPassword("12345");
	}


}
