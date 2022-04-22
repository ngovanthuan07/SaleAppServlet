package com.quanlybanhang.controller.admin.product;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanlybanhang.constants.SystemConstant;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.PageRequest;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.service.ICategoryService;
import com.quanlybanhang.service.IProductService;
import com.quanlybanhang.sort.Sorter;
import com.quanlybanhang.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class ProductPage extends HttpServlet{

	private static final long serialVersionUID = -8556859759259587853L;

	@Inject
	private IProductService productService;
	
	@Inject
	private ICategoryService categoryService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		ProductModel model = HttpUtil.of(req.getReader()).toModel(ProductModel.class);
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
					new Sorter(model.getSortName(), model.getSortBy()));	
			model.setTotalItem(productService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResult(productService.findAll(pageble));
			StringBuilder sb = new StringBuilder();
			model.getListResult().forEach(item -> {
				sb.append(
						"<tr>\r\n"
								+ "	<td><input type=\"checkbox\" id=\"checkbox_${"+item.getId()+"}\" value=\""+item.getId()+"\"></td>\r\n"
								+ "	<td><img src=\""+item.getImage() +"\" alt=\"\" width=\"100\" height=\"100\"></td>\r\n"
								+ "	<td>"+item.getName()+"</td>\r\n"
								+ "	<td>"+item.getDescription()+"</td>\r\n"
								+ "	<td>"+item.getPrice()+"</td>\r\n"
								+ "	<td>"+item.getQuantity()+"</td>\r\n"
								+ "	<td>\r\n"
									+ "				<a class=\"btn btn-sm btn-primary btn-edit\" data-toggle=\"tooltip\" title=\"Cập nhật bài viết\" href='"+req.getContextPath()+"/admin-new?type=edit&id="+item.getId()+"'>\r\n"
									+ "						<i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i>\r\n"
									+ "		</a>\r\n"
								+ " </td>\r\n"
							+ "	</tr>\n"
						);
			});
			mapper.writeValue(resp.getOutputStream(), sb.toString());	
		} else {
			if(model.getType().equals(SystemConstant.EDIT)) {
				model = productService.save(model);
				mapper.writeValue(resp.getOutputStream(), model.getId());	
			}
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		ProductModel model = HttpUtil.of(req.getReader()).toModel(ProductModel.class);
		model = productService.update(model);
		mapper.writeValue(resp.getOutputStream(), model);	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		ProductModel model = HttpUtil.of(req.getReader()).toModel(ProductModel.class);
		productService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");	
	}
}
