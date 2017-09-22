package com.jk.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.jk.tb.pojo.TResources;

public interface RoleResMapper {

	@Select("SELECT * FROM t_resources WHERE EXISTS (SELECT res_id FROM t_role_res WHERE role_id=#{roleid})")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_id", property = "groupId", jdbcType = JdbcType.CHAR) })
	List<TResources> selectTResourcesByRoleId(String roleid);
}
