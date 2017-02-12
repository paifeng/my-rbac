package com.hooverz.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.hooverz.domain.User;
import com.hooverz.exception.IillegalRequestException;
import com.hooverz.service.UserService;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 2380884300103142653L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");

		// 拿到action
		String action = request.getParameter("action");

		try {

			if (action.equals("login")) {
				login(request, response);
			} else if (action.equals("exit")) {
				exit(request, response);
			} else {
				throw new IillegalRequestException("");
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IillegalRequestException e) {
			// 非法请求
		} catch (NullPointerException e) {
			// 非法请求
		}
	}

	/**
	 * 退出登录
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("loginUser");

		request.setAttribute("msg-notice", "退出成功!");
		request.getRequestDispatcher("/page/msg.jsp")
				.forward(request, response);
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException,
			IOException, ServletException {
		// 封装数据
		User commitUser = new User();
		Map<String, String[]> param = request.getParameterMap();

		BeanUtils.populate(commitUser, param);

		// 登陆
		UserService userService = new UserService();
		User dbUser;
		try {
			dbUser = userService.login(commitUser);

			// 登陆成功，将登陆后的用户信息放到session中去
			request.getSession().setAttribute("loginUser", dbUser);
			// 跳转到成功页面
			response.sendRedirect(request.getContextPath() + "/");
		} catch (SQLException e) {
			// 登陆失败，拿到失败信息
			/*
			 * Map<String, String> errMsg = userService.getErrMsg();
			 * request.setAttribute("errMsg", errMsg);
			 */
			request.getRequestDispatcher("/page/login.jsp").forward(request,
					response);
			// e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
