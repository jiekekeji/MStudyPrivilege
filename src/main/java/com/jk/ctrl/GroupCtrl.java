package com.jk.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.service.GroupService;
import com.jk.utils.TxUtils;

@Controller
@RequestMapping("/resgroup")
public class GroupCtrl {

	@Autowired
	private GroupService groupService;

	@RequestMapping("/add")
	@ResponseBody
	public Object groupAdd(String name, String remarks) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return groupService.addGroup(name, remarks);
		} catch (Exception e) {
			System.out.println(e);
		}
		map.put("code", "error");
		map.put("desc", "添加失败");
		return map;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object groupDeleteById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return groupService.deleteGroupById(id);
		} catch (Exception e) {
			System.out.println(e);
		}
		map.put("code", "error");
		map.put("desc", "删除失败");
		return map;
	}

	@RequestMapping("/select")
	@ResponseBody
	public Object selectGruopAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return groupService.selectGruopAll();
		} catch (Exception e) {
			System.out.println(e);
		}
		map.put("code", "error");
		map.put("desc", "查询失败");
		return map;
	}

	@RequestMapping("/isexit")
	@ResponseBody
	public Object isGroupNameExit(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || name.length() > 24) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return groupService.isGroupNameExit(name);
		} catch (Exception e) {
			System.out.println(e);
		}
		map.put("code", "error");
		map.put("desc", "查询失败");
		return map;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object updateGroupById(String id, String name, String remarks) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || TxUtils.isEmpty(id) || id.length() != 32
				|| name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			return groupService.updateGroupById(id, name, remarks);
		} catch (Exception e) {
			System.out.println(e);
		}
		map.put("code", "error");
		map.put("desc", "查询失败");
		return map;
	}
}
