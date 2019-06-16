package com.harrisburg.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.dao.UserDAO;
import com.harrisburg.entity.Users;

public class UserServices {
	private UserDAO userDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		userDao = new UserDAO();
	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUser = userDao.listAll();
		request.setAttribute("listusers", listUser);

		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {

		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");

		Users existedUser = userDao.findByEmail(email);
		if (existedUser != null) {
			String message = "Could not created. A user with email "+ email +" already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher =request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, fullName, password);
			userDao.create(newUser);
			listUser("New User created successfully!");
		}
	}
	
	public void editUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		
		Users user = userDao.get(userId);
		
		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		Users userById = userDao.get(userId);
		
		Users userByEmail = userDao.findByEmail(email);
		
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message ="Could not update the user. A user with " + email+" already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			Users user = new Users(userId, email, fullName, password);
			userDao.update(user);
			
			String message = "User has been updated successfully!";
			listUser(message);
			
			String deletePage = "user_form.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(deletePage);
			requestDispatcher.forward(request, response);
		}
		
	}

	public void deleteUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
			
		String message = "User has been deleted succesfully!";
			
		if (userId == 1) {
				message = "The default admin user account cannot be deleted.";
				
				request.setAttribute("message", message);
				request.getRequestDispatcher("message.jsp").forward(request, response);
				return;
		}
		
		userDao.delete(userId);
		
		
		listUser(message);
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean loginResult = userDao.checkLogin(email, password);
		
		if(loginResult) {
			request.getSession().setAttribute("userEmail", email);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/");
			requestDispatcher.forward(request, response);
			
		}else {
			String message = "Login failed!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
