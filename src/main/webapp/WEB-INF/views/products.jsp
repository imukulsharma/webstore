<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>

	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<img alt="${product.productId} Image "
							src="<c:url  value="/resource/images/${product.productId}.png" />">
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>${product.unitPrice}USD</p>
							<p>Available ${product.unitsInStock} units in stock</p>

							<a
								href="<spring:url value="/products/product?id=${product.productId}" />"
								class="btn btn-primary"><span
								class="glyphicon-info-sign glyphicon"></span> Details </a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>


</body>

</html>