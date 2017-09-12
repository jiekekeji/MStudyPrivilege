package com.jk.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class TRoleCtrl {

	public TRoleCtrl() {
		System.out.println("TRoleCtrl");
	}

	@RequestMapping("/selectOne")
	@ResponseBody
	public String selectOne() {

		return "success";
	}

}
