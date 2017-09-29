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
	public Object add(String name, String url, Integer login, String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(url) || name.length() > 36 || url.length() > 64
				|| TxUtils.isEmpty(groupId) || groupId.length() != 32 || login != 0 || login != 1) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return resourcesService.add(name, url, login, groupId);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "添加失败");
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
			return resourcesService.deleteByID(id);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "删除失败");
			return map;
		}
	}

	@RequestMapping("/udapte")
	@ResponseBody
	public Object udapteByID(String id, String name, String url, Integer login, String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || TxUtils.isEmpty(name) || TxUtils.isEmpty(url) || TxUtils.isEmpty(groupId)
				|| id.length() != 32 || name.length() > 36 || url.length() > 64 || groupId.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return resourcesService.udapteByID(id, name, url, login, groupId);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "更新失败");
			return map;
		}
	}

	@RequestMapping("/selectbyid")
	@ResponseBody
	public Object selectByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return resourcesService.selectByID(id);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "查询失败");
			return map;
		}
	}

	@RequestMapping("/isnameexit")
	@ResponseBody
	public Object isResourcesNameExit(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || name.length() > 36) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return resourcesService.isResourcesNameExit(name);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "查询失败");
			return map;
		}
	}

	@RequestMapping("/selectbygid")
	@ResponseBody
	public Object selectByGroupId(String groupId, Integer pagenum, Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(groupId) || groupId.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return resourcesService.selectByGroupId(groupId, pagenum, pagesize);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "查询失败");
			return map;
		}
	}

	@RequestMapping("/selectall")
	@ResponseBody
	public Object selectAll(Integer pagenum, Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return resourcesService.selectAll(pagenum, pagesize);
		} catch (Exception e) {
			System.out.println(e);
			map.put("code", "error");
			map.put("desc", "查询失败");
			return map;
		}
	}

}
