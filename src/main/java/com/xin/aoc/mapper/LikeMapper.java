package com.xin.aoc.mapper;

import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LikeMapper {
    @Insert("insert into likes (post_id, user_id) " +
            "values (#{postId},#{userId})")
    void like(Like like);

    @Select("SELECT count(*) from likes WHERE user_id=#{userId} and post_id=#{postId}")
    int checkExist(int userId, int postId);

    @Delete("delete from likes "+
            "where user_id=#{userId} and post_id=#{postId}")
    void unlike(Like like);

    @Update("update posts set likes=likes-1 " +
            "where post_id=#{postId}")
    void minusLikeCount(int postId);

    @Update("update posts set likes=likes+1 " +
            "where post_id=#{postId}")
    void addLikeCount(int postId);
}
