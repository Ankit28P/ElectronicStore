package com.harrisburg.controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.services.ProductServices;

@WebServlet("/admin/new_product")
public class NewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewProductServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ProductServices productServices = new ProductServices(request, response);
		productServices.showproductNewForm();
	}

}
