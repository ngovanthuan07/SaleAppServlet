package com.quanlybanhang.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.quanlybanhang.dao.ICategoryDAO;
import com.quanlybanhang.dao.IProductDAO;
import com.quanlybanhang.dao.impl.CategoryDAO;
import com.quanlybanhang.model.CategoryModel;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.selling.dao.ISellingProductDAO;
import com.quanlybanhang.selling.model.SellingProductModel;
import com.quanlybanhang.service.IProductService;

public class ProductService implements IProductService{
	
	@Inject
	public IProductDAO productDAO;
	
	@Inject
	public ICategoryDAO categoryDAO;
	
	@Inject
	private ISellingProductDAO sellingProductDAO;

	@Override
	public ProductModel findOne(Long id) {
		return productDAO.findOne(id);
	}

	@Override
	public List<ProductModel> findByCategoryId(Long categoryId) {
		return productDAO.findByCategoryId(categoryId);
	}

	@Override
	public ProductModel save(ProductModel productModel) {
		CategoryModel category = categoryDAO.findOneByCode(productModel.getCategoryCode());
		productModel.setCategoryId(category.getId());
		Long id = productDAO.save(productModel);
		return productDAO.findOne(id);
	}

	@Override
	public ProductModel update(ProductModel updateNew) {
		CategoryModel category = categoryDAO.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(category.getId());
		productDAO.update(updateNew);;
		return productDAO.findOne(updateNew.getId()); 
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			productDAO.delete(id);
		}
	}
	@Override
	public void delete(long id) {
		productDAO.delete(id);
	}

	@Override
	public List<ProductModel> findAll(Pageble pageble) {
		return productDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return productDAO.getTotalItem();
	}

	@Override
	public List<ProductModel> findAll(Pageble pageble, SellingProductModel model) {
		return sellingProductDAO.findAll(pageble, model);
	}



}
