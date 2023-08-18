package com.xin.aoc.mapper;

import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.PostForm;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CollectMapper {
    @Insert("insert into collects (post_id, user_id) " +
            "values (#{postId},#{userId})")
    void collect(Collect collect);

    @Select("SELECT count(*) from collects WHERE user_id=#{userId} and post_id=#{postId}")
    int checkExist(int userId, int postId);

    @Delete("delete from collects "+
            "where user_id=#{userId} and post_id=#{postId}")
    void uncollect(Collect collect);


    @Select("SELECT * FROM posts" +
            " WHERE" +
            " UPPER(title) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " OR UPPER(content) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " and post_id in (#{postIds})" +
            " order by post_date desc"
    )
    List<Post> getPostByKeyPostIds(List<Integer> postIds);

    @Select({
            "SELECT * FROM posts",
            "WHERE post_id IN",
            "<foreach item='postId' collection='postIds' open='(' separator=',' close=')'>",
            "#{postId}",
            "</foreach>",
            "ORDER BY post_date DESC"
    })
    List<Post> getPostsByPostIds(@Param("postIds") List<Integer> postIds);

    @Select("SELECT post_id FROM collects where user_id=#{id}")
    List<Integer> getPostIdsByUserId(int id);
}
