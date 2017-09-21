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

import com.jk.tb.pojo.TRoleRes;
import com.jk.tb.pojo.TRoleResExample;

public interface TRoleResMapper {
    @SelectProvider(type=TRoleResSqlProvider.class, method="countByExample")
    int countByExample(TRoleResExample example);

    @DeleteProvider(type=TRoleResSqlProvider.class, method="deleteByExample")
    int deleteByExample(TRoleResExample example);

    @Delete({
        "delete from t_role_res",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_role_res (id, role_id, res_id)",
        "values (#{id,jdbcType=CHAR}, #{roleId,jdbcType=CHAR}, #{resId,jdbcType=CHAR})"
    })
    int insert(TRoleRes record);

    @InsertProvider(type=TRoleResSqlProvider.class, method="insertSelective")
    int insertSelective(TRoleRes record);

    @SelectProvider(type=TRoleResSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.CHAR),
        @Result(column="res_id", property="resId", jdbcType=JdbcType.CHAR)
    })
    List<TRoleRes> selectByExample(TRoleResExample example);

    @Select({
        "select",
        "id, role_id, res_id",
        "from t_role_res",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.CHAR),
        @Result(column="res_id", property="resId", jdbcType=JdbcType.CHAR)
    })
    TRoleRes selectByPrimaryKey(String id);

    @UpdateProvider(type=TRoleResSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TRoleRes record, @Param("example") TRoleResExample example);

    @UpdateProvider(type=TRoleResSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TRoleRes record, @Param("example") TRoleResExample example);

    @UpdateProvider(type=TRoleResSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TRoleRes record);

    @Update({
        "update t_role_res",
        "set role_id = #{roleId,jdbcType=CHAR},",
          "res_id = #{resId,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TRoleRes record);
}