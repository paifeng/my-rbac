package com.hooverz.privilege;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hooverz.domain.User;
import com.hooverz.exception.PrivilegeException;
import com.hooverz.utils.DataSourceUtils;

public class PrivilegeUtils {

	/**
	 * 权限控制
	 * 
	 * @param request
	 * @throws PrivilegeException
	 */
	public static void checkPrivilege(HttpServletRequest request)
			throws PrivilegeException {

		// 拿到登录后的用户对象
		User loginUser = (User) request.getSession(true).getAttribute(
				"loginUser");

		// 拿到requestUrl
		String requestUrl = request.getRequestURI();

		// 拿到requestAction
		String requestAction = request.getParameter("action");
		if (requestAction == null)
				requestAction = "noaction";
		
		System.out.println("requestUrl: " + requestUrl);
		System.out.println("requestAction: " + requestAction);

		try {
			// 未登录,去权限表查看当前连接是否能在未登录的情况下打开
			if (loginUser == null) {
				isAllowNoLogin(requestUrl,requestAction);
			} else {
				// 登录情况,分action为空和action不为空的情况
				isRoleAllow(loginUser, requestUrl, requestAction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 是否能在未登录的情况下访问
	 * 
	 * @param requestUrl
	 * @throws SQLException
	 * @throws PrivilegeException
	 */
	private static void isAllowNoLogin(String requestUrl,String requestAction) throws SQLException,
			PrivilegeException {

		String sql = "select count(*) from PRIVILEGES where privilegeUrl=? and requestAction=? and nologin=1";

		DataSource dataSource = DataSourceUtils.getDataSource();
		QueryRunner runner = new QueryRunner(dataSource);

		Long ret = (Long) runner.query(sql, new ScalarHandler(),requestUrl,requestAction);

		System.out.println("ret: " + ret);
		if (ret < 1)
			throw new PrivilegeException("请先登录!");
	}

	/**
	 * 查看当前角色是否能访问
	 * 
	 * @param loginUser
	 * @param requestUrl
	 * @param requestAction
	 * @throws PrivilegeException
	 * @throws SQLException
	 */
	private static void isRoleAllow(User loginUser, String requestUrl,
			String requestAction) throws PrivilegeException, SQLException {

		// 去数据库查找此url和action对于的角色有哪些
		String sql = "SELECT r.id FROM Roles AS r,PRIVILEGES AS p,RolePrivilege AS rp WHERE p.privilegeUrl=? AND p.requestaction=? AND p.id=rp.privilege_id AND rp.role_id=r.id";

		DataSource dataSource = DataSourceUtils.getDataSource();
		QueryRunner runner = new QueryRunner(dataSource);

		// 所有的复合角色
		List<Object> rightRoleIds = runner.query(sql, new ColumnListHandler(1),
				requestUrl, requestAction);

		if (rightRoleIds != null) {
			// 查找此用户的所有角色
			String sql2 = "SELECT r.id FROM Users AS u,Roles AS r,UserRole AS ur WHERE u.id=ur.user_id AND r.id=ur.role_id AND u.id=?";
			List<Object> userRoleIds = runner.query(sql2,
					new ColumnListHandler(1), loginUser.getId());

			System.out.println("loginUser : " + loginUser);
			System.out.println("requestUrl : " + requestUrl);
			System.out.println("rightRoleIds : " + rightRoleIds);
			System.out.println("userRoleIds : " + userRoleIds);
			System.out.println("requestAction : " + requestAction);

			// 比对此用户中是否有包含查找到的角色
			rightRoleIds.retainAll(userRoleIds); // 取交集
			if (rightRoleIds.size() <= 0)
				throw new PrivilegeException("权限不足!");
		}

	}
}
