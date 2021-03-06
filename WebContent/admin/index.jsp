<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Electronics Administration!</title>
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
		<hr width="60%" />
	</div>
	
	<div align="center">
		<h2 class="pageheading">Quick Actions:</h2>
		<b>
		<a href="product_form.jsp">New Product</a> |
		<a href="user_form.jsp">New User</a> |
		<a href="category_form.jsp">New Category</a> |
		<a href="customer_form.jsp">New Customer</a>
		</b>
	</div>
	
	<div align="center" >
		<hr width="60%" />
		<h2 class="pageheading">Recent Sales:</h2>
	</div>
	
	<div align="center">
		<hr width="60%" />
		<h2 class="pageheading">Recent Reviews:</h2>
	</div>
	
	<div align="center">
		<hr width="60%" />
		<h2 class="pageheading">Statistics:</h2>
		Total Users: ${totalUsers} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Products: ${totalproducts} &nbsp;&nbsp;&nbsp;&nbsp;
		Total Customers: ${totalCustomers} &nbsp;&nbsp;&nbsp;&nbsp;
		<hr width="60%" />
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>