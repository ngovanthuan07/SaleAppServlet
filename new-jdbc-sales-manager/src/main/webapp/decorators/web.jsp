<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Shop Homepage - Start Bootstrap Template</title>
<!-- Core theme CSS (includes Bootstrap)-->

<link rel="icon" type="image/x-icon"
	href="<c:url value='/template/web/assets/favicon.ico' />" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link href="<c:url value = '/template/web/css/productdetail.css'/>"
	rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="<c:url value = '/template/web/css/styles.css'/>"
	rel="stylesheet" />
<link href="<c:url value = '/template/cart/css/styles.css'/>"
	rel="stylesheet" />
<style>
body {
	background-color: #f0f2f5;
}

.navbar.bg-light {
	background-color: #fcfdff !important;
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/common/web/header.jsp"%>
	<!-- header -->
	<dec:body />


	
</body>
</html>