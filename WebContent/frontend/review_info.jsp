<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write a Review- online productstore</title>
<link rel="stylesheet" href="../ProductStroreWebsite/css/style.css" />
<script type="text/javascript"
	src="../ProductStroreWebsite/js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<form id="reviewForm">
			<table class="customer" width="60%">
				<tr>
					<td><h3>You already wrote review for this Product.</h3></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td><span id="Product_title">${Product.title} </span><br /> <img
						class="Product_large" src="data:image/jpg;base64,${Product.base64Image}" />
					</td>
					<td>
						<div id="rateYo"></div>
						<br /> 
						<input type="text" name="headline" size="60" readonly="readonly" value="${review.headline}" />
						<br /> <br /> 
						<textarea name="comment" rows="10" cols="70" readonly="readonly">${review.comment}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#rateYo").rateYo({
		    starWidth: "40px",
		    fullstar: true,
		    rating: ${review.rating},
		    readOnly: true
		  });
	})
</script>
</html>