package com.xin.aoc.mapper;

import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.Rating;
import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RatingMapper {
    @Insert("insert into ratings (camp_id, user_id, rating) " +
            "values (#{campId},#{userId},#{rating})")
    void addRating(Rating rate);

    @Select("SELECT count(*) from ratings WHERE user_id=#{userId} and camp_id=#{campId}")
    int checkExist(int userId, int campId);

    @Update("update ratings set rating=#{rating} " +
            "where user_id=#{userId} and camp_id=#{campId}")
    void update(Rating rate);

    @Update("update ratings set rating=rating+1 " +
            "where camp_id=#{campId}")
    void addRatingCount(int campId);

    @Select("select rating from ratings where user_id=#{userId} and camp_id=#{campId}")
    int getRatingByUser(int userId, int campId);

    @Select("SELECT count(*) from ratings WHERE camp_id=#{campId}")
    int getRatingCount(int campId);

}
