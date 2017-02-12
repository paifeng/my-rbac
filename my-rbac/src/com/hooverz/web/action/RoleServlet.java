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

import com.hooverz.domain.Privilege;
import com.hooverz.domain.Role;
import com.hooverz.exception.ErrorOperationException;
import com.hooverz.exception.IillegalRequestException;
import com.hooverz.exception.PrivilegeException;
import com.hooverz.service.PrivilegeService;
import com.hooverz.service.RoleService;

public class RoleServlet extends HttpServlet {

	private static final long serialVersionUID = -711435827330541501L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");

		// 拿到action
		String action = request.getParameter("action");
		System.out.println(action);

		try {

			// 权限控制
			// PrivilegeUtils.checkPrivilege(request);

			if (action.equals("listrole")) {
				listRole(request, response);
			} else if (action.equals("addroleUI")) {
				addRoleUI(request, response);
			} else if (action.equals("addrole")) {
				addRole(request, response);
			} else if (action.equals("deleterole")) {
				deleteRole(request, response);
			} else if (action.equals("updateroleUI")) {
				updateRoleUI(request, response);
			} else if (action.equals("updaterole")) {
				updateRole(request, response);
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
	 * 更新角色
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws ErrorOperationException
	 */
	private void updateRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			ErrorOperationException {
		// 封装数据
		Role commitRole = new Role();
		Map<String, String[]> param = request.getParameterMap();

		try {
			BeanUtils.populate(commitRole, param);

			String[] privilegeIds = request.getParameterValues("privilegeid");
			if (privilegeIds == null)
				throw new ErrorOperationException("角色至少需要一个权限!");

			RoleService roleService = new RoleService();
			roleService.updateRole(commitRole, privilegeIds);

			// 修改成功
			response.sendRedirect(request.getContextPath()
					+ "/page/rolemanage.jsp");

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// e.printStackTrace();

			returnErrorMsg(request, response, e.getMessage());
		}
	}

	/**
	 * 更新角色UI
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws PrivilegeException
	 */
	private void updateRoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id == null)
			throw new NullPointerException();

		// 查找到要修改的角色的信息
		RoleService roleService = new RoleService();

		// 查找到所有的权限信息
		PrivilegeService privilegeService = new PrivilegeService();
		try {

			Role role = roleService.getRoleById(id);

			List<Privilege> privileges = privilegeService.getAllPrivileges();

			if (privileges.size() == 0)
				throw new SQLException("还没有可以使用的权限，请先完善权限!");

			request.setAttribute("role", role);
			request.setAttribute("privileges", privileges);
			request.setAttribute("action.name", "updaterole");
			request.setAttribute("action", "更新");
			request.getRequestDispatcher("/page/roleform.jsp").forward(request,
					response);
		} catch (SQLException e) {
			returnErrorMsg(request, response, e.getMessage());
		}
	}

	/**
	 * 删除指定的用户
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null)
			throw new NullPointerException();

		RoleService roleService = new RoleService();
		try {
			roleService.deleteRoleById(id);

			// 成功
			listRole(request, response);
		} catch (SQLException e) {
			// e.printStackTrace();
			returnErrorMsg(request, response, "删除角色失败!");
		}
	}

	/**
	 * 添加角色
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws ErrorOperationException
	 */
	private void addRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ErrorOperationException {

		// 封装数据
		Map<String, String[]> commitMap = request.getParameterMap();
		Role commitRole = new Role();
		try {
			BeanUtils.populate(commitRole, commitMap);

			// 获取权限数据
			String[] privilegeids = request.getParameterValues("privilegeid");

			if (privilegeids == null)
				throw new ErrorOperationException("角色至少需要一个权限!");

			// 添加角色
			RoleService roleService = new RoleService();
			roleService.addRoleAndPrivileges(commitRole, privilegeids);

			response.sendRedirect(request.getContextPath()
					+ "/page/rolemanage.jsp");

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// e.printStackTrace();
			returnErrorMsg(request, response, "添加角色失败!");
		}
	}

	/**
	 * 添加角色UI
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws PrivilegeException
	 */
	private void addRoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 查找到所有的权限信息
		PrivilegeService privilegeService = new PrivilegeService();
		try {
			List<Privilege> privileges = privilegeService.getAllPrivileges();

			// 成功
			request.setAttribute("privileges", privileges);
			request.setAttribute("action", "添加角色");
			request.setAttribute("action.name", "addrole");
			request.getRequestDispatcher("/page/roleform.jsp").forward(request,
					response);
		} catch (SQLException e) {
			// e.printStackTrace();
			// 失败
			returnErrorMsg(request, response, "异常!");
		}
	}

	/**
	 * 查找出所有的角色信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RoleService roleService = new RoleService();
		try {
			List<Role> roles = roleService.getAllRoles();

			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/page/rolelist.jsp").forward(request,
					response);

		} catch (SQLException e) {
			// e.printStackTrace();
			returnErrorMsg(request, response, "异常!");
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
