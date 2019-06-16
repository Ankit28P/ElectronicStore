package com.harrisburg.controller.admin.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.services.CategoryServices;


@WebServlet("/admin/update_category")
public class updateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public updateCategoryServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CategoryServices categoryService = new CategoryServices(request, response);
		categoryService.updateCategory();
	}

}
