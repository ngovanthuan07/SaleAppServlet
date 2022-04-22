package com.quanlybanhang.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.quanlybanhang.dao.ICartDAO;
import com.quanlybanhang.model.CartModel;
import com.quanlybanhang.service.ICartService;

public class CartService implements ICartService{
	
	@Inject
	private ICartDAO cartDAO;

	@Override
	public List<CartModel> findAll() {
		return cartDAO.findAll();
	}

	@Override
	public CartModel findOneById(Long cartId) {
		return null;
	}

	@Override
	public CartModel findOneByUserId(Long userId) {
		return cartDAO.findOneByUserId(userId);
	}

	@Override
	public CartModel save(CartModel model) {
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id= cartDAO.save(model);
		return cartDAO.findOneById(id);
	}

}
