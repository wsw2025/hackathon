package com.xin.aoc.mapper;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CampMapper {
    @Select("select * from camps")
    List<Camp> getCamps();

    @Select("SELECT * FROM camps" +
            "  WHERE" +
            "  UPPER(title) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            "  OR UPPER(category) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            "  OR UPPER(content) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            ")"
    )
    List<Camp> getCampsByKey(String key);

    @Select("select * from camps where camp_id=#{id}")
    Camp getCampById(int id);
}
