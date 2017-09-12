package com.jk.mapper;

import com.jk.pojo.TResources;
import com.jk.pojo.TResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TResourcesMapper {
    int countByExample(TResourcesExample example);

    int deleteByExample(TResourcesExample example);

    @Delete({
        "delete from t_resources",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_resources (id, name, url, ",
        "group_id)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{groupId,jdbcType=CHAR})"
    })
    int insert(TResources record);

    int insertSelective(TResources record);

    List<TResources> selectByExample(TResourcesExample example);

    @Select({
        "select",
        "id, name, url, group_id",
        "from t_resources",
        "where id = #{id,jdbcType=CHAR}"
    })
    @ResultMap("BaseResultMap")
    TResources selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TResources record, @Param("example") TResourcesExample example);

    int updateByExample(@Param("record") TResources record, @Param("example") TResourcesExample example);

    int updateByPrimaryKeySelective(TResources record);

    @Update({
        "update t_resources",
        "set name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TResources record);
}