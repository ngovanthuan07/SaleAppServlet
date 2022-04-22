package com.quanlybanhang.dao.impl;

import java.util.List;

import com.quanlybanhang.dao.ICartDetailDAO;
import com.quanlybanhang.mapper.CartDetailMapper;
import com.quanlybanhang.model.CartDetailModel;

public class CartDetailDAO extends AbstractDAO<CartDetailModel> implements ICartDetailDAO{

	@Override
	public List<CartDetailModel> findAll() {
		String sql = "SELECT * FROM cart_detail";
		List<CartDetailModel> cartDetails = query(sql, new CartDetailMapper());
		return cartDetails.isEmpty() ? null : cartDetails;
	}
	
	@Override
	public List<CartDetailModel> findByCartId(Long cartId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM cart_detail AS CD");
		sql.append(" JOIN cart AS C ON C.id = CD.cart_id");
		sql.append(" JOIN product AS P ON P.id = CD.product_id");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND CD.cart_id = ?");
		sql.append(" AND CD.cart_id IN (SELECT id FROM cart WHERE cart.id = CD.cart_id)");
		List<CartDetailModel> cartDetails = query(sql.toString(),new CartDetailMapper(), cartId);
		return cartDetails.isEmpty() ? null : cartDetails;
	}



	@Override
	public CartDetailModel findByCartIdAndProductId(Long cartId, Long productId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM cart_detail AS CD");
		sql.append(" JOIN cart AS C ON C.id = CD.cart_id");
		sql.append(" JOIN product AS P ON P.id = CD.product_id");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND CD.cart_id = ? AND CD.product_id = ?");
		sql.append(" AND CD.cart_id IN (SELECT id FROM cart WHERE cart.id = CD.cart_id)");
		sql.append(" AND CD.product_id IN (SELECT id FROM product WHERE product.id = CD.product_id)");
		List<CartDetailModel> cartDetails = query(sql.toString(),new CartDetailMapper(), cartId, productId);
		return cartDetails.isEmpty() ? null : cartDetails.get(0);
	}
	

	@Override
	public Long save(CartDetailModel cartDetail) {
		StringBuilder sql = new StringBuilder("INSERT INTO cart_detail");
		sql.append(" (product_quantity,product_id,cart_id)");
		sql.append(" VALUES (?,?,?)");
		return insert(sql.toString(), cartDetail.getProductQuantity(), cartDetail.getProduct().getId(),cartDetail.getCart().getId());
	}

	@Override
	public void delete(CartDetailModel cartDetailModel) {
		StringBuilder sql = new StringBuilder("DELETE FROM cart_detail");
		sql.append(" WHERE cart_id = ? AND product_id = ?");
		update(sql.toString(), cartDetailModel.getCart().getId(), cartDetailModel.getProduct().getId());
	}

	@Override
	public void update(CartDetailModel cartDetailModel) {
		StringBuilder sql = new StringBuilder("UPDATE cart_detail SET ");
		sql.append(" product_quantity = ?");
		sql.append(" WHERE cart_id = ? AND product_id = ?");
		update(sql.toString(), cartDetailModel.getProductQuantity(), cartDetailModel.getCart().getId(), cartDetailModel.getProduct().getId()); 
	}



	

}
