package com.jk.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.jk.pojo.my.RoleRes;
import com.jk.pojo.tb.TResources;

public interface AdminRoleMapper {

	/**
	 * 查询角色下的所有权限明细
	 * 
	 * @param roleid
	 * @return
	 */
	@Select("SELECT * FROM t_role WHERE EXISTS (SELECT role_id FROM t_admin_role WHERE admin_id=#{adminId})")
	List<TResources> selectTRolesByAdminId(String adminId);

	/**
	 * 查询所有的角色并查询该角色下的明细
	 * 
	 * @return
	 */
	@Select("SELECT * FROM t_role")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "remarks", property = "remarks"),
			@Result(column = "id", property = "resources", many = @Many(select = "com.jk.mapper.my.RoleResMapper.selectTResourcesByRoleId") ) })
	List<RoleRes> selectRoleAndResources();
}
