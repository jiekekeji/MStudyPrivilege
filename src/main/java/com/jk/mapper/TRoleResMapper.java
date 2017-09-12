package com.jk.mapper;

import com.jk.pojo.TRoleRes;
import com.jk.pojo.TRoleResExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TRoleResMapper {
    int countByExample(TRoleResExample example);

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

    int insertSelective(TRoleRes record);

    List<TRoleRes> selectByExample(TRoleResExample example);

    @Select({
        "select",
        "id, role_id, res_id",
        "from t_role_res",
        "where id = #{id,jdbcType=CHAR}"
    })
    @ResultMap("BaseResultMap")
    TRoleRes selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRoleRes record, @Param("example") TRoleResExample example);

    int updateByExample(@Param("record") TRoleRes record, @Param("example") TRoleResExample example);

    int updateByPrimaryKeySelective(TRoleRes record);

    @Update({
        "update t_role_res",
        "set role_id = #{roleId,jdbcType=CHAR},",
          "res_id = #{resId,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TRoleRes record);
}