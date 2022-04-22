package com.quanlybanhang.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanlybanhang.constants.SystemConstant;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.service.IProductService;
import com.quanlybanhang.utils.FormUtil;

@WebServlet(urlPatterns = {"/chi-tiet"})
public class ProductController extends HttpServlet{
	
	private static final long serialVersionUID = 7071359776298478176L;
	
	@Inject
	private IProductService productService;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action.equals("detail")) {
			ProductModel model = FormUtil.toModel(ProductModel.class, req);
			model = productService.findOne(model.getId());
			req.setAttribute(SystemConstant.MODEL, model);
			
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/web/product/ProductDetail.jsp");
			rd.forward(req, resp);
		}
	}
}
