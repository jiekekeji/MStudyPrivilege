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

import com.jk.pojo.tb.TAdmin;
import com.jk.pojo.tb.TAdminExample;

public interface TAdminMapper {
    @SelectProvider(type=TAdminSqlProvider.class, method="countByExample")
    int countByExample(TAdminExample example);

    @DeleteProvider(type=TAdminSqlProvider.class, method="deleteByExample")
    int deleteByExample(TAdminExample example);

    @Delete({
        "delete from t_admin",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_admin (id, name, remarks, ",
        "phone, qq)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{remarks,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=CHAR}, #{qq,jdbcType=VARCHAR})"
    })
    int insert(TAdmin record);

    @InsertProvider(type=TAdminSqlProvider.class, method="insertSelective")
    int insertSelective(TAdmin record);

    @SelectProvider(type=TAdminSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR)
    })
    List<TAdmin> selectByExample(TAdminExample example);

    @Select({
        "select",
        "id, name, remarks, phone, qq",
        "from t_admin",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR)
    })
    TAdmin selectByPrimaryKey(String id);

    @UpdateProvider(type=TAdminSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TAdmin record, @Param("example") TAdminExample example);

    @UpdateProvider(type=TAdminSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TAdmin record, @Param("example") TAdminExample example);

    @UpdateProvider(type=TAdminSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TAdmin record);

    @Update({
        "update t_admin",
        "set name = #{name,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=CHAR},",
          "qq = #{qq,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(TAdmin record);
}