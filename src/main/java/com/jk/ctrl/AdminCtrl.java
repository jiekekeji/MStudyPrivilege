package com.jk.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.service.AdminService;
import com.jk.utils.TxUtils;

@Controller
@RequestMapping("/admin")
public class AdminCtrl {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/add")
	@ResponseBody
	public Object add(String name, String password, String remarks, String phone, String qq, String[] roleids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(password) || TxUtils.isEmpty(remarks) || TxUtils.isEmpty(phone)
				|| TxUtils.isEmpty(qq) || null == roleids) {
			map.put("code", "error");
			map.put("desc", "有些参数为空!");
			return map;
		}
		if (name.length() > 24 || remarks.length() > 255 || phone.length() != 11 || qq.length() > 20
				|| password.length() > 24 || password.length() < 8) {
			map.put("code", "error");
			map.put("desc", "有些参数长度过长!");
			return map;
		}
		try {
			map = adminService.add(name, password, remarks, phone, qq, roleids);
			return map;
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}

	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object deleteByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return adminService.deleteByID(id);
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object udapteByID(String id, String name, String remarks, String phone, String qq, String[] roleids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || TxUtils.isEmpty(phone)
				|| TxUtils.isEmpty(qq)) {
			map.put("code", "error");
			map.put("desc", "有些参数为空!");
			return map;
		}
		if (id.length() != 32 || name.length() > 24 || remarks.length() > 255 || phone.length() > 11
				|| qq.length() > 20) {
			map.put("code", "error");
			map.put("desc", "有些参数长度过长!");
			return map;
		}
		try {
			return adminService.udapteByID(id, name, remarks, phone, qq, roleids);
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/psdmdf")
	@ResponseBody
	public Object udapteByID(String id, String npassword, String opassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || TxUtils.isEmpty(npassword) || TxUtils.isEmpty(opassword)) {
			map.put("code", "error");
			map.put("desc", "有些参数为空!");
			return map;
		}
		if (id.length() != 32 || npassword.length() > 24 || npassword.length() < 8 || opassword.length() > 24
				|| opassword.length() < 8) {
			map.put("code", "error");
			map.put("desc", "有些参数长度过长!");
			return map;
		}
		try {
			return adminService.udaptePassword(id, npassword, opassword);
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/selectadmrore")
	@ResponseBody
	public Object selectResByRoleID(String amdinid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(amdinid) || amdinid.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			map = adminService.selectAdminRolesByAdminID(amdinid);
			return map;
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/selectadmrores")
	@ResponseBody
	public Object selectResByRole(Integer pagenum, Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return adminService.selectRoleRes(pagenum, pagesize);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "查询失败");
			return map;
		}
	}

	@RequestMapping("/login")
	@ResponseBody
	public Object login(String phone, String password, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(phone) || phone.length() != 11) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return adminService.login(phone, password, session);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/logout")
	@ResponseBody
	public Object logout(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("admin", null);

		map.put("code", "ok");
		map.put("desc", "注销成功");
		return map;

	}

	@RequestMapping("/noauthority")
	@ResponseBody
	public Object noAuthority() {
		System.out.println("没有权限!");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "error");
		map.put("desc", "没有权限!");
		return map;

	}

	@RequestMapping("/nologin")
	@ResponseBody
	public Object noLogin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "error");
		map.put("desc", "没有登录!");
		return map;

	}

}
