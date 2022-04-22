package com.quanlybanhang.service;

import java.util.List;

import com.quanlybanhang.model.CartDetailModel;

public interface ICartDetailService {
	List<CartDetailModel> findAll();
	List<CartDetailModel> findByCartId(Long cartId);
	CartDetailModel findByCartIdAndProductId(Long cartId, Long productId);
	CartDetailModel save(CartDetailModel cartDetail);
	void delete(CartDetailModel cartDetailModel);
	void update(CartDetailModel cartDetailModel);
}
