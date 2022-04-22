package com.quanlybanhang.selling.model;

import com.quanlybanhang.model.ProductModel;

public class SellingProductModel{
	
	private ProductModel product;
	private Long priceMin;
	private Long priceMax;
	
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public Long getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(Long priceMin) {
		this.priceMin = priceMin;
	}
	public Long getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(Long priceMax) {
		this.priceMax = priceMax;
	}
	
	
	
}
