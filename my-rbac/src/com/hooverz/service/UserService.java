package com.hooverz.service;

import java.sql.SQLException;

import com.hooverz.domain.User;

import com.hooverz.dao.UserDao;

public class UserService {

	/**
	 * 登录
	 * 
	 * @param commitUser
	 * @return
	 * @throws SQLException
	 */
	public User login(User commitUser) throws SQLException {

		UserDao userDao = new UserDao();

		User dbUsre = userDao.findUserByEmailAndPassword(commitUser);

		return dbUsre;
	}

}
