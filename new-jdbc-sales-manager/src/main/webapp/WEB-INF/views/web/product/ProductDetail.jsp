<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="AddToCart" value="/gio-hang" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">

						<div class="preview-pic tab-content">
							<div class="tab-pane active" id="pic-1">
								<img src="${model.image}" />
							</div>
							<div class="tab-pane" id="pic-2">
								<img src="${model.image}" />
							</div>
							<div class="tab-pane" id="pic-3">
								<img src="${model.image}" />
							</div>
							<div class="tab-pane" id="pic-4">
								<img src="${model.image}" />
							</div>
							<div class="tab-pane" id="pic-5">
								<img src="${model.image}" />
							</div>
						</div>
						<ul class="preview-thumbnail nav nav-tabs">
							<li class="active"><a data-target="#pic-1" data-toggle="tab"><img
									src="${model.image}" /></a></li>
							<li><a data-target="#pic-2" data-toggle="tab"><img
									src="${model.image}" /></a></li>
							<li><a data-target="#pic-3" data-toggle="tab"><img
									src="${model.image}" /></a></li>
							<li><a data-target="#pic-4" data-toggle="tab"><img
									src="${model.image}" /></a></li>
							<li><a data-target="#pic-5" data-toggle="tab"><img
									src="${model.image}" /></a></li>
						</ul>

					</div>
					<div class="details col-md-6">
						<h3 class="product-title">${model.name}</h3>
						<div class="rating">
							<div class="stars">
								<span class="fa fa-star checked"></span> <span
									class="fa fa-star checked"></span> <span
									class="fa fa-star checked"></span> <span
									class="fa fa-star checked"></span> <span
									class="fa fa-star checked"></span>
							</div>
						</div>
						<p class="product-description">${model.description}</p>
						<h4 class="price">
							current price: <span>${model.price} VNĐ</span>
						</h4>
						<p class="vote">
							<strong>91%</strong> of buyers enjoyed this product! <strong>(87
								votes)</strong>
						</p>
						<h5 class="sizes">
							sizes: <span class="size" data-toggle="tooltip" title="small">s</span>
							<span class="size" data-toggle="tooltip" title="medium">m</span>
							<span class="size" data-toggle="tooltip" title="large">l</span> <span
								class="size" data-toggle="tooltip" title="xtra large">xl</span>
						</h5>
						<h5 class="colors">
							colors: <span class="color orange not-available"
								data-toggle="tooltip" title="Not In store"></span> <span
								class="color green"></span> <span class="color blue"></span>
						</h5>
						<div class="action">
							<button class="add-to-cart btn btn-default" type="button">add
								to cart</button>
							<button class="like btn btn-default" type="button">
								<span class="fa fa-heart"></span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/web/footer.jsp"%>
	<script>
		$('.add-to-cart').click(function addToCart() {
			window.location.href = "${AddToCart}?id=" + ${model.id};
		});
	</script>
</body>
</html>