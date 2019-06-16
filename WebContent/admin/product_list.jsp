<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Product - Electronicstore Administration</title>
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Product Management</h2>
		<h3>
			<a href="new_product">Create New Product</a>
		</h3>
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
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Update</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="product" items="${listproducts}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${product.productId}</td>
					<td>
						<img src="data:image/jpg;base64,${product.base64Image}" width="84" height="110"/>
					</td>
					<td>${product.title}</td>
					<td>${product.author}</td>
					<td>${product.category.name}</td>
					<td>$${product.price}</td>
					<td><fmt:formatDate pattern='mm/dd/yyyy' value='${product.publishDate}' /></td>
					<td>
						<a href="edit_product?id=${product.productId}">Edit</a> &nbsp;
						<a href="javascript:confirmDelete(${product.productId})">Delete</a>
					</td>
				<tr/>
			</c:forEach>
		</table>
	</div>
	

	
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(productId){
			if(confirm('Are you sure you want to delete the user with Id '+ productId +'?')){
				window.location = 'delete_product?id='+productId;
			}
		}
	</script>
	
</body>
</html>