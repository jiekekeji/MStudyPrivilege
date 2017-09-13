package com.jk.tbmapper;

import com.jk.tbpojo.TGroup;
import com.jk.tbpojo.TGroupExample;
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

public interface TGroupMapper {
    @SelectProvider(type=TGroupSqlProvider.class, method="countByExample")
    int countByExample(TGroupExample example);

    @DeleteProvider(type=TGroupSqlProvider.class, method="deleteByExample")
    int deleteByExample(TGroupExample example);

    @Delete({
        "delete from t_group",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_group (id, name, remarks)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{remarks,jdbcType=VARCHAR})"
    })
    int insert(TGroup record);

    @InsertProvider(type=TGroupSqlProvider.class, method="insertSelective")
    int insertSelective(TGroup record);

    @SelectProvider(type=TGroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    List<TGroup> selectByExample(TGroupExample example);

    @Select({
        "select",
        "id, name, remarks",
        "from t_group",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    TGroup selectByPrimaryKey(String id);

    @UpdateProvider(type=TGroupSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TGroup record, @Param("example") TGroupExample example);

    @UpdateProvider(type=TGroupSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TGroup record, @Param("example") TGroupExample example);

    @UpdateProvider(type=TGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TGroup record);

    @Update({
        "update t_group",
        "set name = #{name,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TGroup record);
}