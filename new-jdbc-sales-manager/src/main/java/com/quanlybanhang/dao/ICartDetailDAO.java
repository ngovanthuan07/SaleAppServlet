package com.quanlybanhang.dao;

import java.util.List;

import com.quanlybanhang.model.CartDetailModel;

public interface ICartDetailDAO extends GenericDAO<CartDetailModel>{
	List<CartDetailModel> findAll();
	List<CartDetailModel> findByCartId(Long cartId);
	CartDetailModel findByCartIdAndProductId(Long cartId, Long productId);
	Long save(CartDetailModel cartDetail);
	void delete(CartDetailModel cartDetailModel);
	void update(CartDetailModel cartDetailModel);
}