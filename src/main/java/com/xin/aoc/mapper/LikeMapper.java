package com.xin.aoc.mapper;

import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LikeMapper {
    @Insert("insert into likes (discussion_id, user_id) " +
            "values (#{discussionId},#{userId})")
    void like(Like like);

    @Select("SELECT count(*) from likes WHERE user_id=#{userId} and discussion_id=#{discussionId}")
    int checkExist(int userId, int discussionId);

    @Delete("delete from likes "+
            "where user_id=#{userId} and discussion_id=#{discussionId}")
    void unlike(Like like);


    @Update("update discussions set like_count=like_count-1 " +
            "where discussion_id=#{discussionId}")
    void minusLikeCount(int discussionId);

    @Update("update discussions set like_count=like_count+1 " +
            "where discussion_id=#{discussionId}")
    void addLikeCount(int discussionId);
}
