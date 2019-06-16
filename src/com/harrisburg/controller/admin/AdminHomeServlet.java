package com.harrisburg.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.dao.CustomerDAO;
import com.harrisburg.dao.ProductDAO;
import com.harrisburg.dao.UserDAO;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminHomeServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		ProductDAO productDao = new ProductDAO();
		CustomerDAO customerDao = new CustomerDAO();

		long totalUsers = userDao.count();
		long totalproducts = productDao.count();
		long totalCustomer = customerDao.count();

		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalproducts", totalproducts);
		request.setAttribute("totalCustomers", totalCustomer);

		String homePage = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
		dispatcher.forward(request, response);

	}

}
