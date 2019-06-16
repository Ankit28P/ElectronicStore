<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.title} - Online product Shopping</title>
<link rel="stylesheet" href="../productStroreWebsite/css/style.css" />
<script 
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<table 	class="product"> 
			<tr>
				<td colspan="3" align="left">
					<p id="product_title">${product.title}</p> 
					<span id="product_author"> by ${product.author} </span>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<img class="product_large" src="data:image/jpg;base64,${product.base64Image}" />
				</td>
				<td valign="top" align="left">
					<jsp:directive.include file="product_rating.jsp" />&nbsp;&nbsp;
					<a href="#reviews">${fn:length(product.reviews)} Reviews</a>
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>$${product.price}</h2>
					<br/><br/>
					<button id="buttonToAddCart">Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td id="product_description">
					${product.description}
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td><h2 id="reviews"><a>Costumer Reviews</a></h2></td>
				<td colspan="2" align="center">
					<button id="buttonWriteReview">Write a Costumer Review</button>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="left">
					<table class="customer">
						<c:forEach items="${product.reviews}" var="review">
							<tr>
								<td>
									<c:forTokens items="${review.stars}" delims="," var="star">
										<c:if test="${star eq 'on'}">
											<img src="../productStroreWebsite/images/rating-on.png">
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src="../productStroreWebsite/images/rating-off.png">
										</c:if>
									</c:forTokens>
									- <b>${review.headline}</b>
								</td>
							</tr>
							<tr>
								<td>
									by ${review.customer.fullname} on ${review.reviewTime}
								</td>
							</tr>
							<tr><td><i>${review.comment}</i></td></tr>
							<tr><td>&nbsp;</td></tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function(){
			$('#buttonWriteReview').click(function(){
				window.location = 'write_review?product_id='+${product.productId};
			});
			$('#buttonToAddCart').click(function(){
				window.location = 'add_to_cart?product_id='+${product.productId};
			});
		})
	</script>
</body>
</html>