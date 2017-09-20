package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.tbmapper.TResourcesMapper;
import com.jk.tbpojo.TResources;
import com.jk.tbpojo.TResourcesExample;
import com.jk.utils.UUIDUtils;

@Service
public class ResourcesService {

	@Autowired
	private TResourcesMapper resourcesMapper;

	public Map<String, Object> add(String name, String url, String groupId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TResources record = new TResources();
		record.setId(UUIDUtils.uuid());
		record.setName(name);
		record.setUrl(url);
		record.setGroupId(groupId);
		try {
			Map<String, Object> temp = isResourcesNameExit(name);
			if ("error".equals(temp.get("code"))) {
				return temp;
			}
			resourcesMapper.insert(record);
			map.put("code", "ok");
			map.put("resources", record);
			return map;
		} catch (Exception e) {
			throw e;
		}
	}

	public Map<String, Object> deleteByID(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			resourcesMapper.deleteByPrimaryKey(id);
			map.put("code", "ok");
			map.put("desc", "删除成功!");
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	public Object udapteByID(String id, String name, String url, String groupId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TResources record = new TResources();
		record.setId(UUIDUtils.uuid());
		record.setName(name);
		record.setUrl(url);
		record.setGroupId(groupId);
		try {
			resourcesMapper.updateByPrimaryKey(record);
			map.put("code", "ok");
			map.put("desc", "更新成功!");
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	public Object selectByID(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TResources record = resourcesMapper.selectByPrimaryKey(id);
			map.put("code", "ok");
			map.put("resources", record);
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	public Map<String, Object> isResourcesNameExit(String name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TResourcesExample example = new TResourcesExample();
		TResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TResources> resources;
		try {
			resources = resourcesMapper.selectByExample(example);
			if (null == resources || resources.size() == 0) {
				map.put("code", "ok");
				map.put("desc", "资源名称未存在");
				return map;
			}
			map.put("code", "error");
			map.put("desc", "资源名称已存在");
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	public Object selectByGroupId(String groupId, Integer pagenum, Integer pagesize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TResourcesExample example = new TResourcesExample();
		TResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andGroupIdEqualTo(groupId);
		List<TResources> records;
		try {
			if (null == pagenum || null == pagesize) {
				records = resourcesMapper.selectByExample(example);
				map.put("code", "ok");
				map.put("resources", records);
				return map;
			}
			PageHelper.startPage(pagenum, pagesize);
			records = resourcesMapper.selectByExample(example);
			PageInfo<TResources> page = new PageInfo<TResources>(records);
			long count = page.getTotal();
			map.put("code", "ok");
			map.put("count", count);
			map.put("resources", records);
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	public Object selectAll(Integer pagenum, Integer pagesize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TResourcesExample example = new TResourcesExample();
		example.setOrderByClause("group_id ASC");
		List<TResources> records;
		try {
			if (null == pagenum || null == pagesize) {
				records = resourcesMapper.selectByExample(example);
				map.put("code", "ok");
				map.put("resources", records);
				return map;
			}
			PageHelper.startPage(pagenum, pagesize);
			records = resourcesMapper.selectByExample(example);
			PageInfo<TResources> page = new PageInfo<TResources>(records);
			long count = page.getTotal();
			map.put("code", "ok");
			map.put("count", count);
			map.put("resources", records);
			return map;
		} catch (Exception e) {
			throw e;
		}

	}
}
