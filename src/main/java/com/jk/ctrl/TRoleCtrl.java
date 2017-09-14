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
public class TRoleCtrl {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/create")
	@ResponseBody
	public Object roleCreate(String name, String remarks, String[] resIds) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(name) || TxUtils.isEmpty(remarks) || name.length() > 24 || remarks.length() > 255) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}

		return roleService.addRole(name, remarks, resIds);

	}

}
