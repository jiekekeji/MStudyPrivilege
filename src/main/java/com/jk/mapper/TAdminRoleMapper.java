package com.jk.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jk.pojo.TAdminRole;
import com.jk.pojo.TAdminRoleExample;

public interface TAdminRoleMapper {
    int countByExample(TAdminRoleExample example);

    int deleteByExample(TAdminRoleExample example);

    @Delete({
        "delete from t_admin_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_admin_role (id, role_id, admin_id)",
        "values (#{id,jdbcType=CHAR}, #{roleId,jdbcType=CHAR}, #{adminId,jdbcType=CHAR})"
    })
    int insert(TAdminRole record);

    int insertSelective(TAdminRole record);

    List<TAdminRole> selectByExample(TAdminRoleExample example);

    @Select({
        "select",
        "id, role_id, admin_id",
        "from t_admin_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    @ResultMap("BaseResultMap")
    TAdminRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TAdminRole record, @Param("example") TAdminRoleExample example);

    int updateByExample(@Param("record") TAdminRole record, @Param("example") TAdminRoleExample example);

    int updateByPrimaryKeySelective(TAdminRole record);

    @Update({
        "update t_admin_role",
        "set role_id = #{roleId,jdbcType=CHAR},",
          "admin_id = #{adminId,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TAdminRole record);
}