package com.quanlybanhang.dao.impl;

import java.util.List;

import com.quanlybanhang.dao.IUserDAO;
import com.quanlybanhang.mapper.UserMapper;
import com.quanlybanhang.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.role_id");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}
}
