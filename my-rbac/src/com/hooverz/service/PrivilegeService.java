package com.hooverz.service;

import java.sql.SQLException;
import java.util.List;

import com.hooverz.dao.PrivilegeDao;
import com.hooverz.domain.Privilege;
import com.hooverz.exception.PrivilegeException;

public class PrivilegeService {

	public List<Privilege> getAllPrivileges() throws SQLException {

		PrivilegeDao privilegeDao = new PrivilegeDao();
		try {

			return privilegeDao.findAllprivileges();
		} catch (SQLException e) {
			throw new SQLException("获取权限信息失败");
		}
	}

	/**
	 * 根据id删除一个权限
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws PrivilegeException
	 */
	public void deleteById(String delId) throws SQLException {

		PrivilegeDao privilegeDao = new PrivilegeDao();
		try {

			privilegeDao.deleteById(delId);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new SQLException("删除权限失败!");
		}
	}

	/**
	 * 根据id获取到权限信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws PrivilegeException
	 */
	public Privilege getPrivilegeById(String id) throws SQLException {

		PrivilegeDao privilegeDao = new PrivilegeDao();
		Privilege dbPrivilege = null;
		try {

			dbPrivilege = privilegeDao.findPrivilegeById(id);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new SQLException("异常!");
		}

		return dbPrivilege;
	}

	/**
	 * 修改权限信息
	 * 
	 * @param commitPrivilege
	 * @throws SQLException
	 * @throws PrivilegeException
	 */
	public void updatePrivilege(Privilege commitPrivilege) throws SQLException {

		PrivilegeDao privilegeDao = new PrivilegeDao();
		try {

			privilegeDao.updatePrivilege(commitPrivilege);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new SQLException("更新失败");
		}
	}

	/**
	 * 添加权限
	 * 
	 * @param commitPrivilege
	 * @throws SQLException
	 * @throws PrivilegeException
	 */
	public void addPrivileges(Privilege commitPrivilege) throws SQLException {

		PrivilegeDao privilegeDao = new PrivilegeDao();
		try {

			privilegeDao.addPrivilege(commitPrivilege);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new SQLException("添加权限失败!");
		}
	}

}
