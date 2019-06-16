package com.harrisburg.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.harrisburg.dao.CustomerDAO;
import com.harrisburg.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDao = new CustomerDAO();
	}

	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}

	public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDao.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listCustomer", listCustomer);

		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void readData(Customer customer) {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");

		if (email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		if (password != null && !password.equals("")) {
			customer.setPassword(password);
		}

		customer.setFullname(fullname);

		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setZipcode(zipcode);

	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");

		Customer existCustomer = customerDao.findByEmail(email);

		if (existCustomer != null) {
			String message = "Could not create customer. The email: " + email
					+ " already register by another customer!";
			listCustomers(message);
		} else {
			Customer newCustomer = new Customer();
			readData(newCustomer);
			customerDao.create(newCustomer);
			String message = "Customer has been created successfully!";
			listCustomers(message);
		}
	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDao.get(customerId);

		request.setAttribute("customer", customer);

		String editPage = "customer_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");

		Customer existCustomer = new Customer();
		Customer customeByEmail = customerDao.findByEmail(email);

		if (customeByEmail != null && !existCustomer.equals(customeByEmail)) {
			String message = "Could not update Customer. becouse, another customer having the same Email.";
			listCustomers(message);
		} else {
			readData(existCustomer);
			existCustomer.setRegisterDate(new Date());
			customerDao.update(existCustomer);
			String message = "A product has been updated successfully!";
			listCustomers(message);
		}
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		customerDao.delete(customerId);
		String message = "The customer has been deleted successfully.";
		listCustomers(message);
	CommonUtitlity.showMessageBackend(message,request,response);
	}

	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		
		String message ="";
		Customer existCustomer = customerDao.findByEmail(email);
		
		if(existCustomer != null) {
			message = "Could not register. The email: "+email+
					" already register by another customer!";
		}else {
			Customer newCustomer = new Customer();
			readData(newCustomer);
			customerDao.create(newCustomer);
			message = "You has been register successfully. Thank you!.<br/>"+
			"<a href='login'>Click here</a> to login.";
		}
		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Customer checkCustomer = customerDao.checkLogin(email, password);

		if (checkCustomer == null) {
			String message = "Login failed!, Please check youe email or password";
			request.setAttribute("message", message);
			showLogin();
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", checkCustomer);
			Object redirectPath = session.getAttribute("redirectURL");
			if (redirectPath != null) {
				String redirectURL = (String) redirectPath;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showCustomerProfile();
			}
		}
	}

	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");

		readData(customer);
		customerDao.update(customer);
		showCustomerProfile();
	}
}
