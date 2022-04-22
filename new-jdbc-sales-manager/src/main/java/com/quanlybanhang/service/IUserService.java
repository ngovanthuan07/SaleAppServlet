package com.quanlybanhang.service;

import com.quanlybanhang.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
