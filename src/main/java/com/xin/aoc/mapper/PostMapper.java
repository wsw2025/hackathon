package com.xin.aoc.mapper;

import com.xin.aoc.form.PostForm;
import com.xin.aoc.model.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostMapper {
    @Insert("insert into posts (title, content, post_date, likes, image, video, user_id) " +
            "values (#{title},#{content},#{postDate},#{likes},#{image},#{video},#{userId})")
    boolean insert(PostForm post);

    @Select("SELECT * FROM posts" +
            " WHERE" +
            " UPPER(title) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " OR UPPER(content) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " order by post_date desc"
    )
    List<Post> getPostsByKey(String key);

    @Select("SELECT * FROM posts" +
            " WHERE" +
            " UPPER(title) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " OR UPPER(content) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " and user_id=#{id}" +
            " order by post_date desc"
    )
    List<Post> getPostsByKeyUserId(String key, int id);

    @Select("SELECT * FROM posts where post_id=#{postId}")
    PostForm getPostById(int id);

    @Select("SELECT * FROM posts where user_id=#{id}")
    List<Post> getPostByUserId(int id);

    @Select("select * from posts " +
            "order by post_date desc")
    List<Post> getPosts();

    @Delete("delete from posts where post_id=#{postId}")
    void del(int postId);


    @Update("update posts set content=#{content}, title=#{title}, post_date=#{postDate} " +
            "where post_id=#{postId}")
    boolean update(PostForm post);
}
