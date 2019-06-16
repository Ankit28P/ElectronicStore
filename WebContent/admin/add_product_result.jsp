<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add product To Order</title>
</head>
<body>
	<div align="center">
		<h2>The product <i>${book.title}</i> has been to added to order Id ${order.orderId}.</h2>
		<input type="button" value="Close" onclick="javascript: self.close();">
	</div>
	<script type="text/javascript">
		window.onunload =function(){
			window.opener.location.reload();
		}
	</script>
</body>
</html>