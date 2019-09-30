package com.harrisburg.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.harrisburg.dao.ProductDAO;
import com.harrisburg.dao.ReviewDAO;
import com.harrisburg.entity.Customer;
import com.harrisburg.entity.Product;
import com.harrisburg.entity.Review;

public class ReviewServices {

	private ReviewDAO reviewDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		reviewDao = new ReviewDAO();
	}

	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}

	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDao.listAll();
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listReviews", listReviews);

		String listPage = "review_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDao.get(reviewId);

		request.setAttribute("review", review);

		String editPage = "review_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		Review review = reviewDao.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		reviewDao.update(review);

		String message = "Review has been updated successfully!";
		listAllReview(message);
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDao.get(reviewId);

		if (review == null) {
			String message = "The Review could not Delete with this title " + review.getProduct() + " already Deleted";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("massage.jsp");
			requestDispatcher.forward(request, response);
			return;
		} else {
			reviewDao.delete(reviewId);
			String massage = "Review has been Deleted successfully";
			listAllReview(massage);
		}
	}

	public void showReviewForm() throws ServletException, IOException {
		Integer ProductId = Integer.parseInt(request.getParameter("Product_id"));
		ProductDAO ProductDao = new ProductDAO();
		Product Product = ProductDao.get(ProductId);

		HttpSession session = request.getSession();
		request.getSession().setAttribute("Product", Product);

		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		Review existReview = reviewDao.findByCustomer(customer.getCustomerId(), ProductId);

		String targetPage = "frontend/review_form.jsp";
		if (existReview != null) {
			request.setAttribute("review", existReview);
			targetPage = "frontend/review_info.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);

	}

	public void submitReview() throws ServletException, IOException {
		Integer ProductId = Integer.parseInt(request.getParameter("ProductId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");

		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		newReview.setRating(rating);

		Product Product = new Product();
		Product.setProductId(ProductId);
		newReview.setProduct(Product);

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);

		reviewDao.create(newReview);

		String messagePage = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

}
