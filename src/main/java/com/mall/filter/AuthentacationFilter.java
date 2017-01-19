package com.mall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mall.services.UserService;

public class AuthentacationFilter implements Filter {

	WebApplicationContext context;

	FilterConfig fc;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		fc = filterConfig;
		context = WebApplicationContextUtils.getWebApplicationContext(fc.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// getUser info by userName.
		// compare userPasswoed in HttpRequest is equals password in DB

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURI();

		if ((!"/mall/users".equals(url) && !"POST".equals(httpRequest.getMethod()))
				|| (!"/mall/users".equals(url) && !"GET".equals(httpRequest.getMethod()))) {
			String token = httpRequest.getHeader("token");
			if (token != null && !"".equals(token)) {
				UserService userService = (UserService) context.getBean("userService");
				boolean isTokenValid = userService.validateToken(token);
				if (!isTokenValid) {
					httpResponse.sendError(401, "username or password is not right");
				}

			} else {
				httpResponse.sendError(401, "username or password is not right");
			}

			// UserService us = (UserService)context.getBean("userServiceImpl");

		}

		UserService.updateTokenTime();
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
