package com.jk.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.jk.pojo.my.RoleRes;
import com.jk.pojo.tb.TResources;

public interface RoleResMapper {

	/**
	 * 查询角色下的所有权限明细
	 * 
	 * @param roleid
	 * @return
	 */
	@Select("SELECT * FROM t_resources WHERE EXISTS (SELECT res_id FROM t_role_res WHERE role_id=#{roleid})")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_id", property = "groupId", jdbcType = JdbcType.CHAR) })
	List<TResources> selectTResourcesByRoleId(String roleid);

	/**
	 * 查询所有的角色并查询该角色下的明细
	 * 
	 * @return
	 */
	@Select("SELECT * from t_role")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "remarks", property = "remarks"),
			@Result(column = "id", property = "resources", many = @Many(select = "com.jk.mapper.my.RoleResMapper.selectTResourcesByRoleId") ) })
	List<RoleRes> selectRoleAndResources();
}
