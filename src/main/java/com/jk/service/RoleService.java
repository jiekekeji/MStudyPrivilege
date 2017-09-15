package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.tbmapper.TRoleMapper;
import com.jk.tbmapper.TRoleResMapper;
import com.jk.tbpojo.TRole;
import com.jk.tbpojo.TRoleExample;
import com.jk.tbpojo.TRoleRes;
import com.jk.tbpojo.TRoleResExample;
import com.jk.utils.UUIDUtils;

@Service
public class RoleService {

	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TRoleResMapper roleResMapper;

	@Transactional(propagation = Propagation.MANDATORY)
	public Object add(String name, String remarks, String[] resourcesIDs) {

		Map<String, Object> map = new HashMap<String, Object>();
		// 判断是否已存在name
		Map<String, Object> temp = isRoleNameExit(name);
		if (temp.get("code").equals("error")) {
			return temp;
		}

		TRole role = new TRole();
		role.setId(UUIDUtils.uuid());
		role.setName(name);
		role.setRemarks(remarks);
		roleMapper.insert(role);
		
		String string  = null;
	    if(string.equals("")) {
	        int i = 0;
	    }

//		try {
//			// 往角色表中添加
//			roleMapper.insert(role);
//			// 往角色资源关系表添加
//			for (int i = 0; i < resourcesIDs.length; i++) {
//				TRoleRes res = new TRoleRes();
//				res.setId(UUIDUtils.uuid());
//				res.setRoleId(role.getId());
//				res.setResId(resourcesIDs[i]);
//				roleResMapper.insert(res);
//			}
//			map.put("code", "ok");
//			map.put("desc", "添加成功");
//		} catch (Exception e) {
//			map.put("code", "error");
//			map.put("desc", "添加失败");
//			System.out.println(e.toString());
//			throw new RuntimeException(e);
//		}
		return map;
	}

	@Transactional
	public Object deleteByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			roleMapper.deleteByPrimaryKey(id);
			// 从关系表中删除
			TRoleResExample example = new TRoleResExample();
			TRoleResExample.Criteria criteria = example.createCriteria();
			criteria.andResIdEqualTo(id);
			roleResMapper.deleteByExample(example);
			map.put("code", "ok");
			map.put("desc", "删除成功!");
			return map;
		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", "添加失败");
			System.out.println(e.toString());
			return map;
		}
	}

	@Transactional
	public Object udapteByID(String id, String name, String remarks, String[] resourcesIDs) {
		Map<String, Object> map = new HashMap<String, Object>();
		TRole role = new TRole();
		role.setId(id);
		role.setName(name);
		role.setRemarks(remarks);
		try {
			roleMapper.updateByPrimaryKey(role);
			// 先把之前的删除
			TRoleResExample example = new TRoleResExample();
			TRoleResExample.Criteria criteria = example.createCriteria();
			criteria.andResIdEqualTo(id);
			roleResMapper.deleteByExample(example);
			// 在添加进去
			for (int i = 0; i < resourcesIDs.length; i++) {
				TRoleRes res = new TRoleRes();
				res.setId(UUIDUtils.uuid());
				res.setRoleId(role.getId());
				res.setResId(resourcesIDs[i]);
				roleResMapper.insert(res);
			}
			map.put("code", "ok");
			map.put("desc", "更新成功");
			return map;
		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", "更新失败");
			System.out.println(e.toString());
			return map;
		}
	}

	//
	// @RequestMapping("/select_id")
	// @ResponseBody
	// public Object selectByID(String id) {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// TRole role = roleMapper.selectByPrimaryKey(id);
	// TRoleResExample example = new TRoleResExample();
	// TRoleResExample.Criteria criteria = example.createCriteria();
	// criteria.andResIdEqualTo(id);
	// roleResMapper.selectByExample(example);
	// return resourcesService.selectByID(id);
	// }
	//
	// @RequestMapping("/is_name_exit")
	@ResponseBody
	public Map<String, Object> isRoleNameExit(String name) {
		Map<String, Object> map = new HashMap<String, Object>();

		TRoleExample example = new TRoleExample();
		TRoleExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TRole> resources = roleMapper.selectByExample(example);
		if (null != resources && resources.size() > 0) {
			map.put("code", "error");
			map.put("desc", "资源名称已存在");
			return map;
		}
		map.put("code", "ok");
		return map;
	}
	//
	// @RequestMapping("/select_all")
	// @ResponseBody
	// public Object selectAll() {
	// return resourcesService.selectAll();
	// }

}
