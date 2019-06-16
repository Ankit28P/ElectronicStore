package com.harrisburg.controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.services.ProductServices;


@WebServlet("/admin/update_product")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10,  //10kb
		maxFileSize = 1024 * 300,		//300kb
		maxRequestSize = 1024 * 1024	//1MB
		)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateProductServlet() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ProductServices productServices = new ProductServices(request, response);
		productServices.updateproduct();
	}

}
