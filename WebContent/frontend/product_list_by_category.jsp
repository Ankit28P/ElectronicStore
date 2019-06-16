<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products in ${category.name} - Online product Shopping</title>
<link rel="stylesheet" href="../productStroreWebsite/css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<h2>${category.name}</h2>
	</div>
	<div class="product_group">
		<c:forEach items="${listproducts}" var="product">
			<div class="Dproduct">
				<div>
					<a href="view_product?id=${product.productId}">
						<img class="product_small" src="data:image/jpg;base64,${product.base64Image}" />
					</a>
				</div>
				<div>
					<a href="view_product?id=${product.productId}">
						<b>${product.title}</b>
					</a>
				</div>
				<div>
					<jsp:directive.include file="product_rating.jsp" />
				</div>
				<div><i>by ${product.author}</i></div>
				<div><b>$ ${product.price}</b></div>
			</div>
		</c:forEach>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>