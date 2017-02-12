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
import com.hooverz.exception.IillegalRequestException;
import com.hooverz.exception.PrivilegeException;
import com.hooverz.service.PrivilegeService;

public class PrivilegeServlet extends HttpServlet {

	private static final long serialVersionUID = 2397024635136330760L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		// 拿到action
		String action = request.getParameter("action");
		System.out.println(action);

		try {

			// 权限控制
			//PrivilegeUtils.checkPrivilege(request);

			if (action.equals("listprivileges")) {
				listPrivileges(request, response);
			} else if (action.equals("deleteprivilege")) {
				deletePrivilege(request, response);
			} else if (action.equals("updateprivilegeUI")) {
				updatePrivilegeUI(request, response);
			} else if (action.equals("updateprivilege")) {
				updatePrivilege(request, response);
			} else if (action.equals("addprivilegeUI")) {
				addPrivilegeUI(request, response);
			} else if (action.equals("addprivilege")) {
				addPrivilege(request, response);
			} else {
				throw new IillegalRequestException("");
			}
		} catch (IillegalRequestException e) {

			// 非法请求
		} catch (NullPointerException e) {
			// 空指针
		} catch (PrivilegeException e) {
			// 权限异常
			//e.printStackTrace();
			returnErrorMsg(request, response, e.getMessage());
		}
	}

	/**
	 * 添加权限信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws PrivilegeException
	 */
	private void addPrivilege(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			PrivilegeException {

		// 封装数据
		Map<String, String[]> commitMap = request.getParameterMap();
		Privilege commitPrivilege = new Privilege();
		try {
			BeanUtils.populate(commitPrivilege, commitMap);

			// 添加角色
			PrivilegeService privilegeService = new PrivilegeService();
			privilegeService.addPrivileges(commitPrivilege);

			response.sendRedirect(request.getContextPath()
					+ "/page/privilegemanage.jsp");

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			returnErrorMsg(request, response, "添加权限失败!");
		}
	}

	/**
	 * 添加权限UI
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 * @throws PrivilegeException
	 */
	private void addPrivilegeUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			PrivilegeException {

		request.setAttribute("action", "添加权限");
		request.setAttribute("action.name", "addprivilege");
		request.getRequestDispatcher("/page/privilegeform.jsp").forward(
				request, response);
	}

	/**
	 * 更新权限信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws PrivilegeException
	 */
	private void updatePrivilege(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			PrivilegeException {

		// 封装数据
		Privilege commitPrivilege = new Privilege();
		Map<String, String[]> param = request.getParameterMap();

		try {
			BeanUtils.populate(commitPrivilege, param);

			PrivilegeService privilegeService = new PrivilegeService();
			privilegeService.updatePrivilege(commitPrivilege);

			// 修改成功
			response.sendRedirect(request.getContextPath()
					+ "/page/privilegemanage.jsp");

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			returnErrorMsg(request, response, e.getMessage());
		}
	}

	/**
	 * 删除权限UI
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws IillegalRequestException
	 * @throws PrivilegeException
	 */
	private void updatePrivilegeUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			IillegalRequestException, PrivilegeException {

		String id = request.getParameter("id");
		if (id == null)
			throw new IillegalRequestException("");

		// 查找到要修改的权限的信息
		PrivilegeService privilegeService = new PrivilegeService();
		try {

			Privilege privilege = privilegeService.getPrivilegeById(id);

			request.setAttribute("privilege", privilege);
			request.setAttribute("action.name", "updateprivilege");
			request.setAttribute("action", "更新权限");
			request.getRequestDispatcher("/page/privilegeform.jsp").forward(
					request, response);
		} catch (SQLException e) {
			request.setAttribute("msg-error", e.getMessage());
			request.getRequestDispatcher("/page/msg.jsp").forward(request,
					response);
		}
	}

	/**
	 * 根据id删除一个权限
	 * 
	 * @param request
	 * @param response
	 * @throws IillegalRequestException
	 * @throws IOException
	 * @throws ServletException
	 * @throws PrivilegeException
	 */
	private void deletePrivilege(HttpServletRequest request,
			HttpServletResponse response) throws IillegalRequestException,
			ServletException, IOException, PrivilegeException {

		String id = request.getParameter("id");

		if (id == null)
			throw new IillegalRequestException("");

		PrivilegeService privilegeService = new PrivilegeService();
		try {

			privilegeService.deleteById(id);

			// 成功
			listPrivileges(request, response);
		} catch (SQLException e) {
			// e.printStackTrace();
			returnErrorMsg(request, response, e.getMessage());
		}
	}

	/**
	 * 列出所有的权限信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws PrivilegeException
	 */
	private void listPrivileges(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			PrivilegeException {

		// 获取所有的权限
		PrivilegeService privilegeService = new PrivilegeService();
		try {
			List<Privilege> privileges = privilegeService.getAllPrivileges();

			request.setAttribute("privileges", privileges);
			request.getRequestDispatcher("/page/privilegelist.jsp").forward(
					request, response);
		} catch (SQLException e) {
			// e.printStackTrace();
			returnErrorMsg(request, response, e.getMessage());
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
