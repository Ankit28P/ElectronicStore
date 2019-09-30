<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Review</title>
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
		<h2 class="pageheading">Edit Review</h2>
	</div>

	<div align="center">
		<form action="update_review" method="POST" id="reviewForm">
			<input type="hidden" name="reviewId" value="${review.reviewId}">
			<table class="form">
				<tr>
					<td align="right">Product:</td>
					<td align="left"><b>${review.Product.title}</b></td>
				</tr>
				<tr>
					<td align="right">Rating:</td>
					<td align="left"><b>${review.rating}</b></td>
				</tr>
				<tr>
					<td align="right">Customer:</td>
					<td align="left"><b>${review.customer.fullname}</b></td>
				</tr>
				<tr>
					<td align="right">Headline:</td>
					<td align="left"><input type="text" id="headline"
						name="headline" size="60" value="${review.headline}" /></td>
				</tr>
				<tr>
					<td align="right">Comment:</td>
					<td align="left"><textarea rows="5" cols="70" name="comment">${review.comment}</textarea>
					</td>
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
	$(document).ready(function() {

		$("#reviewForm").validate({
			rules : {
				headline : "required",
				comment : "required",
			},
			messages : {
				headline : "please enter Headline!",
				comment : "please enter Comment!",
			}
		});
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		})
	});
	
</script>
</html>