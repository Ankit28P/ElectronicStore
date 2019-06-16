<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Product</title>
	<link rel="stylesheet" href="../css/style.css" />
	<link rel="stylesheet" 
		href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" 
		href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/richtext.min.css" />
	<script 
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	</script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script 
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js">
	</script>
	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
	</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${product != null}">
			Edit Product
			</c:if>
			<c:if test="${product == null}">
			Create New Product
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${product != null}">
			<form action="update_product" method="POST" id="productForm" enctype="multipart/form-data">
			<input type="hidden" name="productId" value="${product.productId}">
		</c:if>
		<c:if test="${product == null}">
			<form action="create_product" method="POST" id="productForm" enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Category: </td>
				<td>
					<select name="category">
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq product.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne product.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
				
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title" name="title" size="20" value="${product.title}"/></td>
			</tr>
			<tr>
				<td align="right">Owner:</td>
				<td align="left"><input type="text" id="author" name="author" size="20" value="${product.author}"/></td>
			</tr>
			<tr>
				<td align="right">Identification Id:</td>
				<td align="left"><input type="text" id="isbn" name="isbn" size="20" value="${product.isbn}"/></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left"><input type="text" id="publishDate" name="publishDate" size="20" 
				value="<fmt:formatDate pattern='mm/dd/yyyy' value='${product.publishDate}' />" /></td>
			</tr>
			<tr>
				<td align="right">Product Image:</td>
				<td align="left">
					<input type="file" id="productImage" name="productImage" size="20" /><br/>
					<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px"
						src="data:image/jpg;base64,${product.base64Image}"
					/>
				</td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price" name="price" size="20" value="${product.price}"/></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
				<textarea rows="5" cols="50" name="descreption" id="descreption">${product.description}</textarea>
			</tr>
			<tr>
				<td>&nbsp;</td>
				
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="javascript:history.go(-1);">Cancel</button>
				</td>
				
				
			</tr>
		</table>
		</form>
	</div>

	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	$(document).ready(function(){
		$('#publishDate').datepicker();
		$('#descreption').richText();
		$('#productImage').change(function(){
			showImageThumbnail(this);
		})
		
		$("#productForm").validate({
			rules:{
				category: "required",
				title: "required",
				author: "required",
				isbn: "required",
				publishDate: "required",
				
				<c:if test="${product == null}">
					productImage: "required",
				</c:if>
				
				price: "required",
				descreption: "required"
			},
			messages: {
				category: "Please select Category for the product!",
				title: "please enter title!",
				author: "please enter author!",
				isbn: "please enter ISBN",
				publishDate: "please enter Publish Date!",
				productImage: "please enter product Image!",
				price: "please enter Price!",
				descreption: "please enter Description!"
			}
		});
	});
	
	function showImageThumbnail(fileInput){
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		reader.onLoad = function(e){
			$('#thumbnail').attr('src', e.target.result);
		};
	}

	function validateFormInput() {
		var fieldCategory = document.getElementById("category");
		var fieldtitle = document.getElementById("title");
		var fieldAuthor = document.getElementById("author");
		var fieldISBN = document.getElementById("isbn");
		var fieldPublishdate = document.getElementById("publishDate");
		var fieldproductImage = document.getElementById("productImage");
		var fieldPrice = document.getElementById("price");
		var fieldDescription = document.getElementById("descreption");
		
		if (fieldCategory.value.length == 0){
			alert("Please select category!");
			fieldCategory.focus();
			return false;
		}
		if (fieldtitle.value.length == 0){
			alert("please enter title");
			fieldtitle.focus();
			return false;
		}
		if (fieldAuthor.value.length == 0){
			alert("please enter author");
			fieldAuthor.focus();
			return false;
		}
		if (fieldISBN.value.length == 0){
			alert("please enter ISBN");
			fieldISBN.focus();
			return false;
		}
		if (fieldPublishdate.value.length == 0){
			alert("please enter fieldPublishdate");
			fieldPublishdate.focus();
			return false;
		}
		if (fieldproductImage.value.length == 0){
			alert("please enter fieldproductImage");
			fieldproductImage.focus();
			return false;
		}
		if (fieldPrice.value.length == 0){
			alert("please enter fieldPrice");
			fieldPrice.focus();
			return false;
		}
		if (fieldDescription.value.length == 0){
			alert("please enter fieldDescription");
			fieldDescription.focus();
			return false;
		}
		return true;
	}
</script>
</html>