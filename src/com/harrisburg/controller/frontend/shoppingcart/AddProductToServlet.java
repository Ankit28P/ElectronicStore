package com.harrisburg.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harrisburg.dao.ProductDAO;
import com.harrisburg.entity.Product;

@WebServlet("/add_to_cart")
public class AddProductToServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddProductToServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer ProductId = Integer.parseInt(request.getParameter("Product_id"));
		
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = null;
		if(cartObject != null && cartObject instanceof ShoppingCart) {
			shoppingCart = (ShoppingCart) cartObject;
		} else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		} 
		
		ProductDAO ProductDao = new ProductDAO();
		Product Product = ProductDao.get(ProductId);
		
		shoppingCart.addItem(Product);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}


}
