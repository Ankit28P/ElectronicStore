package com.harrisburg.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.harrisburg.controller.frontend.shoppingcart.ShoppingCart;
import com.harrisburg.dao.OrderDAO;
import com.harrisburg.entity.Customer;
import com.harrisburg.entity.OrderDetail;
import com.harrisburg.entity.Product;
import com.harrisburg.entity.ProductOrder;

public class OrderServices {
	private OrderDAO orderDao;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		orderDao = new OrderDAO();
	}

	private HttpServletRequest request;
	private HttpServletResponse response;

	public void listAllOrders() throws ServletException, IOException {
		listAllOrders(null);
	}

	public void listAllOrders(String message) throws ServletException, IOException {
		List<ProductOrder> listOrders = orderDao.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listOrders", listOrders);
		String listPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void viewDetailForAdmin() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));

		ProductOrder order = orderDao.get(orderId);
		if (order != null) {
			request.setAttribute("order", order);
			CommonUtitlity.forwardToPage("order_details.jsp", request, response);
		} else {
			String message = "Could not find order with ID " + orderId;
			CommonUtitlity.showMessageBackend(message, request, response);
		}
//		request.setAttribute("order", order);
//		
//		String orderDetail = "order_details.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(orderDetail);
//		dispatcher.forward(request, response);
	}

	public void checkOutForm() throws ServletException, IOException {
		String orderDetail = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(orderDetail);
		dispatcher.forward(request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");

		ProductOrder order = new ProductOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);

		String shippingAddress = address + ", " + city + ", " + zipCode + ", " + country;
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		ShoppingCart sCart = (ShoppingCart) session.getAttribute("cart");
		Map<Product, Integer> items = sCart.getItems();

		Iterator<Product> iterator = items.keySet().iterator();

		Set<OrderDetail> orderDetails = new HashSet<>();

		while (iterator.hasNext()) {
			Product Product = iterator.next();
			Integer quantity = items.get(Product);
			float subTotal = quantity * Product.getPrice();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(Product);
			orderDetail.setProductOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subTotal);

			orderDetails.add(orderDetail);
		}

		order.setCustomer(customer);
		order.setOrderDetails(orderDetails);
		order.setTotal(sCart.getTotalAmount());

		orderDao.create(order);

		String message = "Thank you, your order has been received. Your Product will deliver in 4 to 5 bussiness days.";
		request.setAttribute("message", message);
		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

	public void listOrderByCustomere() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		List<ProductOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId());

		request.setAttribute("listOrders", listOrders);

		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		ProductOrder order = orderDao.get(orderId, customer.getCustomerId());
		request.setAttribute("order", order);
		String detailPage = "frontend/order_details.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);

	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Object isPandingProduct = session.getAttribute("NewProductPendingToAddOrder");

		if (isPandingProduct == null) {
			ProductOrder order = orderDao.get(orderId);
			if (order == null) {
				String message = "Could not find order with ID " + orderId;
				CommonUtitlity.showMessageBackend(message, request, response);
				return;
			}
			session.setAttribute("order", order);
		} else {
			session.removeAttribute("NewProductPendingToAddOrder");
		}
		String editPage = "edit_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);

	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductOrder order = (ProductOrder) session.getAttribute("order");

		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String shipingAddress = request.getParameter("address");
		String paymentMethod = request.getParameter("paymentMethod");
		String orderStatus = request.getParameter("status");

		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shipingAddress);
		order.setPaymentMethod(paymentMethod);
		order.setStatus(orderStatus);

		String[] arrayProductId = request.getParameterValues("ProductId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayProductId.length];

		for (int i = 1; i <= arrayQuantity.length; i++) {
			arrayQuantity[i - 1] = request.getParameter("quantity" + i);
		}

		Set<OrderDetail> oDetails = order.getOrderDetails();
		oDetails.clear();

		float totalAmount = 0.0f;

		for (int i = 0; i < arrayProductId.length; i++) {
			int ProductId = Integer.parseInt(arrayProductId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);

			float subTotal = price * quantity;

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(new Product(ProductId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subTotal);
			orderDetail.setProductOrder(order);

			oDetails.add(orderDetail);

			totalAmount += subTotal;
		}
		order.setTotal(totalAmount);

		orderDao.update(order);

		String message = "The order with orderId: " + order.getOrderId() + " has been update succesfully.";

		listAllOrders(message);
	}

	public void deleteOrder() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		ProductOrder order = orderDao.get(orderId);

		if (order != null) {
			orderDao.delete(orderId);

			String message = "The order ID " + orderId + " has been deleted.";
			listAllOrders(message);
		} else {
			String message = "Could not find order with ID " + orderId
					+ ", or it might have been deleted by another admin.";
			CommonUtitlity.showMessageBackend(message, request, response);
		}
	}
}
