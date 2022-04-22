package com.quanlybanhang.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanlybanhang.constants.SystemConstant;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.model.UserModel;
import com.quanlybanhang.paping.PageRequest;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.service.ICategoryService;
import com.quanlybanhang.service.IProductService;
import com.quanlybanhang.service.IUserService;
import com.quanlybanhang.utils.FormUtil;
import com.quanlybanhang.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1226490885612716883L;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private ICategoryService categoryService;
	
	private ResourceBundle rd = ResourceBundle.getBundle("message");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			String alert = req.getParameter("alert");
			String message = req.getParameter("message");
			if (message != null && alert != null) {
				req.setAttribute("message", rd.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login");
		} 
		else {
			Pageble pageble = new PageRequest(1, 3, null);
			ProductModel model = new ProductModel();
			model.setListResult(productService.findAll(pageble));
			req.setAttribute("categories", categoryService.findAll());
			req.setAttribute(SystemConstant.MODEL, model);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/web/home.jsp");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if(model != null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if(model.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				} else {
					if(model.getRole().getCode().equals("ADMIN")) {
						resp.sendRedirect(req.getContextPath() + "/admin-home");
					}
				}
			}else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
