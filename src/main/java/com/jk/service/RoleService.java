package com.jk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.tbmapper.TRoleMapper;
import com.jk.tbmapper.TRoleResMapper;
import com.jk.tbpojo.TRole;
import com.jk.tbpojo.TRoleRes;
import com.jk.utils.UUIDUtils;

@Service
public class RoleService {

	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TRoleResMapper roleResMapper;

	@Transactional
	public Object addRole(String name, String remarks, String[] resIds) {

		Map<String, Object> map = new HashMap<String, Object>();

		String id = UUIDUtils.uuid();
		TRole role = new TRole();
		role.setId(id);
		role.setName(name);
		role.setRemarks(remarks);

		// 向角色表中添加
		roleMapper.insert(role);

		List<TRoleRes> roleRes = new ArrayList<TRoleRes>();
		for (int i = 0; i < resIds.length; i++) {
			TRoleRes temp = new TRoleRes();
			temp.setId(UUIDUtils.uuid());
			temp.setRoleId(id);
			temp.setResId(resIds[i]);
		}

		// 向角色权限表中添加角色权限关系
		for (TRoleRes res : roleRes) {
			roleResMapper.insert(res);
		}

		map.put("code", "ok");
		return map;
	}

}
