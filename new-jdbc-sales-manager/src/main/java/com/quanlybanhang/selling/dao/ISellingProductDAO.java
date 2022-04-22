package com.quanlybanhang.selling.dao;

import java.util.List;

import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.selling.model.SellingProductModel;

public interface ISellingProductDAO extends GenericDAO<SellingProductModel> {
	List<ProductModel> findAll(Pageble pageble, SellingProductModel model);
}
