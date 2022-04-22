<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <section class="jumbotron text-center">
      <div class="container">
        <h1 class="jumbotron-heading">E-COMMERCE CART</h1>
      </div>
    </section>
	 <div class="container mb-4">
      <div class="row">
        <div class="col-12">
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">Image</th>
                  <th scope="col">Name</th>
               
                  <th scope="col" class="text-center">Quantity</th>
                  <th scope="col" class="text-right">Price</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>   
              
              <!-- cart-product -->
              <c:forEach var="item" items="${cartDetail}">
              	<tr>
                  <td><img style="height:100px;with:100px; object-fit: cover" src="${item.product.image}" /></td>
                  <td>${item.product.name}</td>
                  <td>
                    <input
                      class="form-control"
                      type="number"
                      value="${item.productQuantity}"
                      min="1"
                      max="${item.product.quantity}"
                    />
                  </td>
                  <td class="text-right "><span class = "price">${item.product.price * item.productQuantity}</span> VNĐ</td>
                  <td class="text-right">
                    <button class="btn btn-sm btn-danger">
                      <i class="bi bi-trash"></i>
                    </button>
                  </td>
                </tr>
			  </c:forEach>
                
                
                
                <!-- money -->
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td>Sub-Total</td>
                  <td class="text-right"><span class = "subTotal"></span> VNĐ</td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td>Shipping</td>
                  <td class="text-right shipping">6.90 VNĐ</td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><strong>Total</strong></td>
                  <td class="text-right"><strong class = "total"></strong> VNĐ</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="col mb-2">
          <div class="row">
            <div class="col-sm-12 col-md-6">
              <button class="btn btn-block btn-light">Continue Shopping</button>
            </div>
            <div class="col-sm-12 col-md-6 text-right">
              <button class="btn btn-lg btn-block btn-success text-uppercase">
                Checkout
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <%@ include file="/common/web/footer.jsp"%>
	<script>
		
		function total(){
			var element = document.querySelectorAll('.price');
			var sum = 0;
			for(var i = 0; i < element.length; i++){
				var num = Number(element[i].innerText);
				sum += num;
			}
			$('.subTotal').html(sum);
			$('.total').html(sum + 6,90);
		}
		total();
		
	</script>
</body>
</html>