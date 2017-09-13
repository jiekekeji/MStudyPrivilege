package com.jk.ctrl;

import java.util.UUID;

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
		
		String uuid=UUID.randomUUID().toString().replace("-", "").toLowerCase();

		TRole role = new TRole();
		role.setId(uuid);
		role.setName("Asdfasdfasdf阿斯顿发阿斯顿发送阿斯顿发fsaffffffffff等待斗订单订单1");
		role.setRemarks("备注信息");
		int code = tRoleMapper.insert(role);
		System.out.println("role" + tRoleMapper.selectByPrimaryKey("159ccce2265e04f4533378e0e6c28911"));
		return "success";
	}

}
