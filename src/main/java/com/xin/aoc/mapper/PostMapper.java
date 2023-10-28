package com.xin.aoc.mapper;

import com.xin.aoc.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    @Insert("insert into posts (date, category, img) values (#{date},#{category},#{img})")
    void insert(String date, String category, String img);

    @Select("SELECT * FROM posts" )
    List<Post> getPosts();
}
