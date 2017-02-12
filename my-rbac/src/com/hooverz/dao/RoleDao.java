package com.hooverz.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hooverz.domain.Privilege;
import com.hooverz.domain.Role;
import com.hooverz.utils.DataSourceUtils;

public class RoleDao {

	/**
	 * 获取到所有的角色信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Role> findAllRoles() throws SQLException {

		DataSource dataSource = DataSourceUtils.getDataSource();

		QueryRunner runner = new QueryRunner(dataSource);

		String sql = "SELECT * FROM Roles;";

		// 查找所有的角色信息
		List<Role> roles = runner.query(sql, new BeanListHandler<Role>(
				Role.class));

		if (roles == null)
			throw new SQLException("没有任何可用的角色!请先添加角色信息");

		// 查找每个角色的权限
		String sql2 = "SELECT p.id,p.privilegename,p.description FROM Roles AS r,PRIVILEGES AS p,RolePrivilege AS rp WHERE r.id=rp.role_id AND p.id=rp.privilege_id AND r.id=?";
		for (int i = 0; i < roles.size(); i++) {
			List<Privilege> privileges = runner.query(sql2,
					new BeanListHandler<Privilege>(Privilege.class),
					roles.get(i).getId());
			roles.get(i).setPrivileges(privileges);
		}

		return roles;
	}

	/**
	 * 添加角色
	 * 
	 * @param conn
	 * @param commitRole
	 * @throws SQLException
	 */
	public void addRole(Connection conn, Role commitRole) throws SQLException {
		String sql = "INSERT INTO Roles (rolename,description) VALUES (?,?)";

		QueryRunner runner = new QueryRunner();

		int ret = runner.update(conn, sql, commitRole.getRolename(),
				commitRole.getDescription());

		if (ret <= 0)
			throw new SQLException();
	}

	/**
	 * 删除对于角色的所有的权限
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public void deleteAllPrivileges(Connection conn, Role commitRole)
			throws SQLException {
		String sql = "delete from RolePrivilege where role_id=?";

		QueryRunner runner = new QueryRunner();

		runner.update(conn, sql, commitRole.getId());
	}

	/**
	 * 为对应的角色添加权限
	 * 
	 * @param conn
	 * @param privilegeids
	 * @throws SQLException
	 */
	public void addPrivileges(Connection conn, Role commitRole,
			String[] privilegeids) throws SQLException {

		String sql = "INSERT INTO RolePrivilege (role_id,privilege_id) VALUES (?,?);";

		QueryRunner runner = new QueryRunner();

		Object[][] params = new Object[privilegeids.length][2];
		// 初始化数据
		for (int i = 0; i < params.length; i++) {
			params[i][0] = commitRole.getId();
			params[i][1] = privilegeids[i];
		}

		int[] ret = runner.batch(conn, sql, params);

		// 检测结果
		for (int i = 0; i < ret.length; i++) {
			if (ret[i] <= 0)
				throw new SQLException();
		}
	}

	/**
	 * 在使用事务时，获取到刚刚插入的自增长字段的值(id),前提是要使用同一个conn,且id是自增长的
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public int getIdentity(Connection conn) throws SQLException {

		String sql = "select @@identity";

		QueryRunner runner = new QueryRunner();
		BigInteger ret = (BigInteger) runner.query(conn, sql,
				new ScalarHandler());

		if (ret == null)
			throw new SQLException("空值");

		return ret.intValue();
	}

	/**
	 * 根据id删除一个用户
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRoleById(String id) throws SQLException {

		String sql = "delete from Roles where id=?";

		DataSource dataSource = DataSourceUtils.getDataSource();
		QueryRunner runner = new QueryRunner(dataSource);

		int ret = runner.update(sql, id);

		if (ret <= 0)
			throw new SQLException();
	}

	public Role findRoleById(String id) throws SQLException {

		String sql = "select * from Roles where id=?";

		DataSource dataSource = DataSourceUtils.getDataSource();
		QueryRunner runner = new QueryRunner(dataSource);

		Role dbRole = runner.query(sql, new BeanHandler<Role>(Role.class), id);

		if (dbRole == null)
			throw new SQLException();

		return dbRole;
	}

	/**
	 * 更新角色,带事务
	 * 
	 * @param conn
	 * @param commitRole
	 * @throws SQLException
	 */
	public void updateRole(Connection conn, Role commitRole)
			throws SQLException {

		QueryRunner runner = new QueryRunner();

		String sql = "update Roles set rolename=?,description=? where id=?";
		int ret = runner.update(conn, sql, commitRole.getRolename(),
				commitRole.getDescription(), commitRole.getId());

		if (ret <= 0)
			throw new SQLException();
	}
}
