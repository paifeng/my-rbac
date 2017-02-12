package com.hooverz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hooverz.exception.PrivilegeException;
import com.hooverz.privilege.PrivilegeUtils;

public class PrivilegeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		System.out.println("过滤!");

		// 强制转换类型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		
		//chain.doFilter(req, res);

		// 权限控制
		///*
		try {
			PrivilegeUtils.checkPrivilege(req);

			// 放行
			chain.doFilter(req, res);
		} catch (PrivilegeException e) {
			e.printStackTrace();

			// 返回错误页面
			returnErrorMsg(req, res, e.getMessage());
		}
		//*/
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

	@Override
	public void destroy() {

	}

}
