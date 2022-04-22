package com.quanlybanhang.controller.admin.product;

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
import com.quanlybanhang.paping.PageRequest;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.service.ICategoryService;
import com.quanlybanhang.service.IProductService;
import com.quanlybanhang.sort.Sorter;
import com.quanlybanhang.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewProductController extends HttpServlet{
	
	private static final long serialVersionUID = 5496952657287966880L;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private ICategoryService categoryService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel model = FormUtil.toModel(ProductModel.class, req);
		String view = "";
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
					new Sorter(model.getSortName(), model.getSortBy()));	
			model.setTotalItem(productService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResult(productService.findAll(pageble));
			view = "/WEB-INF/views/admin/product/list.jsp";
		}else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = productService.findOne(model.getId());
				model.setCategoryCode(categoryService.findOne(model.getCategoryId()).getCode());
			} else {
				
			}
			req.setAttribute("categories", categoryService.findAll());

			view = "/WEB-INF/views/admin/product/edit.jsp";
		}
		
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
