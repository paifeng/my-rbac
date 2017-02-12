package com.hooverz.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JdbcUtils {

	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	static {

		// 初始化数据
		ResourceBundle bundle = ResourceBundle.getBundle("jdbcConf");
		URL = bundle.getString("url");
		USERNAME = bundle.getString("userName");
		PASSWORD = bundle.getString("password");
		DRIVER = bundle.getString("driver");

		try {
			// 注册驱动
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return 返回数据库连接对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	/**
	 * 关闭结果集
	 * 
	 * @param set
	 *            结果集
	 */
	public static void closeResultSet(ResultSet set) {
		try {
			if (set != null)
				set.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param st
	 *            Statement对象
	 */
	public static void closeStatement(Statement st) {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接器
	 * 
	 * @param conn
	 *            Connection对象
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
