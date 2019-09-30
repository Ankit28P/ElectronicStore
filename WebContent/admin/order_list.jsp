<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Orders - Productstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Product Order Management</h2>
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message"><i>${message}</i></h4>
	</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered by</th>
				<th>Product Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrders}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.ProductCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					<td>
						<a href="view_order?id=${order.orderId}">Details</a> &nbsp;
						<a href="edit_order?id=${order.orderId}">Edit</a> &nbsp;
						<a href="javascript:confirmDelete(${order.orderId})">Delete</a>
					</td>
				<tr/>
			</c:forEach>
		</table>
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