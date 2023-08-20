package com.xin.aoc.mapper;

import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.PostForm;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {
    @Insert("insert into comments (post_id, user_id, content, post_date) " +
            "values (#{postId},#{userId},#{content},#{postDate})")
    void insert(Comment comment);

    @Select("SELECT * FROM comments where post_id=#{id}")
    List<Comment> getComments(int id);
}
