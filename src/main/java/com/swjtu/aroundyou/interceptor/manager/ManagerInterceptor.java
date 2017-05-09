package com.swjtu.aroundyou.interceptor.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ManagerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
	    HttpSession session = request.getSession();
		if (url.contains("manager")) {
			if (session.getAttribute("managerInfo")==null) {
				response.sendRedirect("login.jsp");
				return false;
			}
		}
		return true;
	}
}
