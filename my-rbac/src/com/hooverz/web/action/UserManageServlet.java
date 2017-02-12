package com.hooverz.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.hooverz.domain.Role;
import com.hooverz.domain.User;
import com.hooverz.exception.ErrorOperationException;
import com.hooverz.exception.IillegalRequestException;
import com.hooverz.service.RoleService;
import com.hooverz.service.UserManageService;
import com.hooverz.utils.FfUtils;

public class UserManageServlet extends HttpServlet {

	private static final long serialVersionUID = -3048628672866151445L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");

		// 拿到action
		String action = request.getParameter("action");
		System.out.println(action);

		try {

			// 添加权限控制
			//PrivilegeUtils.checkPrivilege(request);

			if (action.equals("listuser")) {
				listUser(request, response);
			} else if (action.equals("adduser")) {
				addUser(request, response);
			} else if (action.equals("adduserUI")) {
				addUserUI(request, response);
			} else if (action.equals("deleteuser")) {
				deleteUser(request, response);
			} else if (action.equals("updateuserUI")) {
				updateUserUI(request, response);
			} else if (action.equals("updateuser")) {
				updateUser(request, response);
			} else {
				throw new IillegalRequestException("");
			}
		} catch (IillegalRequestException e) {

			// 非法请求
		} catch (NullPointerException e) {
			// 非法请求
		} catch (ErrorOperationException e) {
			returnErrorMsg(request, response, e.getMessage());
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws ErrorOperationException
	 */
	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			ErrorOperationException {

		// 封装数据
		User commitUser = new User();
		Map<String, String[]> param = request.getParameterMap();

		try {
			BeanUtils.populate(commitUser, param);

			String[] roleIds = request.getParameterValues("roleid");

			if (roleIds == null)
				throw new ErrorOperationException("用户至少需要一个角色!");

			UserManageService userManageService = new UserManageService();
			userManageService.updateUser(commitUser, roleIds);

			// 修改成功
			response.sendRedirect(request.getContextPath()
					+ "/page/usermanage.jsp");

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			returnErrorMsg(request, response, e.getMessage());
		}

	}

	/**
	 * 返回添加用户的表单UI
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addUserUI(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		// 获取到所有的角色
		RoleService roleService = new RoleService();
		try {
			List<Role> roles = roleService.getAllRoles();

			request.setAttribute("roles", roles);
			request.setAttribute("action.name", "adduser");
			request.setAttribute("action", "添加");
			request.getRequestDispatcher("/page/userform.jsp").forward(request,
					response);
		} catch (SQLException e) {

			// 查询失败
			returnErrorMsg(request, response, e.getMessage());
			// e.printStackTrace();
		}
	}

	/**
	 * 返回用户表单UI
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateUserUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		try {

			if (id == null)
				throw new NullPointerException("非法参数!");

			// 根据id得到用户信息
			UserManageService userManageService = new UserManageService();
			User dbUser = userManageService.getUserById(id);

			// 获取到所有的角色
			RoleService roleService = new RoleService();

			List<Role> roles = roleService.getAllRoles();

			request.setAttribute("user", dbUser);
			request.setAttribute("roles", roles);

			// 成功
			request.setAttribute("action.name", "updateuser");
			request.setAttribute("action", "修改");
			request.getRequestDispatcher("/page/userform.jsp").forward(request,
					response);
		} catch (SQLException e) {
			// 操作失败
			returnErrorMsg(request, response, "异常!");

		} catch (NullPointerException e) {
			// 非法请求

		}
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			UserManageService userManageService = new UserManageService();
			userManageService.deleteUserById(id);

			// 删除成功
			listUser(request, response);
		} catch (NullPointerException e) {
			// 非法请求
		} catch (SQLException e) {
			// e.printStackTrace();
			// 删除用户失败
			returnErrorMsg(request, response, "删除用户失败!");
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws ErrorOperationException
	 */
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ErrorOperationException {

		// 封装数据
		User commitUser = new User();
		Map<String, String[]> param = request.getParameterMap();

		try {
			BeanUtils.populate(commitUser, param);
			// 封装ID
			commitUser.setId(FfUtils.getRandomId(String.valueOf(System
					.currentTimeMillis()) + commitUser.getEmail()));

			// 拿到角色
			String[] roleIds = request.getParameterValues("roleid");

			if (roleIds == null)
				throw new ErrorOperationException("用户至少需要一个角色!");

			UserManageService userManageService = new UserManageService();
			userManageService.addUser(commitUser, roleIds);

			// 添加成功
			response.sendRedirect(request.getContextPath()
					+"/page/usermanage.jsp");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// 添加失败
			returnErrorMsg(request, response, "添加用户失败!");
		}
	}

	/**
	 * 列出所有的User
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserManageService userManageService = new UserManageService();
		try {
			List<User> dbUsers = userManageService.getAllUser();

			request.setAttribute("userlist", dbUsers);
			request.getRequestDispatcher("/page/userlist.jsp").forward(request,
					response);

		} catch (SQLException e) {

			// 转发失败页面
			returnErrorMsg(request, response, "操作失败");
		}
	}

	/**
	 * 返回错误信息
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void returnErrorMsg(HttpServletRequest request,
			HttpServletResponse response, String mgs) throws ServletException,
			IOException {
		request.setAttribute("msg-error", mgs);
		request.getRequestDispatcher("/page/msg.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
