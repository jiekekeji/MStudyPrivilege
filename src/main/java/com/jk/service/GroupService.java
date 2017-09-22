package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jk.mapper.tb.TGroupMapper;
import com.jk.mapper.tb.TResourcesMapper;
import com.jk.pojo.tb.TGroup;
import com.jk.pojo.tb.TGroupExample;
import com.jk.pojo.tb.TResources;
import com.jk.pojo.tb.TResourcesExample;
import com.jk.pojo.tb.TGroupExample.Criteria;
import com.jk.utils.UUIDUtils;

@Service
public class GroupService {

	@Autowired
	private TGroupMapper groupMapper;
	@Autowired
	private TResourcesMapper resourcesMapper;

	public Map<String, Object> addGroup(String name, String remarks) throws Exception {

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
		try {
			groupMapper.insert(group);
			map.put("code", "ok");
			map.put("desc", "新增成功!");
			return map;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 删除组，先解除组关系后删除组
	 * 
	 * @param id
	 *            组ID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteGroupById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();

		TResourcesExample example = new TResourcesExample();
		TResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andGroupIdEqualTo(id);
		TResources record = new TResources();
		record.setGroupId("");
		try {
			// 解除关系
			resourcesMapper.updateByExampleSelective(record, example);
			// 执行删除
			groupMapper.deleteByPrimaryKey(id);
			map.put("code", "ok");
			map.put("id", id);
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 根据id更新组
	 * 
	 * @param id
	 * @param name
	 * @param remarks
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateGroupById(String id, String name, String remarks) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		TGroup group = new TGroup();
		group.setId(id);
		group.setName(name);
		group.setRemarks(remarks);
		try {
			groupMapper.updateByPrimaryKey(group);
			map.put("code", "ok");
			map.put("desc", "更新成功");
			return map;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 查询所有的分组
	 * 
	 * @param id
	 * @param name
	 * @param remarks
	 * @return
	 * @throws Exception
	 */
	public Object selectGruopAll() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TGroupExample example = new TGroupExample();
		List<TGroup> groups;
		try {
			groups = groupMapper.selectByExample(example);
			map.put("code", "ok");
			map.put("groups", groups);
			return map;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 判断分组名是否已存在
	 * 
	 * @param name
	 * @return
	 */
	public Map<String, Object> isGroupNameExit(String name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TGroupExample example = new TGroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TGroup> groups;
		try {
			groups = groupMapper.selectByExample(example);
			if (null == groups || groups.size() == 0) {
				map.put("code", "ok");
				map.put("desc", "分组名不存在!");
				return map;
			}
			map.put("code", "error");
			map.put("desc", "分组名已存在!");
			return map;
		} catch (Exception e) {
			throw e;
		}
	}

}
