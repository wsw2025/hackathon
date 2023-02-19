package com.xin.aoc.mapper;

import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface RecordMapper {
    @Insert("insert into records (cur_date, user_id, problem_id) values (#{curDate},#{userId},#{problemId})")
    void addRecord(@Param("curDate") String curDate,
                   @Param("userId") int userId,
                   @Param("problemId") int problemId);

    @Select("SELECT count(*) from records WHERE user_id=#{userId} and problem_id=#{problemId}")
    int checkExist(@Param("userId") int userId,
                   @Param("problemId") int problemId);

    @Select("SELECT problem_id from records WHERE user_id=#{userId}")
    ArrayList<Integer> findSolved(int user);
}
