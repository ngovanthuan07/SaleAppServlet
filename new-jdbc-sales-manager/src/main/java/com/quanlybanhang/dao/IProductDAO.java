package com.quanlybanhang.dao;

import java.util.List;

import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.Pageble;

public interface IProductDAO extends GenericDAO<ProductModel>{
	ProductModel findOne(Long id);
	List<ProductModel> findByCategoryId(Long categoryId);
	Long save(ProductModel productModel);
	void update(ProductModel updateNew);
	void delete(long id);
	List<ProductModel> findAll(Pageble pageble);
	int getTotalItem();
}
