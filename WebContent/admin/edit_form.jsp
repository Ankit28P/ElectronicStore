<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit Order - Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" />
	<script 
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	</script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Edit Order Id: ${order.orderId}</h2>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message"><i>${message}</i></h4>
	</div>
	</c:if>
	<form action="update_order" method="post" id="orderForm">
	<div align="center">
		<h2>Order Overview</h2>
		<table cellpadding="5">
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td><input name="recipientName" value="${order.recipientName}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td><input name="recipientPhone" value="${order.recipientPhone}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Ship To: </b></td>
				<td><input name="address" value="${order.shippingAddress}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>
					<select name="paymentMethod">
						<option value="CashOnDelivery">Cash On Delivery</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><b>Order Status: </b></td>
				<td>
					<select name="status">
							<option value="processing" <c:if test="${order.status eq 'processing'}"></c:if>>Processing</option>
							<option value="shipping" <c:if test="${order.status eq 'shipping'}"></c:if>>Shipping</option>
							<option value="delivered" <c:if test="${order.status eq 'delivered'}"></c:if>>Delivered</option>
							<option value="completed" <c:if test="${order.status eq 'completed'}"></c:if>>Completed</option>
							<option value="cancel" <c:if test="${order.status eq 'cancel'}"></c:if>>Cancel</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Order Books</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>SubTotal</th>
				<th></th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
						${orderDetail.book.title}
					</td>
					<td>${orderDetail.book.author}</td>
					<td>
						<input type="hidden" name="price" value="${orderDetail.book.price}" />
						<fmt:formatNumber value="${orderDetail.book.price}" type="currency" />
					</td>
					<td>
						<input type="hidden" name="bookId" value="${orderDetail.book.bookId}" />
						<input type="text" name="quantity${status.index + 1}" value="${orderDetail.quantity}" size="5" />
					</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
					<td><a href="remove_book_from_order?id=${orderDetail.book.bookId}">Remove</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL: </i></b>
				</td>
				<td>
				<b>${order.bookCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
				<td></td>
			</tr>
		</table>
	</div>
	<div align="center">
		<br/>
		<a href="javascript:showAddBookPopup()"><b>Add Books</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Save">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Cancel" onclick="javascript:window.location.href='list_orders';">
	</div>
	</form>
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		function showAddBookPopup(){
			var width = 600;
			var height = 250;
			var top = (screen.height - height)/2;
			var left = (screen.width - width)/2;
			window.open('add_book_form', '_blank', 'width='+ width + ', height='+ height + ', top='+ top + ', left='+ left);
		}
		
		$(document).ready(function(){
			
			$("#orderForm").validate({
				rules:{
					recipientName: "required",
					recipientPhone: "required",
					address: "required",
					paymentMethod: "required",
					<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
						quantity${status.index + 1}: {
							required: true, 
							number: true, 
							min: 1
						},
					</c:forEach>
				},
				messages: {
					recipientName: "please enter recipient name!",
					recipientPhone: "please enter recipient phone!",
					address: "please enter recipient address!",
					paymentMethod: "please select payment method!",
					<c:forEach items="${order.orderDetails}" var="item" varStatus="status">
						quantity${status.index + 1}: {
							required: "Please enter quantities!",
							number: "Quantity must be a number!",
							min: "Quantity must be 1 or more!"
						},
					</c:forEach>
				}
			});
		});
		
	</script>
	
</body>
</html>