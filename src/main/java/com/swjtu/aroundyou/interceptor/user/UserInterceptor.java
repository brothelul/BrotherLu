package com.swjtu.aroundyou.interceptor.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		System.out.println(request.getRequestURL().toString());
		if (session.getAttribute("userInfo") == null) {
			response.sendRedirect("index.jsp");
			return false;
		}
		return true;
	}
}
