package com.jk.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.mapper.TRoleMapper;
import com.jk.pojo.TRole;

@Controller
@RequestMapping("/role")
public class TRoleCtrl {

	@Autowired
	TRoleMapper tRoleMapper;

	@RequestMapping("/selectOne")
	@ResponseBody
	public String selectOne() {

		TRole role = new TRole();
		role.setId("159ccce2265e04f4533378e0e6c28958");
		role.setName("service");
//		role.setDesc("service desc");

		int code = tRoleMapper.insert(role);
		System.out.println("role" + code);
		return "success";
	}

}
