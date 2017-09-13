package com.jk.ctrl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.tbmapper.TRoleMapper;
import com.jk.tbpojo.TRole;

@Controller
@RequestMapping("/role")
public class TRoleCtrl {

	@Autowired
	TRoleMapper tRoleMapper;

	@RequestMapping("/selectOne")
	@ResponseBody
	public String selectOne() {
		
		String uuid=UUID.randomUUID().toString().replace("-", "").toLowerCase();

		TRole role = new TRole();
		role.setId(uuid);
		role.setName("客服");
		role.setRemarks("备注信息");
		int code = tRoleMapper.insert(role);
		System.out.println("role" + tRoleMapper.selectByPrimaryKey(uuid));
		return "success";
	}

}
