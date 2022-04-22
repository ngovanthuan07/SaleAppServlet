package com.quanlybanhang.service;

import java.util.List;

import com.quanlybanhang.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	CategoryModel findOneByCode(String code);
	CategoryModel findOne(Long id);
}
