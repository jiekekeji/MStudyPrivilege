package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.tbmapper.TGroupMapper;
import com.jk.tbmapper.TResourcesMapper;
import com.jk.tbpojo.TGroup;
import com.jk.tbpojo.TGroupExample;
import com.jk.tbpojo.TGroupExample.Criteria;
import com.jk.tbpojo.TResources;
import com.jk.tbpojo.TResourcesExample;
import com.jk.utils.UUIDUtils;

@Service
public class GroupService {

	@Autowired
	private TGroupMapper groupMapper;

	@Autowired
	private TResourcesMapper resourcesMapper;

	public Object addGroup(String name, String remarks) {

		Map<String, Object> map = new HashMap<String, Object>();

		String id = UUIDUtils.uuid();
		TGroup group = new TGroup();
		group.setId(id);
		group.setName(name);
		group.setRemarks(remarks);

		Map<String, Object> temp = isGroupNameExit(name);
		if (temp.get("code").equals("error")) {
			return temp;
		}

		// 向角色表中添加
		groupMapper.insert(group);

		map.put("code", "ok");
		map.put("group", group);
		return map;
	}

	/**
	 * 删除组，如果组下有明细，则需先删除组下的明细
	 * 
	 * @param id
	 *            组ID
	 * @return
	 */
	public Object deleteGroupById(String id) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 查询该ID下是否有明细
		TResourcesExample example = new TResourcesExample();
		example.createCriteria();
		TResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andGroupIdEqualTo(id);
		List<TResources> resources = resourcesMapper.selectByExample(example);

		if (null != resources && resources.size() > 0) {
			map.put("code", "error");
			map.put("desc", "请将该组下的子项删除后在删除");
			map.put("id", id);
			return map;
		}

		// 执行删除
		groupMapper.deleteByPrimaryKey(id);

		map.put("code", "ok");
		map.put("id", id);
		return map;
	}

	/**
	 * 根据id更新组
	 * 
	 * @param id
	 * @param name
	 * @param remarks
	 * @return
	 */
	public Object updateGroupById(String id, String name, String remarks) {

		Map<String, Object> map = new HashMap<String, Object>();

		TGroup group = new TGroup();
		group.setId(id);
		group.setName(name);
		group.setRemarks(remarks);

		Map<String, Object> temp = isGroupNameExit(name);
		if (temp.get("code").equals("error")) {
			return temp;
		}

		// 向角色表中添加
		groupMapper.updateByPrimaryKey(group);

		map.put("code", "ok");
		map.put("group", group);
		return map;
	}

	/**
	 * 查询所有的分组
	 * 
	 * @param id
	 * @param name
	 * @param remarks
	 * @return
	 */
	public Object selectGruopAll() {

		Map<String, Object> map = new HashMap<String, Object>();

		TGroupExample example = new TGroupExample();
		Criteria criteria = example.createCriteria();

		// 向角色表中添加
		List<TGroup> groups = groupMapper.selectByExample(example);

		map.put("code", "ok");
		map.put("groups", groups);
		return map;
	}

	/**
	 * 判断分组名是否已存在
	 * 
	 * @param name
	 * @return
	 */
	public Map<String, Object> isGroupNameExit(String name) {

		Map<String, Object> map = new HashMap<String, Object>();
		TGroupExample example = new TGroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TGroup> groups = groupMapper.selectByExample(example);
		if (null != groups && groups.size() > 0) {
			map.put("code", "error");
			map.put("desc", "分组名已存在!");
			return map;
		}
		map.put("code", "ok");
		return map;
	}

}
