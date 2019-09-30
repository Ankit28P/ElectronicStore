package com.harrisburg.controller.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.dao.ProductDAO;
import com.harrisburg.entity.Product;


@WebServlet("/admin/add_Product_form")
public class ShowAddProductFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowAddProductFormServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ProductDAO ProductDao = new ProductDAO();
		List<Product> listProducts = ProductDao.listAll();
		request.setAttribute("listProducts", listProducts);
		
		String addPage = "add_Product_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(addPage);
		dispatcher.forward(request, response);
	}

}
