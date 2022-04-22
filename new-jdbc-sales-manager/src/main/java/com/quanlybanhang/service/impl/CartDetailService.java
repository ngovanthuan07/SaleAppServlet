package com.quanlybanhang.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.quanlybanhang.dao.ICartDetailDAO;
import com.quanlybanhang.model.CartDetailModel;
import com.quanlybanhang.service.ICartDetailService;

public class CartDetailService implements ICartDetailService{
	
	@Inject
	private ICartDetailDAO cartDetailDAO;

	@Override
	public List<CartDetailModel> findAll() {
		return cartDetailDAO.findAll();
	}
	
	@Override
	public List<CartDetailModel> findByCartId(Long cartId) {
		// TODO Auto-generated method stub
		return cartDetailDAO.findByCartId(cartId);
	}



	@Override
	public CartDetailModel findByCartIdAndProductId(Long cartId, Long productId) {
		// TODO Auto-generated method stub
		return cartDetailDAO.findByCartIdAndProductId(cartId, productId);
	}


	@Override
	public CartDetailModel save(CartDetailModel cartDetail) {
		Long id = cartDetailDAO.save(cartDetail);
		return findByCartIdAndProductId(cartDetail.getCart().getId(), id);
	}

	@Override
	public void delete(CartDetailModel cartDetailModel) {
		cartDetailDAO.delete(cartDetailModel);
	}

	@Override
	public void update(CartDetailModel cartDetailModel) {
		cartDetailDAO.update(cartDetailModel);
	}





}
