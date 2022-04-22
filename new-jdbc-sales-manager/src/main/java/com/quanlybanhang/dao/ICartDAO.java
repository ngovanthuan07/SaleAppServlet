package com.quanlybanhang.dao;

import java.util.List;

import com.quanlybanhang.model.CartModel;

public interface ICartDAO extends GenericDAO<CartModel>{
	List<CartModel> findAll();
	CartModel findOneByUserId(Long userId);
	CartModel findOneById(Long cartId);
	Long save(CartModel model);
}
