package com.hooverz.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.hooverz.dao.RoleDao;
import com.hooverz.domain.Role;
import com.hooverz.utils.DataSourceUtils;

public class RoleService {

	/**
	 * 获取到所有的角色信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Role> getAllRoles() throws SQLException {

		RoleDao roleDao = new RoleDao();

		try {
			return roleDao.findAllRoles();
		} catch (SQLException e) {

			e.printStackTrace();
			throw new SQLException("获取操作失败!");
		}
	}

	/**
	 * 添加角色和角色的权限
	 * 
	 * @param commitRole
	 * @param privilegeids
	 * @throws SQLException
	 */
	public void addRoleAndPrivileges(Role commitRole, String[] privilegeids)
			throws SQLException {

		// 使用session
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection();
			conn.setAutoCommit(false); // 开启事务

			RoleDao roleDao = new RoleDao();

			// 添加角色
			roleDao.addRole(conn, commitRole);

			// 拿到id
			int id = roleDao.getIdentity(conn);

			// 设置id
			commitRole.setId(id);

			// 删除角色的所有权限
			// roleDao.deleteAllPrivileges(conn, commitRole);

			// 添加角色的权限
			roleDao.addPrivileges(conn, commitRole, privilegeids);

		} catch (SQLException e) {
			e.printStackTrace();
			// 回滚
			try {
				DbUtils.rollback(conn);
			} catch (SQLException e1) {
				throw new SQLException("添加角色失败!");
			}
			throw new SQLException("添加角色失败!");
		} finally {

			// 提交事务
			try {
				DbUtils.commitAndClose(conn);
			} catch (SQLException e) {
				throw new SQLException("添加角色失败!");
			}
		}
	}

	/**
	 * 根据id删除一个角色
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRoleById(String id) throws SQLException {

		RoleDao roleDao = new RoleDao();

		roleDao.deleteRoleById(id);
	}

	public Role getRoleById(String id) throws SQLException {

		RoleDao roleDao = new RoleDao();
		Role dbRole = null;
		try {
			dbRole = roleDao.findRoleById(id);

		} catch (SQLException e) {
			throw new SQLException("获取角色信息失败!");
		}
		return dbRole;
	}

	/**
	 * 更新角色和角色所对应的权限
	 * 
	 * @param commitRole
	 * @param privilegeIds
	 * @throws SQLException
	 */
	public void updateRole(Role commitRole, String[] privilegeIds)
			throws SQLException {
		// 使用session
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection();
			conn.setAutoCommit(false);// 开启事务

			// 更新角色信息
			RoleDao roleDao = new RoleDao();
			roleDao.updateRole(conn, commitRole);

			// 去除角色的所有权限
			roleDao.deleteAllPrivileges(conn, commitRole);

			// 更新角色权限
			roleDao.addPrivileges(conn, commitRole, privilegeIds);

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
