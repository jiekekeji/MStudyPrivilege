package com.jk.filters;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jk.pojo.tb.TAdmin;
import com.jk.pojo.tb.TResources;
import com.jk.service.AdminService;

/**
 * 登录验证过滤器
 * 
 * @author Handsome
 *
 */
public class LoginFilter extends HandlerInterceptorAdapter {

	@Autowired
	private AdminService adminService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===preHandle===");
		String path = request.getServletPath();
		System.out.println("访问的路径:" + path);
		TAdmin admin = (TAdmin) request.getSession().getAttribute("admin");
		if (null == admin) {
			response.sendRedirect(request.getContextPath() + "/admin/nologin");
			return false;
		}

		Map<String, Object> map = adminService.selectTResourcesByAdminId(admin.getId());
		List<TResources> resources = (List<TResources>) map.get("resources");
		for (TResources tResources : resources) {
			if (path.equalsIgnoreCase(tResources.getUrl())) {
				return true;
			}
		}

		response.sendRedirect(request.getContextPath() + "/admin/noauthority");
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===postHandle===");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("===afterCompletion===");
		super.afterCompletion(request, response, handler, ex);
	}
}
