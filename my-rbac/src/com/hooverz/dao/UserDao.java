package com.hooverz.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hooverz.domain.User;
import com.hooverz.utils.DataSourceUtils;
import com.hooverz.utils.EncryptionUtils;

public class UserDao {

	/**
	 * 根据用户名和密码获取到一个用户的信息
	 * 
	 * @param commitUser
	 * @return
	 * @throws SQLException
	 */
	public User findUserByEmailAndPassword(User commitUser) throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String sql = "select * from Users where email=? and password=?";

		User dbUser = runner.query(sql, new BeanHandler<User>(User.class),
				commitUser.getEmail(),
				EncryptionUtils.md5(commitUser.getPassword()));

		if (dbUser == null)
			throw new SQLException("用户不存在");

		return dbUser;
	}
}
