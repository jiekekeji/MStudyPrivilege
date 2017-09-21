package com.jk.mapper.my;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jk.bean.RoleRes;
import com.jk.tb.pojo.TResources;

public interface RoleResMapper {

	@Select("SELECT * from t_resources WHERE id=#{id}")
	@Results({ 
	    @Result(id = true, column = "id", property = "id"),
	    @Result(column = "name", property = "name"),
		@Result(column = "url", property = "url"),
		@Result(column = "group_id", property = "groupId"),
	})
	public TResources selectTResourcesById(String id);
	
	
	@Select("SELECT * from t_role_res WHERE role_id=#{roleId}")
	@Results({ 
		    @Result(id = true, column = "role_id", property = "roleId"),
//			@Result(column = "role_id", property = "role_id"),
			@Result(column = "res_id", property = "resources", many = @Many(select = "com.jk.mapper.my.RoleResMapper.selectTResourcesById") ), })
	RoleRes selectResourcesByRoleID(String roleId);
}
