package com.quanlybanhang.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanlybanhang.model.CategoryModel;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.PageRequest;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.selling.model.SellingProductModel;
import com.quanlybanhang.service.ICategoryService;
import com.quanlybanhang.service.IProductService;
import com.quanlybanhang.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-trang-chu" })
public class FilterProductController extends HttpServlet {

	private static final long serialVersionUID = -5878500002916889496L;

	@Inject
	private IProductService productService;

	@Inject
	private ICategoryService categoryService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		SellingProductModel sell = HttpUtil.of(req.getReader()).toModel(SellingProductModel.class);
		Pageble pageble = new PageRequest(sell.getProduct().getTotalItem(), sell.getProduct().getMaxPageItem(), null);
		ProductModel model = new ProductModel();
		CategoryModel categoryModel = categoryService.findOneByCode(sell.getProduct().getCategoryCode());
		if(categoryModel != null) {
			sell.getProduct().setCategoryId(categoryModel.getId());
		}
		model.setListResult(productService.findAll(pageble, sell));
		StringBuilder sb = new StringBuilder();
		if (model.getListResult().isEmpty()) {
			mapper.writeValue(resp.getOutputStream(), ""
					+ "<div class = "+"noProductExists"+">Không tồn tại sản phẩm</h1>");
		} else {
			model.getListResult().forEach(item -> {
				sb.append("							<div class=\"col col-sm-5 col-lg-4 mb-3 item-detal-product\"\r\n"
						+ "								item-id=\""+item.getId()+"\">\r\n"
						+ "								<div class=\"card h-100\">\r\n"
						+ "									<!-- Product image-->\r\n"
						+ "									<img class=\"img-fluid mx-auto\" alt=\"Card image cap\"\r\n"
						+ "										src=\""+item.getImage()+"\" />\r\n"
						+ "									<!-- Product details-->\r\n"
						+ "									<div class=\"card-body p-4\">\r\n"
						+ "										<div class=\"text-center\">\r\n"
						+ "											<!-- Product name-->\r\n"
						+ "											<h5 class=\"fw-bolder\">" + item.getName()
						+ "</h5>\r\n" + "											<!-- Product price-->\r\n"
						+ "											" + item.getPrice() + " VNĐ\r\n"
						+ "										</div>\r\n"
						+ "									</div>\r\n"
						+ "									<!-- Product actions-->\r\n"
						+ "								</div>\r\n" + "							</div>");
			});
			mapper.writeValue(resp.getOutputStream(), sb);
		}
	}
}
