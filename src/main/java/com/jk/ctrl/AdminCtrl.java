package com.jk.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.service.AdminService;
import com.jk.service.RoleService;
import com.jk.utils.TxUtils;

@Controller
@RequestMapping("/admin")
public class AdminCtrl {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/add")
	@ResponseBody
	public Object add(String name, String remarks, String phone, String qq, String[] roleids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || TxUtils.isEmpty(phone) || TxUtils.isEmpty(qq)) {
			map.put("code", "error");
			map.put("desc", "有些参数为空!");
			return map;
		}
		if (name.length() > 24 || remarks.length() > 255 || phone.length() > 11 || qq.length() > 20) {
			map.put("code", "error");
			map.put("desc", "有些参数长度过长!");
			return map;
		}
		try {
			map = adminService.add(name, remarks, phone, qq, roleids);
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

	@RequestMapping("/udapte")
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

}
