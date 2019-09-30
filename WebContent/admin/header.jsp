<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}/admin/">
			<img src="../images/electronics.png" alt="Image" height="200" width="900"/>
		</a>
	</div>

	<div>
		Welcome, <c:out value="${sessionScope.userEmail}"></c:out> | <a href="logout">Logout</a>
		<br/><br/>
	</div>
	<div id="headermenu">
		<div class="menu_item" style="display:table-cell; padding-right: 20px;">
			<a href="list_users">
				<img src="../images/users.png" style="width:60px;height:60px;"/><br/>Users
			</a>
		</div>
		<div style="display:table-cell; padding-right: 20px;">
			<a href="list_category">
				<img src="../images/category.png" style="width:60px;height:60px;"/><br/>Categories
			</a>
		</div>
		<div style="display:table-cell; padding-right: 20px;">
			<a href="list_books">
				<img src="../images/product.png" style="width:60px;height:60px;"/><br/>Products
			</a>
		</div>
		<div style="display:table-cell; padding-right: 20px;">
			<a href="list_customer">
				<img src="../images/customer.png" style="width:60px;height:60px;"/><br/>Customers
			</a>
		</div>
	</div>
</div>