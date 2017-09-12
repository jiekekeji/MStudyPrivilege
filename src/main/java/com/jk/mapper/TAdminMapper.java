package com.jk.mapper;

import com.jk.pojo.TAdmin;
import com.jk.pojo.TAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TAdminMapper {
    int countByExample(TAdminExample example);

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

    int insertSelective(TAdmin record);

    List<TAdmin> selectByExample(TAdminExample example);

    @Select({
        "select",
        "id, name, remarks, phone, qq",
        "from t_admin",
        "where id = #{id,jdbcType=CHAR}"
    })
    @ResultMap("BaseResultMap")
    TAdmin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TAdmin record, @Param("example") TAdminExample example);

    int updateByExample(@Param("record") TAdmin record, @Param("example") TAdminExample example);

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