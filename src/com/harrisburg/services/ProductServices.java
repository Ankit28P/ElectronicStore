package com.harrisburg.services;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.harrisburg.dao.CategoryDAO;
import com.harrisburg.dao.ProductDAO;
import com.harrisburg.entity.Category;
import com.harrisburg.entity.Product;

public class ProductServices {

	private ProductDAO productDao;
	private CategoryDAO categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ProductServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;

		productDao = new ProductDAO();
		categoryDao = new CategoryDAO();
	}

	public void listproducts() throws ServletException, IOException {
		listproducts(null);
	}

	public void listproducts(String message) throws ServletException, IOException {
		List<Product> listproducts = productDao.listAll();

		request.setAttribute("listproducts", listproducts);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "product_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void showproductNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDao.listAll();

		request.setAttribute("listCategory", listCategory);

		String newPage = "product_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}

	public void readproductFields(Product product) throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("descreption");
		float price = Float.parseFloat(request.getParameter("price"));

		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			throw new ServletException("Error parsing publish date (Format is mm/dd/yyyy)");
		}

//		System.out.println(categoryId +", title: "+ title +", author: "+ author +", isbn: "+ isbn 
//				+", Description: "+ description +", price: "+ price +", "
//						+ "Publish Date: "+ publishDate);

		product.setTitle(title);
		product.setAuthor(author);
		product.setDescription(description);
		product.setPublishDate(publishDate);

		Category category = categoryDao.get(categoryId);
		product.setCategory(category);
		product.setPrice(price);

		Part part = request.getPart("productImage");
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageByte = new byte[(int) size];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageByte);
			inputStream.close();

			product.setImage(imageByte);
		}
	}

	public void createproduct() throws ServletException, IOException {

		String title = request.getParameter("title");

		Product existproduct = productDao.findByTitle(title);
		if (existproduct != null) {
			String message = "Could not create new Product. Becouse,the product with title " + title
					+ " already exists!";
			listproducts(message);
			return;
		}

		Product newproduct = new Product();
		readproductFields(newproduct);

		Product createdproduct = productDao.create(newproduct);

		if (createdproduct.getProductId() > 0) {
			String message = "A new product has been created successfull!y";
			listproducts(message);
		}
	}

	public void editproduct() throws ServletException, IOException {
		Integer productId = Integer.parseInt(request.getParameter("id"));
		Product product = productDao.get(productId);
		List<Category> listCategory = categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);

		request.setAttribute("product", product);

		String editPage = "product_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateproduct() throws ServletException, IOException {
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		String title = request.getParameter("title");

		Product existedproduct = productDao.get(productId);
		Product productByTitle = productDao.findByTitle(title);

		if (productByTitle != null && !existedproduct.equals(productByTitle)) {
			String message = "Could not update product. becouse, another product having the same title.";
			listproducts(message);
			return;
		}

		readproductFields(existedproduct);

		productDao.update(existedproduct);

		String message = "A product has been updated successfully!";
		listproducts(message);
	}

	public void deleteproduct() throws ServletException, IOException {
		Integer productId = Integer.parseInt(request.getParameter("id"));

		Product product = productDao.get(productId);

		if (product == null) {
			String message = "Could not find product with ID " + productId + ", or it might have been deleted";
			CommonUtitlity.showMessageBackend(message, request, response);
			productDao.delete(productId);
			listproducts(message);
		}
	}

	public void viewproductsByCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("id"));

		List<Product> listByCategory = productDao.listByCategory(categoryId);
		Category category = categoryDao.get(categoryId);

		request.setAttribute("category", category);
		request.setAttribute("listproducts", listByCategory);
		String listPage = "frontend/product_list_by_category.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void viewproductDetails() throws ServletException, IOException {
		Integer productId = Integer.parseInt(request.getParameter("id"));
		Product product = productDao.get(productId);

		request.setAttribute("product", product);

		String detailPage = "frontend/product_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Product> result = null;
		if (keyword.equals("")) {
			result = productDao.listAll();
		} else {
			result = productDao.search(keyword);
		}

		request.setAttribute("result", result);
		request.setAttribute("keyword", keyword);

		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
	}

}
