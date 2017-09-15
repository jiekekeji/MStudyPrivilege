package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.tbmapper.TResourcesMapper;
import com.jk.tbpojo.TResources;
import com.jk.tbpojo.TResourcesExample;
import com.jk.utils.TxUtils;
import com.jk.utils.UUIDUtils;

@Service
public class ResourcesService {

	@Autowired
	private TResourcesMapper resourcesMapper;

	public Object add(String name, String url, String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> temp = isResourcesNameExit(name);
		if ("error".equals(temp.get("code"))) {
			return temp;
		}

		TResources record = new TResources();
		record.setId(UUIDUtils.uuid());
		record.setName(name);
		record.setUrl(url);
		record.setGroupId(groupId);
		try {
			resourcesMapper.insert(record);
			map.put("code", "ok");
			map.put("resources", record);
			return map;
		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", e.toString());
			return map;
		}

	}

	public Object deleteByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (TxUtils.isEmpty(id) || id.length() != 32) {
			map.put("code", "error");
			map.put("desc", "参数错误");
			return map;
		}
		try {
			resourcesMapper.deleteByPrimaryKey(id);
			map.put("code", "ok");
			map.put("desc", "删除成功!");
			return map;

		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", e.toString());
			return map;
		}

	}

	public Object udapteByID(String id, String name, String url, String groupId) {
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
			map.put("code", "error");
			map.put("desc", e.toString());
			return map;
		}

	}

	public Object selectByID(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TResources record = resourcesMapper.selectByPrimaryKey(id);
			map.put("code", "ok");
			map.put("resources", record);
			return map;
		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", e.toString());
			return map;
		}

	}

	public Map<String, Object> isResourcesNameExit(String name) {
		Map<String, Object> map = new HashMap<String, Object>();

		TResourcesExample example = new TResourcesExample();
		TResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TResources> resources = resourcesMapper.selectByExample(example);
		if (null != resources && resources.size() > 0) {
			map.put("code", "error");
			map.put("desc", "资源名称已存在");
			return map;
		}
		map.put("code", "ok");

		return map;

	}

	public Object selectByGroupId(String groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		TResourcesExample example = new TResourcesExample();
		TResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andGroupIdEqualTo(groupId);

		try {
			List<TResources> record = resourcesMapper.selectByExample(example);
			map.put("code", "ok");
			map.put("resources", record);
			return map;
		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", e.toString());
			return map;
		}

	}

	public Object selectAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		TResourcesExample example = new TResourcesExample();
		example.setOrderByClause("groupId ASC");
		try {
			List<TResources> record = resourcesMapper.selectByExample(example);
			map.put("code", "ok");
			map.put("resources", record);
			return map;
		} catch (Exception e) {
			map.put("code", "error");
			map.put("desc", e.toString());
			return map;
		}

	}
}
