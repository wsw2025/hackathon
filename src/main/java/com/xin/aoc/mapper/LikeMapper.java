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

    @Insert("insert into comment_likes (comment_id, user_id) " +
            "values (#{commentId},#{userId})")
    void commentLike(CommentLike like);

    @Select("SELECT count(*) from likes WHERE user_id=#{userId} and post_id=#{postId}")
    int checkExist(int userId, int postId);

    @Select("SELECT count(*) from comment_likes WHERE user_id=#{userId} and comment_id=#{commentId}")
    int commentCheckExist(int userId, int commentId);

    @Select("select count(*) from likes where post_id=#{postId}")
    int getLikeCount(int postId);
    @Select("select count(*) from comment_likes where comment_id=#{commentId}")
    int getCommentLikeCount(int commentId);

    @Delete("delete from likes "+
            "where user_id=#{userId} and post_id=#{postId}")
    void unlike(Like like);

    @Delete("delete from comment_likes "+
            "where user_id=#{userId} and comment_id=#{commentId}")
    void commentUnlike(CommentLike like);

    @Update("update posts set likes=likes-1 " +
            "where post_id=#{postId}")
    void minusLikeCount(int postId);

    @Update("update posts set likes=likes+1 " +
            "where post_id=#{postId}")
    void addLikeCount(int postId);
}
