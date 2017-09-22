package com.jk.mapper.my;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import com.jk.sql.provider.RoleResProvider;
import com.jk.tb.pojo.TResources;

public interface RoleResMapper {

	@SelectProvider(type = RoleResProvider.class, method = "selectTResourcesByIds")
	public List<TResources> selectTResourcesByIds(Map<String, Object> map);
}
