package com.jk.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jk.pojo.my.AdminRole;
import com.jk.pojo.tb.TResources;
import com.jk.pojo.tb.TRole;

public interface AdminRoleMapper {

	/**
	 * 根据用户id查询用户的角色
	 * 
	 * @param roleid
	 * @return
	 */
	@Select("SELECT * FROM t_role WHERE EXISTS (SELECT role_id FROM t_admin_role WHERE admin_id=#{adminId})")
	List<TRole> selectTRolesByAdminId(String adminId);

	/**
	 * 查询多个用户并查询用户的角色
	 * 
	 * @return
	 */
	@Select("SELECT * FROM t_admin")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "remarks", property = "remarks"), @Result(column = "phone", property = "phone"),
			@Result(column = "qq", property = "qq"), @Result(column = "state", property = "state"),
			@Result(column = "id", property = "roles", many = @Many(select = "com.jk.mapper.my.AdminRoleMapper.selectTRolesByAdminId") ) })
	List<AdminRole> selectAdminAndRole();

	/**
	 * 根据用户id查询用户的资源
	 * 
	 * @param adminId
	 * @return
	 */
	@Select("SELECT * FROM t_resources WHERE EXISTS (SELECT id FROM t_role WHERE EXISTS (SELECT role_id FROM t_admin_role WHERE admin_id=#{adminId})")
	List<TResources> selectTResourcesByAdminId(String adminId);
}
