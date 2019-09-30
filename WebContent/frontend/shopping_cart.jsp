<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shopping Cart</title>
	<link rel="stylesheet" href="../ProductStroreWebsite/css/style.css" />
	<script type="text/javascript" src="../ProductStroreWebsite/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../ProductStroreWebsite/js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>Your Shopping Cart Details.</h2>
		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message"><i>${message}</i></h4>
			</div>
		</c:if>
		
		<c:set var="cart" value="${sessionScope['cart']}" />
		<c:if test="${cart.totalItems == 0}">
			<h2>There is no item in the cart</h2>
		</c:if>
		<c:if test="${cart.totalItems > 0}">
				<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1" cellpadding="5">
						<tr>
							<th>No</th>
							<th colspan="2">Product</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>SubTotal</th>
							<th>
							</th>
						</tr>
						<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>
									<img class="Product_small" src="data:image/jpg;base64,${item.key.base64Image}" />
								</td>
								<td>
									<span id="Product_title">${item.key.title}</span>
								</td>
								<td>
									<input type="hidden" name="ProductId" value="${item.key.ProductId}" />
									<input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" /> 
								</td>
								<td><fmt:formatNumber value="${item.key.price}" type="currency" /></td>
								<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></td>
								<td><a href="remove_from_cart?Product_id=${item.key.ProductId}">Remove</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>${cart.totalQuantity} Product(s)</td>
							<td>Total:</td>
							<td colspan="2"><b><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></b></td>
						</tr>
					</table>
					</div>
					<div>
						<table class="customer">
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td></td>
								<td><button type="submit">Update</button> </td>
								<td><input type="button" id="cartClear" value="Clear Cart" /> </td>
								<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
								<td><a href="checkout">Check Out</a></td>
							</tr>
						</table>
					</div>
				</form>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#cartClear").click(function(){
		window.location = 'clear_cart';
	})
	
	$("#cartForm").validate({
		rules : {
			<c:forEach items="${cart.items}" var="item" varStatus="status">
				quantity${status.index + 1}: {
					required: true, 
					number: true, 
					min: 1
					},
			</c:forEach>
		},
		messages : {
			<c:forEach items="${cart.items}" var="item" varStatus="status">
				quantity${status.index + 1}: {
					required: "Please enter quantities!",
					number: "Quantity must be a number!",
					min: "Quantity must be 1 or more!"
					},
			</c:forEach>
		}
	});
	
	$("#buttonCancel").click(function(){
		history.go(-1);
	})
});
</script>
</html>