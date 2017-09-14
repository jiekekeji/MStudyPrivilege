package com.jk.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.tbmapper.TRoleMapper;

@Controller
@RequestMapping("/privilege")
public class TRoleCtrl {

	@Autowired
	TRoleMapper tRoleMapper;

	/**
	 * 创建角色
	 * 
	 * @param name
	 *            角色名称
	 * @param remarks
	 *            角色备注描述等
	 * @param resIds
	 *            角色拥有的权限id
	 * @return
	 */
	@RequestMapping("/role/create")
	@ResponseBody
	public String roleCreate(String name, String remarks, String[] resIds) {

		System.out.println(name);
		System.out.println(remarks);
		System.out.println(resIds.length);

		return "success";
	}

}
