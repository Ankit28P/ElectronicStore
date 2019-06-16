<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit a Customer</title>
<link rel="stylesheet" href="../productStroreWebsite/css/style.css" />
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>
<script type="text/javascript" src="../productStroreWebsite/js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Edit my profile</h2>
	</div>

	<div align="center">

		<form action="update_profile" method="POST" id="customerForm">
			<table class="form">
				<tr>
					<td align="right">E-mail:</td>
					<td align="left"><b>${loggedCustomer.email}</b> (cannot be changed)</td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" id="fullname"
						name="fullname" size="45" value="${loggedCustomer.fullname }"/></td>
				</tr>
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" id="phone" name="phone"
						size="45" value="${loggedCustomer.phone }"/></td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" id="address"
						name="address" size="45" value="${loggedCustomer.address }"/></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" id="city" name="city"
						size="45" value="${loggedCustomer.city }"/></td>
				</tr>
				<tr>
					<td align="right">ZipCode:</td>
					<td align="left"><input type="text" id="zipcode"
						name="zipcode" size="45" value="${loggedCustomer.zipcode }"/></td>
				</tr>
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" id="country"
						name="country" size="45" value="${loggedCustomer.country }"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<i>(Leave password field blank, if do you not want to change password.)</i>
					</td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45"  /></td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" id="confirm_password"
						name="confirm_password" size="45" /></td>
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

		$("#customerForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				fullname : "required",
				confirm_password : {
					equalTo : "#password"
				},
				phone : "required",
				address : "required",
				city : "required",
				zipcode : "required",
				country : "required"
			},
			messages : {
				email : {
					required : "Please Enter e-mail address",
					email : "Please enter valid e-mail address"
				},
				fullname : "please enter fullname!",
				confirm_password : {
					equalTo : "Confirm Password does not match with password!"
				},
				phone : "please enter phone number!",
				address : "please enter product Image!",
				city : "please enter City!",
				zipcode : "please enter zipcode!",
				country : "please enter country!"
			}
		});
	});

	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];

		var reader = new FileReader();
		reader.onLoad = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};
	}
</script>
</html>