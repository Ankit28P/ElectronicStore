<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Customer</title>
	<link rel="stylesheet" href="../css/style.css" />
	<link rel="stylesheet" 
		href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script 
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	</script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer != null}">
			Edit Customer
			</c:if>
			<c:if test="${customer == null}">
			Create New Customer
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${customer != null}">
			<form action="update_customer" method="POST" id="customerForm">
			<input type="hidden" name="customerId" value="${customer.customerId}">
		</c:if>
		<c:if test="${customer == null}">
			<form action="create_customer" method="POST" id="customerForm">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><input type="text" id="email" name="email" size="45" value="${customer.email}"/></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname" name="fullname" size="45" value="${customer.fullname}"/></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password" name="password" size="45" value="${customer.password}"/></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" id="confirm_password" name="confirm_password" size="45" value="${customer.password}"/></td>
			</tr>
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" id="phone" name="phone" size="45" value="${customer.phone}" /></td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input type="text" id="address" name="address" size="45" value="${customer.address}"/></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" id="city" name="city" size="45" value="${customer.city}"/></td>
			</tr>
			<tr>
				<td align="right">ZipCode:</td>
				<td align="left"><input type="text" id="zipcode" name="zipcode" size="45" value="${customer.zipcode}"/></td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" id="country" name="country" size="45" value="${customer.country}"/></td>
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
		
		$("#customerForm").validate({
			rules:{
				email: {
					required: true,
					email:true
				},
				fullname: "required",
				password: "required",
				confirm_password:{
					required: true,
					equalTo: "#password"
				},
				phone: "required",
				address: "required",
				city: "required",
				zipcode: "required",
				country: "required"
			},
			messages: {
				email: {
					required: "Please Enter e-mail address",
					email: "Please enter valid e-mail address"
				},
				fullname: "please enter fullname!",
				password: "please enter password!",
				confirm_password:{
					required: "Please confirm password!",
					equalTo: "Confirm Password does not match with password!"
				},
				phone: "please enter Publish Date!",
				address: "please enter address!",
				city: "please enter City!",
				zipcode: "please enter zipcode!",
				country: "please enter country!"
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
		var fieldBookImage = document.getElementById("bookImage");
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
		if (fieldBookImage.value.length == 0){
			alert("please enter fieldBookImage");
			fieldBookImage.focus();
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