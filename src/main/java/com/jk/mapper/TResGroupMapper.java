package com.jk.mapper;

import com.jk.pojo.TResGroup;
import com.jk.pojo.TResGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TResGroupMapper {
    int countByExample(TResGroupExample example);

    int deleteByExample(TResGroupExample example);

    @Delete({
        "delete from t_res_group",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_res_group (id, name, desc)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR})"
    })
    int insert(TResGroup record);

    int insertSelective(TResGroup record);

    List<TResGroup> selectByExample(TResGroupExample example);

    @Select({
        "select",
        "id, name, desc",
        "from t_res_group",
        "where id = #{id,jdbcType=CHAR}"
    })
    @ResultMap("BaseResultMap")
    TResGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TResGroup record, @Param("example") TResGroupExample example);

    int updateByExample(@Param("record") TResGroup record, @Param("example") TResGroupExample example);

    int updateByPrimaryKeySelective(TResGroup record);

    @Update({
        "update t_res_group",
        "set name = #{name,jdbcType=VARCHAR},",
          "desc = #{desc,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TResGroup record);
}