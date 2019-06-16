<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Login</title>
	<link rel="stylesheet" type="text/css" href="../css/styles.css" />
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div align="center">
		<h1>Electronic Store Administration</h1>
		<h2>Admin Login</h2>
		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message"><i>${message}</i></h4>
			</div>
		</c:if>
		<form id="formLogin" action="login" method="post" onsubmit="return validateFormInput()">
			<table>
				<tr>
					<td>Email: </td>
					<td><input type="text" name="email" id="email" size="20"></td>
				</tr>
				<tr>
					<td>Password: </td>
					<td><input type="password" name="password" id="password" size="20"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldPassword = document.getElementById("password");
		
		if (fieldEmail.value.length == 0){
			alert("Please enter Email");
			fieldEmail.focus();
			return false;
		}
		if (fieldPassword.value.length == 0){
			alert("Please enter password");
			fieldPassword.focus();
			return false;
		}
		return true;
	}
</script>
</html>