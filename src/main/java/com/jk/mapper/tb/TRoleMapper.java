package com.jk.mapper.tb;

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

import com.jk.pojo.tb.TRole;
import com.jk.pojo.tb.TRoleExample;

public interface TRoleMapper {
    @SelectProvider(type=TRoleSqlProvider.class, method="countByExample")
    int countByExample(TRoleExample example);

    @DeleteProvider(type=TRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(TRoleExample example);

    @Delete({
        "delete from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_role (id, name, remarks)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{remarks,jdbcType=VARCHAR})"
    })
    int insert(TRole record);

    @InsertProvider(type=TRoleSqlProvider.class, method="insertSelective")
    int insertSelective(TRole record);

    @SelectProvider(type=TRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    List<TRole> selectByExample(TRoleExample example);

    @Select({
        "select",
        "id, name, remarks",
        "from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    TRole selectByPrimaryKey(String id);

    @UpdateProvider(type=TRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    @UpdateProvider(type=TRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    @UpdateProvider(type=TRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TRole record);

    @Update({
        "update t_role",
        "set name = #{name,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TRole record);
}