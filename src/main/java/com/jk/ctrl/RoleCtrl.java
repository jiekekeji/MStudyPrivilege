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
		return roleService.add(name, remarks, resourcesIDs);
	}

	@RequestMapping("/delete_id")
	@ResponseBody
	public Object deleteByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return roleService.deleteByID(id);
	}

	@RequestMapping("/udapte_id")
	@ResponseBody
	public Object udapteByID(String id, String name, String remarks, String[] resourcesIDs) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || id.length() != 32
				|| name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return roleService.udapteByID(id, name, remarks, resourcesIDs);
	}

	/*
	 * @RequestMapping("/select_id")
	 * 
	 * @ResponseBody public Object selectByID(String id) { Map<String, Object>
	 * map = new HashMap<String, Object>(); if (TxUtils.isEmpty(id) ||
	 * id.length() != 32) { map.put("code", "error"); map.put("desc", "参数错误");
	 * return map; } return resourcesService.selectByID(id); }
	 * 
	 * @RequestMapping("/is_name_exit")
	 * 
	 * @ResponseBody public Object isRoleNameExit(String name) { Map<String,
	 * Object> map = new HashMap<String, Object>(); if (TxUtils.isEmpty(name) ||
	 * name.length() > 36) { map.put("code", "error"); map.put("desc", "参数错误");
	 * return map; }
	 * 
	 * return resourcesService.isResourcesNameExit(name);
	 * 
	 * }
	 * 
	 * @RequestMapping("/select_all")
	 * 
	 * @ResponseBody public Object selectAll() { return
	 * resourcesService.selectAll(); }
	 */
}