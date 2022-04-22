package com.quanlybanhang.service;

import java.util.List;

import com.quanlybanhang.model.CartModel;

public interface ICartService {
	List<CartModel> findAll();
	CartModel findOneById(Long cartId);
	CartModel findOneByUserId(Long userId);
	CartModel save(CartModel model);
}
