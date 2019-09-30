<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Order history </title>
	<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">My Order History</h2>
	</div>
	
	<c:if test="${fn:length(listOrders) == 0}">
		<h2 align="center">You have not placed any orders.</h2>
	</c:if>
	<c:if test="${fn:length(listOrders) > 0}">
		<div align="center">
			<table border="1" cellpadding="5">
				<tr>
					<th>Index</th>
					<th>Order ID</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Status</th>
					<th>Order Date</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="order" items="${listOrders}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${order.orderId}</td>
						<td>${order.ProductCopies}</td>
						<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
						<td>${order.status}</td>
						<td>${order.orderDate}</td>
						<td>
							<a href="show_order?id=${order.orderId}">View Details</a> &nbsp;
						</td>
					<tr/>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>