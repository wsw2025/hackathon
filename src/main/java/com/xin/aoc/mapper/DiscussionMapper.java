package com.xin.aoc.mapper;

import com.xin.aoc.model.Discussion;
import com.xin.aoc.model.Problem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DiscussionMapper {

    @Select("select * from discussions where problem_id=#{id}")
    List<Discussion> getDiscussionById(int id);
}
