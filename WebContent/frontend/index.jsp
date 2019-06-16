<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Store - Online Electronic Shopping</title>
<link rel="stylesheet" href="../productStroreWebsite/css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<div>
			<h2>New Products: </h2>
			<c:forEach items="${listNewproduct}" var="product">
				<jsp:directive.include file="product_group.jsp" />
			</c:forEach>
		</div>
		<div class="next_row">
			<h2>Best-selling Products</h2>
			<c:forEach items="${listBestSellingproduct}" var="product">
				<jsp:directive.include file="product_group.jsp" />
			</c:forEach>
		</div>
		<div class="next_row">
			<h2>Most-Favorite Products</h2>
			<c:forEach items="${topMostFavorateproduct}" var="product">
				<jsp:directive.include file="product_group.jsp" />
			</c:forEach>
		</div>
		<br/><br/>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>