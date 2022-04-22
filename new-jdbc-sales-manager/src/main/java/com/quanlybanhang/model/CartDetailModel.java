package com.quanlybanhang.model;

public class CartDetailModel extends AbstractModel<CartDetailModel>{
	private int productQuantity;
	private ProductModel product;
	private CartModel cart;
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public CartModel getCart() {
		return cart;
	}
	public void setCart(CartModel cart) {
		this.cart = cart;
	}
}
