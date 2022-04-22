package com.quanlybanhang.dao.impl;

import java.util.List;

import com.quanlybanhang.dao.IProductDAO;
import com.quanlybanhang.mapper.ProductMapper;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.paping.Pageble;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO{

	@Override
	public ProductModel findOne(Long id) {
		String sql = "SELECT * FROM product WHERE id = ?";
		List<ProductModel> product = query(sql, new ProductMapper(), id);
		return product.isEmpty() ? null : product.get(0);
	}

	@Override
	public List<ProductModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM product WHERE category_id = ?";
		return query(sql, new ProductMapper(), categoryId);
	}

	@Override
	public Long save(ProductModel productModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO product (name, price,");
		sql.append(" quantity, description, image, category_id)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), productModel.getName(),productModel.getPrice(),productModel.getQuantity(),productModel.getDescription(),
				productModel.getImage(),productModel.getCategoryId());
	}

	@Override
	public void update(ProductModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE product SET ");
		sql.append("name = ?, price = ?, quantity = ?, description = ?, image = ?, category_id = ? ");
		sql.append("WHERE id = ?");
		update(sql.toString(), updateNew.getName(),updateNew.getPrice(),updateNew.getQuantity(),updateNew.getDescription(),
				updateNew.getImage(),updateNew.getCategoryId(), updateNew.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM product WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<ProductModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product");
		
		if(pageble.getSorter() != null) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+ pageble.getOffset() + ", " + pageble.getLimit() + "");
		} 
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM product";
		return count(sql);
	}

}
