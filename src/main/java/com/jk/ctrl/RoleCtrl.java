package com.jk.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.service.RoleService;
import com.jk.utils.TxUtils;

@Controller
@RequestMapping("/role")
public class RoleCtrl {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/add")
	@ResponseBody
	public Object add(String name, String remarks, String[] resourcesIDs) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || name.length() > 24 || TxUtils.isEmpty(remarks) || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			map = roleService.add(name, remarks, resourcesIDs);
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
			return roleService.deleteByID(id);
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/udapte")
	@ResponseBody
	public Object udapteByID(String id, String name, String remarks, String[] resourcesIDs) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || id.length() != 32
				|| name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return roleService.udapteByID(id, name, remarks, resourcesIDs);
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

	@RequestMapping("/selectroleres")
	@ResponseBody
	public Object selectRoleResByRoleID(String roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(roleid) || roleid.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			roleService.selectRoleResByRoleID(roleid);
			return map;
		} catch (Exception e) {
			System.err.println(e);
			map.put("code", "error");
			map.put("desc", "系统异常");
			return map;
		}
	}

}
