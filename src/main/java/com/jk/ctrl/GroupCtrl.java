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

		Object o = groupService.addGroup(name, remarks);
		System.out.println(o);
		return o;

	}
}
