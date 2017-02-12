package com.hooverz.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hooverz.domain.Privilege;
import com.hooverz.utils.DataSourceUtils;

public class PrivilegeDao {

	/**
	 * 获取到所有的权限信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Privilege> findAllprivileges() throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String sql = "SELECT * FROM PRIVILEGES;";
		List<Privilege> privileges = runner.query(sql,
				new BeanListHandler<Privilege>(Privilege.class));

		if (privileges == null)
			throw new SQLException();

		return privileges;
	}

	/**
	 * 根据id删除一个权限
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteById(String id) throws SQLException {

		String sql = "delete from PRIVILEGES where id=?";

		DataSource dataSource = DataSourceUtils.getDataSource();
		QueryRunner runner = new QueryRunner(dataSource);

		int ret = runner.update(sql, id);

		if (ret <= 0)
			throw new SQLException();
	}

	/**
	 * 根据id获取权限信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Privilege findPrivilegeById(String id) throws SQLException {

		String sql = "select * from PRIVILEGES where id=?";

		DataSource dataSource = DataSourceUtils.getDataSource();
		QueryRunner runner = new QueryRunner(dataSource);

		Privilege dbPrivilege = runner.query(sql, new BeanHandler<Privilege>(
				Privilege.class), id);

		if (dbPrivilege == null)
			throw new SQLException();

		return dbPrivilege;
	}

	/**
	 * 更新权限信息
	 * 
	 * @param commitPrivilege
	 * @throws SQLException
	 */
	public void updatePrivilege(Privilege commitPrivilege) throws SQLException {

		System.out.println(commitPrivilege);

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String nologin = "0";
		if ("1".equals(commitPrivilege.getNologin())) {
			nologin = "1";
		}
		String sql = "update PRIVILEGES set privilegename=?,privilegeUrl=?,requestaction=?,nologin=?,description=? where id=?";
		int ret = runner.update(sql, commitPrivilege.getPrivilegename(),
				commitPrivilege.getPrivilegeUrl(),
				commitPrivilege.getRequestaction(), nologin,
				commitPrivilege.getDescription(), commitPrivilege.getId());

		if (ret <= 0)
			throw new SQLException();
	}

	/**
	 * 添加权限
	 * 
	 * @param commitPrivilege
	 * @throws SQLException
	 */
	public void addPrivilege(Privilege commitPrivilege) throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		String sql = "INSERT INTO PRIVILEGES (privilegename,privilegeUrl,requestaction,nologin,description) VALUES (?,?,?,?,?)";

		QueryRunner runner = new QueryRunner(dataSource);

		String nologin = "0";
		if ("1".equals(commitPrivilege.getNologin())) {
			nologin = "1";
		}
		int ret = runner.update(sql, commitPrivilege.getPrivilegename(),
				commitPrivilege.getPrivilegeUrl(),
				commitPrivilege.getRequestaction(), nologin,
				commitPrivilege.getDescription());

		if (ret <= 0)
			throw new SQLException();
	}
}
