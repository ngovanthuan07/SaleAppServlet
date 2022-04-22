package com.quanlybanhang.mapper;

import java.sql.ResultSet;

import com.quanlybanhang.model.CartModel;

public class CartMapper implements RowMapper<CartModel>{

	@Override
	public CartModel mapRow(ResultSet rs) {
		try {
			CartModel carts = new CartModel();
			carts.setId(rs.getLong("id"));
			carts.setCreatedDate(rs.getTimestamp("created_date"));
			carts.setStatus(rs.getInt("status"));
			carts.setUserId(rs.getLong("user_id"));
			return carts;
		} catch (Exception e) {
			return null;
		}
	}

}
