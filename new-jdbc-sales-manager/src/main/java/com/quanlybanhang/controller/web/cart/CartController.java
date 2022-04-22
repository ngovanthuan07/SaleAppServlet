package com.quanlybanhang.controller.web.cart;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.quanlybanhang.dao.ICartDetailDAO;
import com.quanlybanhang.model.CartDetailModel;
import com.quanlybanhang.model.CartModel;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.model.UserModel;
import com.quanlybanhang.service.ICartService;
import com.quanlybanhang.utils.SessionUtil;

@WebServlet(urlPatterns = { "/gio-hang" })
public class CartController extends HttpServlet {

	@Inject
	protected ICartService cartService;
	
	@Inject
	protected ICartDetailDAO cartDetailService;
	
	private static final long serialVersionUID = -3602009108956933671L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new Gson();
		String id = req.getParameter("id");
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (user != null) {
			CartModel cart = cartService.findOneByUserId(user.getId());
			if (cart == null) {
				cart = new CartModel();
				cart.setUserId((Long) user.getId());
				cart = cartService.save(cart);
			}
			// khiểm tra sản phầm có tồn tại trong gỏ hàng chưa
			Long productId = Long.valueOf(id); 
			CartDetailModel cartDetail = cartDetailService.findByCartIdAndProductId(cart.getId(), productId);
			if(cartDetail != null) {
				cartDetail.setProductQuantity(cartDetail.getProductQuantity() + 1);
				cartDetailService.update(cartDetail);
			} else {
				ProductModel product = new ProductModel();
				product.setId(productId);
				cartDetail = new CartDetailModel();
				cartDetail.setCart(cart);
				cartDetail.setProduct(product);
				cartDetail.setProductQuantity(1);
				productId =  cartDetailService.save(cartDetail);
			}
			cartDetail.setListResult(cartDetailService.findByCartId(cartDetail.getCart().getId()));
			req.setAttribute("cartDetail", cartDetail.getListResult());
		}

		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/web/cart/cart.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
