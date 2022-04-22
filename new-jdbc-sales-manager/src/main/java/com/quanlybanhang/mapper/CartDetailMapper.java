package com.quanlybanhang.mapper;

import java.sql.ResultSet;

import com.quanlybanhang.model.CartDetailModel;
import com.quanlybanhang.model.CartModel;
import com.quanlybanhang.model.ProductModel;

public class CartDetailMapper implements RowMapper<CartDetailModel>{

	@Override
	public CartDetailModel mapRow(ResultSet rs) {
		try {
			CartDetailModel cartDetails = new CartDetailModel();
			cartDetails.setId(rs.getLong("id"));
			cartDetails.setProductQuantity(rs.getInt("product_quantity"));
			try {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategoryId(rs.getLong("category_id"));
				cartDetails.setProduct(product);
			} catch (Exception e) {
				return null;
			}
			try {
				CartModel cart = new CartModel();
				cart.setId(rs.getLong("cart_id"));
				cartDetails.setCart(cart);
			} catch (Exception e) {
				return null;
			}
			return cartDetails;
		} catch (Exception e) {
			return null;
		}
		
	}

}
