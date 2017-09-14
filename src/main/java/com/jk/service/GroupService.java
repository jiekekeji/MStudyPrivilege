package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.tbmapper.TGroupMapper;
import com.jk.tbpojo.TGroup;
import com.jk.tbpojo.TGroupExample;
import com.jk.tbpojo.TGroupExample.Criteria;
import com.jk.utils.UUIDUtils;

@Service
public class GroupService {

	@Autowired
	private TGroupMapper groupMapper;

	@Transactional
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
