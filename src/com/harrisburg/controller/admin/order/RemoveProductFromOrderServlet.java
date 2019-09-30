package com.harrisburg.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.harrisburg.entity.OrderDetail;
import com.harrisburg.entity.ProductOrder;


@WebServlet("/admin/remove_Product_from_order")
public class RemoveProductFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RemoveProductFromOrderServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer ProductId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		ProductOrder order = (ProductOrder) session.getAttribute("order");
		
		Set<OrderDetail> oDetails = order.getOrderDetails();
		Iterator<OrderDetail> iterator = oDetails.iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getProduct().getProductId() == ProductId) {
				float newTotal = order.getTotal() - orderDetail.getSubtotal();
				order.setTotal(newTotal);
				iterator.remove();
			}
		}
		String editOrderFormPage = "edit_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editOrderFormPage);
		dispatcher.forward(request, response);
	}

}
