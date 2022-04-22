package com.quanlybanhang.dao.impl;

import java.util.List;

import com.quanlybanhang.dao.ICartDAO;
import com.quanlybanhang.mapper.CartMapper;
import com.quanlybanhang.model.CartModel;

public class CartDAO extends AbstractDAO<CartModel> implements ICartDAO{

	@Override
	public List<CartModel> findAll() {
		String sql = "SELECT * FORM cart";
		List<CartModel> carts = query(sql, new CartMapper());
		return carts.isEmpty() ? null : carts;
	}

	@Override
	public CartModel findOneByUserId(Long userId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM cart AS c ");
		sql.append(" JOIN user AS u ON u.id = c.user_id");
		sql.append(" WHERE c.user_id = ?");
		sql.append(" AND c.user_id IN (SELECT id from user)");
		List<CartModel> carts = query(sql.toString(), new CartMapper(), userId);
		return carts.isEmpty() ? null : carts.get(0);
	}

	@Override
	public CartModel findOneById(Long cartId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM cart");
		sql.append(" WHERE id = ?");
		List<CartModel> carts = query(sql.toString(), new CartMapper(), cartId);
		return carts.isEmpty() ? null : carts.get(0);
	}

	@Override
	public Long save(CartModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO cart (created_date,status,user_id)");
		sql.append(" VALUES (?,?,?)");
		return insert(sql.toString(), model.getCreatedDate(), 1, model.getUserId());
	}

	

}
