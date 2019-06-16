<div class="Dproduct">
					<div>
						<a href="view_product?id=${product.productId}">
							<img class="product_small" src="data:image/jpg;base64,${product.base64Image}"/>
						</a>
					</div>
					<div>
						<a href="view_product?id=${product.productId}">
							<b>${product.title}</b>
						</a>
					</div>
					<div>
						<c:forTokens items="${product.ratingStar}" delims="," var="star">
							<c:if test="${star eq 'on'}">
								<img src="../productStroreWebsite/images/rating-on.png">
							</c:if>
							<c:if test="${star eq 'off'}">
								<img src="images/rating-off.png">
							</c:if>
							<c:if test="${star eq 'half'}">
								<img src="images/rating-half.png">
							</c:if>
						</c:forTokens>						
					</div>
					<div><i>by ${product.author}</i></div>
					<div><b>$ ${product.price}</b></div>
				</div>