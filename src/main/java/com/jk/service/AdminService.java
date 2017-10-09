package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

	/**
	 * 新增用户
	 * 
	 * @param name
	 * @param password
	 * @param remarks
	 * @param phone
	 * @param qq
	 * @param roleids
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> add(String name, String password, String remarks, String phone, String qq,
			String[] roleids) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 检测手机号是否已存在
		Map<String, Object> exit = isPhoneExit(phone);
		if (exit.get("code").equals("error")) {
			return exit;
		}

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

	/**
	 * 根据用户id删除用户
	 * 
	 * @param id
	 * @return
	 */
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

	/**
	 * 根据用户id挂起某个用户的权限
	 * 
	 * @param id
	 * @return
	 */
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

	/**
	 * 根据用户id更新用户信息
	 * 
	 * @param id
	 * @param name
	 * @param remarks
	 * @param phone
	 * @param qq
	 * @param roleids
	 * @return
	 */
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

	/**
	 * 根据用户id修改密码
	 * 
	 * @param id
	 * @param npassword
	 * @param opassword
	 * @return
	 */
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

	/**
	 * 检测手机号是否已存在
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> isPhoneExit(String phone) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TAdminExample example = new TAdminExample();
		TAdminExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<TAdmin> admins;
		try {
			admins = adminMapper.selectByExample(example);
			if (null == admins || admins.size() == 0) {
				map.put("code", "ok");
				map.put("desc", "手机不存在!");
				return map;
			}
			map.put("code", "error");
			map.put("desc", "资源名称已存在");
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 根据用户id查询用户的角色
	 * 
	 * @param amdinid
	 * @return
	 * @throws Exception
	 */
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

	/*
	 * 查询用户和用户对应的角色
	 */
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
				map.put("admin", records);
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

	/**
	 * 根据用户id查询资源
	 * 
	 * @param adminid
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 用户登录
	 * 
	 * @param adminid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> login(String phone, String password, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TAdminExample example = new TAdminExample();
		TAdminExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<TAdmin> admins;
		try {
			admins = adminMapper.selectByExample(example);
			if (null == admins || admins.size() == 0) {
				map.put("code", "error");
				map.put("desc", "手机不存在!");
				return map;
			}
			TAdmin admin = admins.get(0);
			System.out.println(admin);
			if (!admin.getPassword().equals(MD5Utils.md5(password))) {
				map.put("code", "error");
				map.put("desc", "密码不正确!");
				return map;

			}
			// 登录成功，保存登录状态
			session.setAttribute("admin", admin);
			// 登录成功返回该用户具有的资源
			Map<String, Object> resMap = selectTResourcesByAdminId(admin.getId());
			map.put("code", "ok");
			map.put("desc", "登录成功");
			map.put("resources", resMap.get("resources"));
			return map;
		} catch (Exception e) {
			throw e;
		}
	}
}
