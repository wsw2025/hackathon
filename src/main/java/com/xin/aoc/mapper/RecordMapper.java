package com.xin.aoc.mapper;

import com.xin.aoc.form.UserForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RecordMapper {
    @Insert("insert into records (cur_date, user_id, problem_id) values (#{curDate},#{userId},#{problemId})")
    void addRecord(@Param("curDate") String curDate,
                   @Param("userId") int userId,
                   @Param("problemId") int problemId);

    @Select("SELECT count(*) from records WHERE user_id=#{userId} and problem_id=#{problemId}")
    int checkExist(@Param("userId") int userId,
                   @Param("problemId") int problemId);
}
