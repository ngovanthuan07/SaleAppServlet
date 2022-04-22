package com.quanlybanhang.service.impl;

import javax.inject.Inject;

import com.quanlybanhang.dao.IUserDAO;
import com.quanlybanhang.model.UserModel;
import com.quanlybanhang.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	private IUserDAO userDAO;

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
