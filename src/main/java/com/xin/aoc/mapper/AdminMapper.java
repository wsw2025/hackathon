package com.xin.aoc.mapper;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    @Insert("insert into problems (title, problem, answer, in_put, category, difficulty) values (#{title},#{problem},#{answer},#{input},#{category},#{difficulty})")
    void insert(ProblemForm problem);

    @Update("update problems set title=#{title}, problem=#{problem}, difficulty=#{difficulty}, answer=#{answer}, in_put=#{input}, category=#{category} " +
            "where problem_id=#{problemId}")
    void changeAll(Problem problem);

    @Delete("delete from problems "+
            "where problem_id=#{problemId}")
    void delById(int id);
}
