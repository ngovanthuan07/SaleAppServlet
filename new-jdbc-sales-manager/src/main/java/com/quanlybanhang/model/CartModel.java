package com.quanlybanhang.model;

public class CartModel extends AbstractModel<CartModel> {
	
	private int status;
	private Long userId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
