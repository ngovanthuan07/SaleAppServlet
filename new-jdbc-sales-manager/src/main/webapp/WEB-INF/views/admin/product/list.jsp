<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "NewURL" value="/admin-new"/>
<c:url var = "APIurl" value="/api-admin-new"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Danh sách bài viết</title>
</head>
<body>
	
	<div class="main-content">
		<form action="<c:url value='/admin-new'/>" id="formSubmit"
			method="GET">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<!-- edit -->
					<div class="widget-box table-filter">
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a flag="info"
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Thêm bài viết'
										href='<c:url value="/admin-new?type=edit"/>'> <span>
											<i class="fa fa-plus-circle bigger-110 purple"></i>
									</span>
									</a>
									<button id="btnDelete" type="button"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa bài viết'>
										<span> <i class="fa fa-trash-o bigger-110 pink"></i>
										</span>
									</button>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<!-- first table -->
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Image</th>
													<th>Name Product</th>
													<th>Description</th>
													<th>Price</th>
													<th>Quantity</th>
													<th>Operation</th>
												</tr>
											</thead>
											<tbody id = "tbody-list">
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
														<td><img src="${item.image}" alt="" width="100" height="100"></td>
														<td>${item.name}</td>
														<td>${item.description}</td>
														<td>${item.price}</td>
														<td>${item.quantity}</td>
														<td>
																	<c:url var="editURL" value="/admin-new">
																		<c:param name="type" value="edit" />
																		<c:param name="id" value="${item.id}" />
																	</c:url>
																	 <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" title="Cập nhật bài viết" href='${editURL}'>
																		<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																	 </a>															
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
										
										<!-- chuyen trang -->
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page"/>
										<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
										<input type="hidden" value="" id="sortName" name="sortName"/>
										<input type="hidden" value="" id="sortBy" name="sortBy"/>
										<input type="hidden" value="" id="type" name="type"/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- /.main-content -->
	<script>

	var totalPages = ${model.totalPage};
	var currentPage = ${model.page};
	var limit = 4;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
			        var data = {};
					if (currentPage != page || page == 1) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('price');
						$('#sortBy').val('desc');
						$('#type').val('list');
				        var formData = $('#formSubmit').serializeArray();
				        $.each(formData, function (i, v) {data[""+v.name+""] = v.value;});
				        handlePage(data);
					}
				}
			});
		});
		
		
		
		function handlePage(data){
			$.ajax({
	            url: '${APIurl}',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            dataType: 'json',
	            success: function (result) {
	            	$('#tbody-list').html(result);
	            },
	            error: function (error) {
	                console.log("That Bai");
	            }
	        });
		}
		// checkAll 1:
		$("#checkAll").click(
		        function check_uncheck_checkbox() {
		          let isChecked = this.checked;
		            if(isChecked) {
		                $('tbody input[type=checkbox]').each(function() { 
		                  this.checked = true; 
		                });
		            } else {
		                $('tbody input[type=checkbox]').each(function() {
		                  this.checked = false;
		              });
		         }
		             
		});
		
		
		//CheckAll 2:
		/*
	    $("#checkAll").click(function(){
	        $("tbody input[type=checkbox]").prop('checked', $(this).prop('checked'));
	    });
		*/		

	$("#btnDelete").click(function() {
		
		var data = {};
		var ids = $('tbody input[type=checkbox]:checked').map(function () {
			return $(this).val();
		}).get();
		data['ids'] = ids;
	
		deleteNew(data);
	});


	function deleteNew(data){
		$.ajax({
			url: '${APIurl}',
			type: 'DELETE',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function (result) {
				window.location.href = "${NewURL}?type=list&page=1&maxPageItem=4&sortName=price&sortBy=desc";
				
			},
			error: function (error) {
				<%--window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";--%>
				console.log(error);
			}
		});
	}
	</script>
</body>
</html>
