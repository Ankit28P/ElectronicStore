<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Order Details</title>
	<link rel="stylesheet" href="../ProductStroreWebsite/css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<c:if test="${order == null}">
		<h2 align="center" class="pageheading">Sorry, you are not authorized to view this order.</h2>
	</c:if>
	<c:if test="${order != null}">
	<div align="center">
		<h2 class="pageheading">Your Order Id: ${order.orderId}</h2>
	</div>
	<div align="center">
		<table cellpadding="5">
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Quantity: </b></td>
				<td>${order.ProductCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td>${order.total}</td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Ship To: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Ordered Products</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>No</th>
				<th>Product</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>SubTotal</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
						<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.Product.base64Image}" width="48" height="64">
						${orderDetail.Product.title}
					</td>
					<td>${orderDetail.Product.author}</td>
					<td><fmt:formatNumber value="${orderDetail.Product.price}" type="currency" /></td>
					<td>${orderDetail.quantity}</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL: </i></b>
				</td>
				<td>
				<b>${order.ProductCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
			</tr>
		</table>
	</div>
	</c:if>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>