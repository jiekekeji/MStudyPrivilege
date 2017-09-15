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
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(url) || name.length() > 36 || url.length() > 64
				|| TxUtils.isEmpty(groupId) || groupId.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return resourcesService.add(name, url, groupId);
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
		return resourcesService.deleteByID(id);
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
		return resourcesService.udapteByID(id, name, url, groupId);
	}

	@RequestMapping("/select_id")
	@ResponseBody
	public Object selectByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return resourcesService.selectByID(id);
	}

	@RequestMapping("/is_name_exit")
	@ResponseBody
	public Object isResourcesNameExit(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || name.length() > 36) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}

		return resourcesService.isResourcesNameExit(name);

	}

	@RequestMapping("/select_groupId")
	@ResponseBody
	public Object selectByGroupId(String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(groupId) || groupId.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return resourcesService.selectByGroupId(groupId);
	}

	@RequestMapping("/select_all")
	@ResponseBody
	public Object selectAll() {
		return resourcesService.selectAll();
	}

}
