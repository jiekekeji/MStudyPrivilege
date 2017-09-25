package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.my.AdminRoleMapper;
import com.jk.mapper.tb.TAdminMapper;
import com.jk.mapper.tb.TAdminRoleMapper;
import com.jk.pojo.my.AdminRole;
import com.jk.pojo.tb.TAdmin;
import com.jk.pojo.tb.TAdminExample;
import com.jk.pojo.tb.TAdminRole;
import com.jk.pojo.tb.TAdminRoleExample;
import com.jk.pojo.tb.TResources;
import com.jk.pojo.tb.TRole;
import com.jk.utils.MD5Utils;
import com.jk.utils.UUIDUtils;

@Service
public class AdminService {

	@Autowired
	private TAdminMapper adminMapper;
	@Autowired
	private TAdminRoleMapper adminRoleMapper;
	@Autowired
	private AdminRoleMapper arMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> add(String name, String password, String remarks, String phone, String qq,
			String[] roleids) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		TAdmin admin = new TAdmin();
		admin.setId(UUIDUtils.uuid());
		admin.setName(name);
		admin.setPassword(MD5Utils.md5(password));
		admin.setRemarks(remarks);
		admin.setPhone(phone);
		admin.setQq(qq);
		admin.setState(0);
		try {
			adminMapper.insert(admin);
			// 往用户-角色表添加
			for (int i = 0; i < roleids.length; i++) {
				TAdminRole adminRole = new TAdminRole();
				adminRole.setId(UUIDUtils.uuid());
				adminRole.setAdminId(admin.getId());
				adminRole.setRoleId(roleids[i]);
				adminRoleMapper.insert(adminRole);
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
			// 从用户表-删除
			adminMapper.deleteByPrimaryKey(id);
			// 从用户-角色表删除
			TAdminRoleExample example = new TAdminRoleExample();
			TAdminRoleExample.Criteria criteria = example.createCriteria();
			criteria.andAdminIdEqualTo(id);
			adminRoleMapper.deleteByExample(example);
			map.put("code", "ok");
			map.put("desc", "删除成功!");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> hangUpById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TAdmin admin = new TAdmin();
			admin.setId(id);
			admin.setState(1);
			// 将state字段设为1
			adminMapper.updateByPrimaryKeySelective(admin);
			map.put("code", "ok");
			map.put("desc", "挂起成功!");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> udapteByID(String id, String name, String remarks, String phone, String qq,
			String[] roleids) {
		Map<String, Object> map = new HashMap<String, Object>();
		TAdmin admin = new TAdmin();
		admin.setId(id);
		admin.setName(name);
		admin.setRemarks(remarks);
		admin.setPhone(phone);
		admin.setQq(qq);
		try {
			// 更新用户信息
			adminMapper.updateByPrimaryKey(admin);
			// 从用户-角色表删除之前的关系
			TAdminRoleExample example = new TAdminRoleExample();
			TAdminRoleExample.Criteria criteria = example.createCriteria();
			criteria.andAdminIdEqualTo(id);
			adminRoleMapper.deleteByExample(example);
			// 往用户-角色表添加
			for (int i = 0; i < roleids.length; i++) {
				TAdminRole adminRole = new TAdminRole();
				adminRole.setId(UUIDUtils.uuid());
				adminRole.setAdminId(admin.getId());
				adminRole.setRoleId(roleids[i]);
				adminRoleMapper.insert(adminRole);
			}
			map.put("code", "ok");
			map.put("desc", "更新成功");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> udaptePassword(String id, String npassword, String opassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询用户
			TAdmin ta = adminMapper.selectByPrimaryKey(id);
			if (ta.getPassword().equals(opassword)) {
				map.put("code", "error");
				map.put("desc", "旧密码不正确");
				return map;
			}
			// 更新用户信息
			TAdmin admin = new TAdmin();
			admin.setId(id);
			admin.setPassword(npassword);
			// 从用户-角色表删除之前的关系
			TAdminExample example = new TAdminExample();
			TAdminExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(npassword);
			adminMapper.updateByPrimaryKeySelective(admin);
			map.put("code", "ok");
			map.put("desc", "更新成功");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Object> selectAdminRolesByAdminID(String amdinid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TRole> roles;
		try {
			roles = arMapper.selectTRolesByAdminId(amdinid);
			map.put("code", "ok");
			map.put("roleid", amdinid);
			map.put("list", roles);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Object> selectRoleRes(Integer pagenum, Integer pagesize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AdminRole> records;
		try {
			if (null == pagenum || null == pagesize) {
				records = arMapper.selectAdminAndRole();
				PageInfo<AdminRole> page = new PageInfo<AdminRole>(records);
				long count = page.getTotal();
				map.put("code", "ok");
				map.put("count", count);
				map.put("resources", records);
				return map;
			}
			PageHelper.startPage(pagenum, pagesize);
			records = arMapper.selectAdminAndRole();
			PageInfo<AdminRole> page = new PageInfo<AdminRole>(records);
			long count = page.getTotal();
			map.put("code", "ok");
			map.put("count", count);
			map.put("roles", records);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Object> selectTResourcesByAdminId(String adminid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TResources> records;
		try {
			records = arMapper.selectTResourcesByAdminId(adminid);
			map.put("code", "ok");
			map.put("resources", records);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
