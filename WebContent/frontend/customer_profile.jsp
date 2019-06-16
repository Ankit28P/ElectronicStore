<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile- Online product Store</title>
<link rel="stylesheet" href="../productStroreWebsite/css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<br/>
		<h2>Welcome, ${loggedCustomer.fullname} </h2>
	
		<table class="customer">
			<tr>
				<td><b>Email address: </b></td>
				<td>${loggedCustomer.email} </td>
			</tr>
			<tr>
				<td><b>Phone: </b></td>
				<td>${loggedCustomer.phone} </td>
			</tr>
			<tr>
				<td><b>Address: </b></td>
				<td>${loggedCustomer.address} </td>
			</tr>
			<tr>
				<td><b>City: </b></td>
				<td>${loggedCustomer.city} </td>
			</tr>
			<tr>
				<td><b>ZipCode: </b></td>
				<td>${loggedCustomer.zipcode} </td>
			</tr>
			<tr>
				<td><b>Country: </b></td>
				<td>${loggedCustomer.country} </td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center"><b><a href="edit_profile">Edit my profile</a></b></td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>