package com.jk.tb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.jk.tb.pojo.TAdminRole;
import com.jk.tb.pojo.TAdminRoleExample;

public interface TAdminRoleMapper {
    @SelectProvider(type=TAdminRoleSqlProvider.class, method="countByExample")
    int countByExample(TAdminRoleExample example);

    @DeleteProvider(type=TAdminRoleSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=TAdminRoleSqlProvider.class, method="insertSelective")
    int insertSelective(TAdminRole record);

    @SelectProvider(type=TAdminRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.CHAR),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.CHAR)
    })
    List<TAdminRole> selectByExample(TAdminRoleExample example);

    @Select({
        "select",
        "id, role_id, admin_id",
        "from t_admin_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.CHAR),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.CHAR)
    })
    TAdminRole selectByPrimaryKey(String id);

    @UpdateProvider(type=TAdminRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TAdminRole record, @Param("example") TAdminRoleExample example);

    @UpdateProvider(type=TAdminRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TAdminRole record, @Param("example") TAdminRoleExample example);

    @UpdateProvider(type=TAdminRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TAdminRole record);

    @Update({
        "update t_admin_role",
        "set role_id = #{roleId,jdbcType=CHAR},",
          "admin_id = #{adminId,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TAdminRole record);
}