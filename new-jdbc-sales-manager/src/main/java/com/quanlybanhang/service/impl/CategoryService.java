package com.quanlybanhang.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.quanlybanhang.dao.ICategoryDAO;
import com.quanlybanhang.model.CategoryModel;
import com.quanlybanhang.service.ICategoryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		return categoryDAO.findOneByCode(code);
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDAO.findOne(id);
	}
	
	
}
