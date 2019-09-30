package com.harrisburg.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UpdateCartServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] arrayBookIds = request.getParameterValues("bookId");
		String[] quantities = new String[arrayBookIds.length];
		
		for(int i=1; i<= quantities.length; i++) {
			String aQuantities = request.getParameter("quantity"+i);
			quantities[i - 1] = aQuantities;
		}
		
		int[] bookIds = Arrays.stream(arrayBookIds).mapToInt(Integer :: parseInt).toArray();
		int[] quantity = Arrays.stream(quantities).mapToInt(Integer :: parseInt).toArray();
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		cart.updateCart(bookIds, quantity);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
		
	}

}
