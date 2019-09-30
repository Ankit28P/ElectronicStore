package com.harrisburg.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.harrisburg.dao.ProductDAO;
import com.harrisburg.entity.OrderDetail;
import com.harrisburg.entity.Product;
import com.harrisburg.entity.ProductOrder;


@WebServlet("/admin/add_Product_to_order")
public class AddProductToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddProductToOrderServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int ProductId = Integer.parseInt(request.getParameter("ProductId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		ProductDAO ProductDao = new ProductDAO();
		Product Product = ProductDao.get(ProductId);
		
		HttpSession session = request.getSession();
		ProductOrder order = (ProductOrder) session.getAttribute("order");
		
		float subTotal = quantity * Product.getPrice();
		
		OrderDetail oDetail = new OrderDetail();
		oDetail.setProduct(Product);
		oDetail.setQuantity(quantity);
		oDetail.setSubtotal(subTotal);
		
		float newTotal = order.getTotal() + subTotal;
		order.setTotal(newTotal);
		
		order.getOrderDetails().add(oDetail);
		
		request.setAttribute("Product", Product);
		session.setAttribute("NewProductPendingToAddOrder", true);
		
		String resultPage = "add_Product_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
