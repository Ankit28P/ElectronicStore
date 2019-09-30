package com.harrisburg.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.entity.Product;


@WebServlet("/remove_from_cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RemoveFromCartServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer ProductId = Integer.parseInt(request.getParameter("Product_id"));
		
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = (ShoppingCart) cartObject;
		
		shoppingCart.removeItem(new Product(ProductId));
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
