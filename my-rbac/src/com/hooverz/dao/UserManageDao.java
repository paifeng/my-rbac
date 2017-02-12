package com.hooverz.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hooverz.domain.Role;
import com.hooverz.domain.User;
import com.hooverz.utils.DataSourceUtils;
import com.hooverz.utils.EncryptionUtils;

public class UserManageDao {

	/**
	 * 获取到所有的用户信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> findAllUser() throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String sql = "select * from Users";

		List<User> dbUsers = runner.query(sql, new BeanListHandler<User>(
				User.class));

		if (dbUsers == null)
			throw new SQLException("未查询到结果");

		// 查权限
		String sql2 = "SELECT r.id,r.rolename,r.description FROM Users AS u,Roles AS r,UserRole AS ur WHERE u.id=ur.user_id AND r.id=ur.role_id AND u.id=?";
		for (User user : dbUsers) {
			List<Role> roles = runner.query(sql2, new BeanListHandler<Role>(
					Role.class), user.getId());
			user.setRoles(roles);
		}

		return dbUsers;
	}

	/**
	 * 添加一个用户
	 * 
	 * @param conn
	 * @param commitUser
	 * @throws SQLException
	 */
	public void addUser(Connection conn, User commitUser) throws SQLException {

		QueryRunner runner = new QueryRunner();

		String sql = "INSERT INTO Users (id,email,nickname,PASSWORD) VALUES (?,?,?,?)";

		int ret = runner.update(conn, sql, commitUser.getId(),
				commitUser.getEmail(), commitUser.getNickname(),
				EncryptionUtils.md5(commitUser.getPassword()));

		if (ret <= 0)
			throw new SQLException("添加用户失败");
	}

	/**
	 * 根据ID删除一个用户
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteUserById(String id) throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String sql = "delete from Users where id=?";

		int ret = runner.update(sql, id);

		if (ret <= 0)
			throw new SQLException("添加用户失败");
	}

	/**
	 * 根据一个ID获取到一个用户的信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User findUserById(String id) throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String sql = "select * from Users where id=?";
		User dbUser = runner.query(sql, new BeanHandler<User>(User.class), id);

		if (dbUser == null)
			throw new SQLException();

		return dbUser;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param conn
	 * @param commitUser
	 * @throws SQLException
	 */
	public void updateUser(Connection conn, User commitUser)
			throws SQLException {

		QueryRunner runner = new QueryRunner();

		String sql = "update Users set email=?,nickname=?,password=? where id=?";
		int ret = runner.update(conn, sql, commitUser.getEmail(),
				commitUser.getNickname(),
				EncryptionUtils.md5(commitUser.getPassword()),
				commitUser.getId());

		if (ret <= 0)
			throw new SQLException();
	}

	/**
	 * 更新用户的角色信息
	 * 
	 * @param conn
	 * @param commitUser
	 * @param roleIds
	 * @throws SQLException
	 */
	public void updateUserRole(Connection conn, User commitUser,
			String[] roleIds) throws SQLException {

		QueryRunner runner = new QueryRunner();

		String sql = "INSERT INTO UserRole (user_id,role_id) VALUES (?,?);";

		Object[][] params = new Object[roleIds.length][2];
		for (int i = 0; i < params.length; i++) {
			params[i][0] = commitUser.getId();
			params[i][1] = roleIds[i];
		}

		int[] ret = runner.batch(conn, sql, params);

		// 检测结果
		for (int i = 0; i < ret.length; i++) {
			if (ret[i] <= 0)
				throw new SQLException();
		}
	}

	/**
	 * 删除一个用户所有的角色信息
	 * 
	 * @param conn
	 * @param commitUser
	 * @throws SQLException
	 */
	public void deleteAllRoles(Connection conn, User commitUser)
			throws SQLException {
		QueryRunner runner = new QueryRunner();

		String sql = "delete from UserRole where user_id=?";

		runner.update(conn, sql, commitUser.getId());
	}
}
