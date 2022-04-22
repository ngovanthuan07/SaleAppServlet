package com.quanlybanhang.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.quanlybanhang.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{

	@Override
	public ProductModel mapRow(ResultSet rs) {
		try {
			ProductModel products = new ProductModel();
			products.setId(rs.getLong("id"));
			products.setName(rs.getString("name"));
			products.setPrice(rs.getLong("price"));
			products.setQuantity(rs.getInt("quantity"));
			products.setDescription(rs.getString("description"));
			products.setImage(rs.getString("image"));
			products.setCategoryId(rs.getLong("category_id"));
			return products;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
