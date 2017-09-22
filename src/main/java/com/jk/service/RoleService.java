package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jk.mapper.my.RoleResMapper;
import com.jk.mapper.tb.TAdminRoleMapper;
import com.jk.mapper.tb.TRoleMapper;
import com.jk.mapper.tb.TRoleResMapper;
import com.jk.tb.pojo.TAdminRole;
import com.jk.tb.pojo.TAdminRoleExample;
import com.jk.tb.pojo.TResources;
import com.jk.tb.pojo.TRole;
import com.jk.tb.pojo.TRoleExample;
import com.jk.tb.pojo.TRoleRes;
import com.jk.tb.pojo.TRoleResExample;
import com.jk.utils.UUIDUtils;

@Service
public class RoleService {

	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TRoleResMapper roleResMapper;
	@Autowired
	private TAdminRoleMapper adminRoleMapper;
	@Autowired
	private RoleResMapper rrsMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> add(String name, String remarks, String[] resourcesIDs) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		TRole role = new TRole();
		role.setId(UUIDUtils.uuid());
		role.setName(name);
		role.setRemarks(remarks);
		try {
			// 检测角色名是否已存在
			Map<String, Object> temp = isRoleNameExit(name);
			if (temp.get("code").equals("error")) {
				return temp;
			}
			// 往角色表中添加
			roleMapper.insert(role);
			// 往角色资源关系表添加
			for (int i = 0; i < resourcesIDs.length; i++) {
				TRoleRes res = new TRoleRes();
				res.setId(UUIDUtils.uuid());
				res.setRoleId(role.getId());
				res.setResId(resourcesIDs[i]);
				roleResMapper.insert(res);
			}
			map.put("code", "ok");
			map.put("desc", "添加成功");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 判断该角色下是否有用户
			TAdminRoleExample arExample = new TAdminRoleExample();
			TAdminRoleExample.Criteria arcriteria = arExample.createCriteria();
			arcriteria.andRoleIdEqualTo(id);
			List<TAdminRole> adminRoles = adminRoleMapper.selectByExample(arExample);
			if (null != adminRoles && adminRoles.size() > 0) {
				map.put("code", "error");
				map.put("desc", "该角色下存在用户，请将该角色下的用户移除后删除!");
				return map;
			}
			// 删除角色
			roleMapper.deleteByPrimaryKey(id);
			// 从角色-权限表中删除
			TRoleResExample example = new TRoleResExample();
			TRoleResExample.Criteria criteria = example.createCriteria();
			criteria.andResIdEqualTo(id);
			roleResMapper.deleteByExample(example);
			map.put("code", "ok");
			map.put("desc", "删除成功!");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> udapteByID(String id, String name, String remarks, String[] resourcesIDs) {
		Map<String, Object> map = new HashMap<String, Object>();
		TRole role = new TRole();
		role.setId(id);
		role.setName(name);
		role.setRemarks(remarks);
		try {
			// 删除角色
			roleMapper.updateByPrimaryKey(role);
			// 删除之前的角色权限关系
			TRoleResExample example = new TRoleResExample();
			TRoleResExample.Criteria criteria = example.createCriteria();
			criteria.andResIdEqualTo(id);
			roleResMapper.deleteByExample(example);
			// 添加新的角色权限关系
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
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检测角色名是否存在
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> isRoleNameExit(String name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TRoleExample example = new TRoleExample();
		TRoleExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TRole> resources;
		try {
			resources = roleMapper.selectByExample(example);
			if (null == resources || resources.size() == 0) {
				map.put("code", "ok");
				map.put("desc", "资源名不存在!");
				return map;
			}
			map.put("code", "error");
			map.put("desc", "资源名已存在!");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Object> selectRoleResByRoleID(String roleid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TResources> resources = rrsMapper.selectTResourcesByRoleId(roleid);

		System.out.println(resources);
		return null;
	}

}
