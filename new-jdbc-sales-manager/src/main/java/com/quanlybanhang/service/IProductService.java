package com.quanlybanhang.service;

import java.util.List;

import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.selling.model.SellingProductModel;

public interface IProductService {
	ProductModel findOne(Long id);
	List<ProductModel> findByCategoryId(Long categoryId);
	ProductModel save(ProductModel productModel);
	ProductModel update(ProductModel updateNew);
	void delete(long[] ids);
	void delete(long id);
	List<ProductModel> findAll(Pageble pageble);
	List<ProductModel> findAll(Pageble pageble, SellingProductModel model);
	int getTotalItem();
}
