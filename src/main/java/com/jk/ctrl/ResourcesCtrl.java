package com.jk.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.service.ResourcesService;
import com.jk.utils.TxUtils;

@Controller
@RequestMapping("/resources")
public class ResourcesCtrl {

	@Autowired
	private ResourcesService resourcesService;

	@RequestMapping("/add")
	@ResponseBody
	public Object add(String name, String url, String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(url) || name.length() > 36 || url.length() > 64) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}

		return roleService.addRole(name, remarks, resIds);

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

		return roleService.addRole(name, remarks, resIds);

	}

	@RequestMapping("/udapte_id")
	@ResponseBody
	public Object udapteByID(String id, String name, String url, String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || TxUtils.isEmpty(name) || TxUtils.isEmpty(url) || TxUtils.isEmpty(groupId)
				|| id.length() != 32 || name.length() > 36 || url.length() > 64 || groupId.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}

		return roleService.addRole(name, remarks, resIds);

	}

}
