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

import com.jk.tb.pojo.TResources;
import com.jk.tb.pojo.TResourcesExample;

public interface TResourcesMapper {
    @SelectProvider(type=TResourcesSqlProvider.class, method="countByExample")
    int countByExample(TResourcesExample example);

    @DeleteProvider(type=TResourcesSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=TResourcesSqlProvider.class, method="insertSelective")
    int insertSelective(TResources record);

    @SelectProvider(type=TResourcesSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.CHAR)
    })
    List<TResources> selectByExample(TResourcesExample example);

    @Select({
        "select",
        "id, name, url, group_id",
        "from t_resources",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.CHAR)
    })
    TResources selectByPrimaryKey(String id);

    @UpdateProvider(type=TResourcesSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TResources record, @Param("example") TResourcesExample example);

    @UpdateProvider(type=TResourcesSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TResources record, @Param("example") TResourcesExample example);

    @UpdateProvider(type=TResourcesSqlProvider.class, method="updateByPrimaryKeySelective")
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