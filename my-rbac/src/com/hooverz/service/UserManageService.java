package com.hooverz.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.hooverz.dao.UserManageDao;
import com.hooverz.domain.User;
import com.hooverz.utils.DataSourceUtils;

public class UserManageService {

	public List<User> getAllUser() throws SQLException {

		UserManageDao userManageDao = new UserManageDao();

		return userManageDao.findAllUser();
	}

	/**
	 * 添加一个用户及其角色信息
	 * 
	 * @param commitUser
	 * @param roleIds
	 * @throws SQLException
	 */
	public void addUser(User commitUser, String[] roleIds) throws SQLException {

		// 使用事务
		Connection conn = DataSourceUtils.getConnection();
		conn.setAutoCommit(false);

		try {
			UserManageDao userManageDao = new UserManageDao();

			// 添加用户
			userManageDao.addUser(conn, commitUser);

			// 为用户添加角色
			userManageDao.updateUserRole(conn, commitUser, roleIds);

		} catch (SQLException e) {
			// 事务回滚
			DbUtils.rollback(conn);

			e.printStackTrace();
			throw new SQLException("添加失败!");
		} finally {

			// 提交事务
			try {
				DbUtils.commitAndClose(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("添加失败!");
			}
		}
	}

	/**
	 * 根据id删除一个用户信息
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteUserById(String id) throws SQLException {

		UserManageDao userManageDao = new UserManageDao();
		userManageDao.deleteUserById(id);
	}

	/**
	 * 通过ID获取一个用户信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User getUserById(String id) throws SQLException {

		UserManageDao userManageDao = new UserManageDao();
		try {
			return userManageDao.findUserById(id);
		} catch (SQLException e) {
			throw new SQLException("操作失败");
		}
	}

	/**
	 * 更新一个用户的信息
	 * 
	 * @param commitUser
	 * @param roleIds
	 * @throws SQLException
	 */
	public void updateUser(User commitUser, String[] roleIds)
			throws SQLException {

		// 使用session
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection();
			conn.setAutoCommit(false);// 开启事务

			// 更新用户信息
			UserManageDao userManageDao = new UserManageDao();
			userManageDao.updateUser(conn, commitUser);

			// 去除用户的所有角色
			userManageDao.deleteAllRoles(conn, commitUser);

			// 更新用户角色
			userManageDao.updateUserRole(conn, commitUser, roleIds);

		} catch (SQLException e) {
			// 事务回滚
			DbUtils.rollback(conn);

			e.printStackTrace();
			throw new SQLException("修改失败!");
		} finally {

			// 提交事务
			try {
				DbUtils.commitAndClose(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("修改失败!");
			}
		}
	}

}
