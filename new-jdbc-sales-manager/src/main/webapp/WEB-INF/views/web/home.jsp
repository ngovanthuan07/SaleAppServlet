<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="homeShopping" value="/trang-chu" />
<c:url var="trangchuAPI" value="/api-trang-chu" />
<c:url var="productDetail" value="/chi-tiet" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<form action="" id="formSubmit">
		<div class="container pt-5">
			<div class="row">
				<div class="col-md-8 order-md-2 col-lg-9">
					<div class="container-fluid">
						<div class="row   mb-5">
							<div class="col-12">
								<div
									class="dropdown text-md-left text-center float-md-left mb-3 mt-3 mt-md-0 mb-md-0">
									<label class="mr-2">Sort by:</label> <a
										class="btn btn-lg btn-light dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Sắp xếp <span class="caret"></span></a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown"
										x-placement="bottom-start"
										style="position: absolute; transform: translate3d(71px, 48px, 0px); top: 0px; left: 0px; will-change: transform;">
										<a class="dropdown-item" href="#">Tăng dần</a> <a
											class="dropdown-item" href="#">Giảm dần</a>
									</div>
								</div>
							</div>
						</div>
						<div class="row" id="list-product">
							<c:forEach var="item" items="${model.listResult}">
								<div class="col col-sm-5 col-lg-4 mb-3 item-detal-product "
									item-id="${item.id}">
									<div class="card h-100">
										<!-- Product image-->
										<img class="img-fluid mx-auto" alt="Card image cap"
											src="${item.image}" />
										<!-- Product details-->
										<div class="card-body p-4">
											<div class="text-center">
												<!-- Product name-->
												<h5 class="fw-bolder">${item.name}</h5>
												<!-- Product price-->
												${item.price} VNĐ
											</div>
										</div>
										<!-- Product actions-->
									</div>
								</div>
							</c:forEach>

						</div>

						<div class="row sorting mb-5 mt-5">
							<div class="col-12 d-flex justify-content-center">
								<button type="button"
									class="btn btn-outline-warning d-flex justify-content-center loadPaping">See
									More</button>

							</div>
						</div>


					</div>
				</div>


				<div class="col-md-4 order-md-1 col-lg-3 sidebar-filter">
					<h3 class="mt-0 mb-5">
						Showing <span class="text-primary"></span> Products
					</h3>
					<h6 class="text-uppercase font-weight-bold mb-3">Name</h6>
					<div class="md-form mt-0">
						<input class="form-control" type="text" placeholder="Search"
							aria-label="Search" name="name" value="">
					</div>
					<h6 class="text-uppercase font-weight-bold mb-3">Categories</h6>
					<select class="form-select form-select-lg mb-3"
						aria-label=".form-select-lg example" id="categoryCode"
						name="categoryCode">
						<option selected>Chọn loại sản phẩm</option>
						<c:forEach var="item" items="${categories}">
							<option value="${item.code}">${item.name}</option>
						</c:forEach>
					</select>

					<div class="divider mt-5 mb-5 border-bottom border-secondary"></div>
					<h6 class="text-uppercase mt-5 mb-3 font-weight-bold">Price</h6>
					<div class="price-filter-control">
						<input type="number" class="form-control w-50 pull-left mb-2"
							value="" id="price-min-control" name="priceMin"> <input
							type="number" class="form-control w-50 pull-right" value=""
							id="price-max-control" name="priceMax">
					</div>
					<input id="ex2" type="text" class="slider " value="50,150"
						data-slider-min="10" data-slider-max="200" data-slider-step="5"
						data-slider-value="[50,150]" data-value="50,150"
						style="display: none;">
					<div class="divider mt-5 mb-5 border-bottom border-secondary"></div>
					<button type="button" class="btn btn-lg btn-block btn-primary mt-5"
						id="btnFilterProducts">Filter</button>
				</div>



			</div>
		</div>
	</form>
	<%@ include file="/common/web/footer.jsp"%>
	<script>
		function loadJs() {
			$(window).scroll(function() {
				var height = $(window).scrollTop();
				if (height > 52) {
					$(".navbar").addClass("fixed-top");
				} else {
					if (height < 52)
						$(".navbar").removeClass("fixed-top");
				}

			});

			$(document).ready(function() {
				var item = $('.item-detal-product').length;
				$('.text-primary').html(item);
			});

			$('#list-product .item-detal-product').click(function(){
					var $div = $(this);
					window.location.href = "${productDetail}?action=detail&id="
					+ $div.attr("item-id");
			});
		}
		loadJs();

		$('#btnFilterProducts').click(function() {
			var data = {};
			console.log("ok");
			var formData = $('#formSubmit').serializeArray();
			data["product"] = {};
			$.each(formData, function(i, v) {
				if (v.name == "name") {
					data.product["" + v.name + ""] = v.value;
				} else if (v.name == "categoryCode") {
					data.product["" + v.name + ""] = v.value;
				} else
					data["" + v.name + ""] = v.value;
			});
			data.product["totalItem"] = 0;
			data.product["maxPageItem"] = 3;
			filterProduct(data, function fillterPr(value) {
				$('#list-product').html(value);
			});

		});

		function filterProduct(data, callback) {
			$.ajax({
				url : '${trangchuAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					callback(result);
					loadJs();
				},
				error : function(error) {
					console.log("That bai");
					loadJs();
				}
			});
		};
		$('.loadPaping').click(function() {
			var totalItem = $('.item-detal-product').length;
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			data["product"] = {};
			$.each(formData, function(i, v) {
				if (v.name == "name") {
					data.product["" + v.name + ""] = v.value;
				} else if (v.name == "categoryCode") {
					data.product["" + v.name + ""] = v.value;
				} else
					data["" + v.name + ""] = v.value;
			});
			data.product["totalItem"] = totalItem;
			data.product["maxPageItem"] = 3;
			filterProduct(data, function loadDataProduct(value) {
				var listProductAdd = document.getElementById('list-product');
				listProductAdd.innerHTML += value;
			})
		});
	</script>
</body>
</html>