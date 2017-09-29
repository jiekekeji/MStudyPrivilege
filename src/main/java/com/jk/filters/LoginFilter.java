package com.jk.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证过滤器
 * 
 * @author Handsome
 *
 */
public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 过滤逻辑
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hsRequest = (HttpServletRequest) request;
		String res = hsRequest.getRequestURI();
		System.out.println("访问资源:" + res);

	}

	public void destroy() {

	}

}
