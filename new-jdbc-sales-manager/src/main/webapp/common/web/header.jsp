<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="<c:url value="/trang-chu" />">Shopping</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#!">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#!">About</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#!">All Products</a></li>
						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item" href="#!">Popular Items</a></li>
						<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
					</ul></li>
			</ul>
			<form class="d-flex">


				<c:if test="${not empty USERMODEL}">

					<button class="btn btn-outline-dark" type="submit">
						<i class="bi-cart-fill me-1"></i> <a style="text-decoration: none"
							href='<c:url value="/gio-hang?action=cart"/>'>Cart</a> <span
							class="badge bg-dark text-white ms-1 rounded-pill">${CartItem}</span>
					</button>

					<div class="dropdown">
						<a class="btn btn-dark dropdown-toggle" href="#" role="button"
							id="dropdownMenuLink" data-bs-toggle="dropdown"
							aria-expanded="false"><img
							src="https://lh3.googleusercontent.com/ogw/ADea4I5QZ9EXIodx_IhZD0Qsesyu928tvO3MQ-bL5GwkLg=s32-c-mo"
							class="rounded-circle" alt="Cinque Terre">
							${USERMODEL.fullName} </a>

						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><a class="dropdown-item" href="<c:url value="/thoat?action=logout"/>">Đăng Xuất</a></li>								
						</ul>
					</div>
				</c:if>



				<c:if test="${empty USERMODEL}">
					<a style="text-decoration: none"
						href='<c:url value="/dang-nhap?action=login"/>'>
						<div class="btn btn-outline-dark" type="submit"> 
							<i class="bi-cart-fill me-1"></i> Cart <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>	
						</div>
					</a>
					<a style="text-decoration: none" href='<c:url value="/dang-nhap?action=login"/>'>
						<div class="btn btn-outline-dark profile-form">Đăng Nhập </div>	
					</a>
				</c:if>

			</form>
		</div>
	</div>
</nav>
