package com.xin.aoc.mapper;

import org.apache.ibatis.annotations.Select;
import com.xin.aoc.model.Problem;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProblemMapper {
    @Select("select problem_id, cur_date, problem, answer from problems where problem_id=#{id}")
    Problem getProblemById(String id);

    @Select("select * from problems")
    List<Problem> getAllProblems();

    @Update("update user_info set score=score+1 where user_id=#{id}")
    void addScoreById(int id);
}
