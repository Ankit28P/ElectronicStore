package com.harrisburg.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.dao.CategoryDAO;
import com.harrisburg.dao.ProductDAO;
import com.harrisburg.entity.Category;

public class CategoryServices {

	private CategoryDAO categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		categoryDao = new CategoryDAO();
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDao.listAll();

		request.setAttribute("listCategory", listCategory);
		if(message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");

		Category existedCategory = categoryDao.findByName(name);
		if (existedCategory != null) {
			String message = "Could not created. A Category with name " + name + " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDao.create(newCategory);
			String message = "New category created succesfully";
			listCategory(message);
		}

	}

	public void editCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		
		Category category = categoryDao.get(categoryId);
		
		String editPage = "category_form.jsp";
		request.setAttribute("category", category);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
		
	}

	public void updateCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));	
		String name = request.getParameter("name");
		
		Category categoryById = categoryDao.get(categoryId);
		
		Category categoryByName = categoryDao.findByName(name);
		
		if(categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			String message ="Could not update the category. A category with " + name+" already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			categoryById.setName(name);
			categoryDao.update(categoryById);
			
			String message = "Category has been updated successfully!";
			listCategory(message);
			
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		String message;
		
		if (categoryId == 1000) {
				message = "The default admin Category cannot be deleted.";
				
				request.setAttribute("message", message);
				request.getRequestDispatcher("message.jsp").forward(request, response);
				return;
		}
		ProductDAO productDao = new ProductDAO();
		long numberOfproducts = productDao.countByCategory(categoryId);
		
		if(numberOfproducts > 0) {
			message = "Could not delete the category (ID: %d) becouse it currently contains some products.";
			message = String.format(message, numberOfproducts);
		}else {
			categoryDao.delete(categoryId);
			message = "Category has been deleted succesfully!";
		}
		
		listCategory(message);
	}



}
