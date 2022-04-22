package com.quanlybanhang.selling.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.quanlybanhang.mapper.ProductMapper;
import com.quanlybanhang.model.ProductModel;
import com.quanlybanhang.orm.query.sqlquery.ISQLQuery;
import com.quanlybanhang.orm.session.ISession;
import com.quanlybanhang.orm.session.SessionFactory;
import com.quanlybanhang.paping.Pageble;
import com.quanlybanhang.selling.dao.ISellingProductDAO;
import com.quanlybanhang.selling.model.SellingProductModel;

public class SellingProductDAO implements ISellingProductDAO{

	@Override
	public List<SellingProductModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> findAll(Pageble pageble, SellingProductModel model) {
		Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT DISTINCT p.* FROM product p");
        StringBuilder from = new StringBuilder();
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        // tim kiem theo ten
        if (StringUtils.isNotBlank(model.getProduct().getName())) {
            where.append(" AND p.name LIKE {name}");
            paramMap.put("name", "%" + model.getProduct().getName() + "%");
        }
        // tim kiem theo loai san pham
        if(model.getProduct().getCategoryId() != null) {
        	 from.append(" JOIN category c ON c.id = p.category_id");
             where.append(" AND p.category_id = {categoryId}");
             where.append(" AND p.category_id IN (SELECT id FROM category WHERE c.id = p.category_id)");
             paramMap.put("categoryId", model.getProduct().getCategoryId());
        }
        // tim kiem theo gia san pham
        if(model.getPriceMin() != null) {
             where.append(" AND price >= {priceMin}");
             paramMap.put("priceMin", model.getPriceMin());
        }
        if(model.getPriceMax() != null) {
        	where.append(" AND price <= {priceMax}");
        	 paramMap.put("priceMax", model.getPriceMax());
        }
        // phan trang
		if(pageble.getSorter() != null) {
			where.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			//where.append(" LIMIT "+ pageble.getOffset() + ", " + pageble.getLimit() + "");
			// day load page nen dung ntn
			where.append(" LIMIT "+ pageble.getPage()+ ", " + pageble.getLimit() + "");
		} 
		
        ISession session = SessionFactory.openSession();
        sqlBuilder.append(from).append(where);
        try {
        	ISQLQuery sqlQuery = session.createSQLQuery(sqlBuilder.toString());
        	sqlQuery.setParamMap(paramMap);
        	return sqlQuery.query(new ProductMapper());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
