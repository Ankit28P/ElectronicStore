<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result for ${keyword} - Online product Shopping</title>
<link rel="stylesheet" href="../productStroreWebsite/css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<c:if test="${fn: length(result) == 0}">
			<h2>No result found for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn: length(result) > 0}">
			<div class="product_group">
				<center> <h2>Result for "${keyword}": </h2> </center>
				<c:forEach items="${result}" var="product">
					<div>
						<div id="search_image">
							<div>
								<a href="view_product?id=${product.productId}">
									<img class="product_small" src="data:image/jpg;base64,${product.base64Image}" />
								</a>
							</div>
						</div>
						<div id="search_description">
							<div>
								<h2>
									<a href="view_product?id=${product.productId}">
										<b>${product.title}</b>
									</a>
								</h2>
							</div>
							<div>
								<jsp:directive.include file="product_rating.jsp" />
							</div>
							<div><i>by ${product.author}</i></div>
							<div>
								<p>${fn: substring(product.description, 0, 100)}...</p>
							</div>
						</div>
						<div id="search_price">
							<h3>
								<b>$ ${product.price}</b>
							</h3>
							<h3>
								<a href="add_to_cart?product_id=${product.productId}">Add to Cart</a>
							</h3>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>