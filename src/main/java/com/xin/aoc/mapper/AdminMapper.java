package com.xin.aoc.mapper;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import org.apache.ibatis.annotations.Insert;

public interface AdminMapper {
    @Insert("insert into problems (cur_date, problem, answer) values (#{cur_date},#{problem},#{answer})")
    void insert(ProblemForm problem);
}
