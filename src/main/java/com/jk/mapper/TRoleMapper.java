package com.jk.mapper;

import com.jk.pojo.TRole;
import com.jk.pojo.TRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TRoleMapper {
    int countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    @Delete({
        "delete from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_role (id, name, desc)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR})"
    })
    int insert(TRole record);

    int insertSelective(TRole record);

    List<TRole> selectByExample(TRoleExample example);

    @Select({
        "select",
        "id, name, desc",
        "from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    @ResultMap("BaseResultMap")
    TRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByPrimaryKeySelective(TRole record);

    @Update({
        "update t_role",
        "set name = #{name,jdbcType=VARCHAR},",
          "desc = #{desc,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TRole record);
}