<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Order Details - Productstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Details of Order Id: ${order.orderId}</h2>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message"><i>${message}</i></h4>
	</div>
	</c:if>
	
	<div align="center">
		<h2>Order Overview</h2>
		<table cellpadding="5">
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Product Copies: </b></td>
				<td>${order.ProductCopies}</td>
			</tr>
			<tr>
				<td><b>Product Copies: </b></td>
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
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Order Products</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Product Title</th>
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
	<div align="center">
		<br/>
		<a href="edit_order?id=${order.orderId}">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Delete this Order</a>
	</div>

	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(orderId){
			if(confirm('Are you sure you want to delete the Review with Id '+ orderId +'?')){
				window.location = 'delete_order?id='+orderId;
			}
		}
	</script>
	
</body>
</html>