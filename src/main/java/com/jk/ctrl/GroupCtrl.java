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
@RequestMapping("/group")
public class GroupCtrl {

	@Autowired
	private GroupService groupService;

	@RequestMapping("/create")
	@ResponseBody
	public Object groupCreate(String name, String remarks) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}

		return groupService.addGroup(name, remarks);

	}

	@RequestMapping("/delete_id")
	@ResponseBody
	public Object groupDeleteById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return groupService.deleteGroupById(id);
	}

	@RequestMapping("/select_all")
	@ResponseBody
	public Object selectGruopAll() {
		return groupService.selectGruopAll();
	}

	@RequestMapping("/is_group_name_exit")
	@ResponseBody
	public Object isGroupNameExit(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || name.length() > 24) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		return groupService.isGroupNameExit(name);
	}

	@RequestMapping("/update_group_by_id")
	@ResponseBody
	public Object updateGroupById(String id, String name, String remarks) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || TxUtils.isEmpty(id) || id.length() != 32
				|| name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}

		return groupService.updateGroupById(id, name, remarks);
	}
}
