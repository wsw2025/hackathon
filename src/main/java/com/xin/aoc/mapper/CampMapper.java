package com.xin.aoc.mapper;

import com.xin.aoc.form.CampForm;
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
            " WHERE" +
            " UPPER(title) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " OR UPPER(category) LIKE UPPER(CONCAT('%', #{key}, '%'))" +
            " OR UPPER(content) LIKE UPPER(CONCAT('%', #{key}, '%'))"
    )
    List<Camp> getCampsByKey(String key);

    @Select("select * from camps where camp_id=#{id}")
    Camp getCampById(int id);

    @Select("SELECT AVG(rating) AS average_rating FROM ratings WHERE camp_id = #{campId}")
    int getRatingCampById(int campId);

    @Select("select * from camps order by rating desc")
    List<Camp> getCampsByRating();


    @Insert("insert into camps (title, content, category, contact, location, camp_date) " +
            "values (#{title},#{content},#{category},#{contact},#{location},#{campDate})")
    void addCamp(CampForm camp);

    @Update("update camps set title=#{title}, content=#{content}, contact=#{contact}, location=#{location}, camp_date=#{campDate}, category=#{category} " +
            "where camp_id=#{campId}")
    boolean editCamp(Camp camp);
    @Select("select * from camps where title=#{title}")
    Camp getCampsByTitle(String title);

    @Delete("delete from camps "+
            "where camp_id=#{campId}")
    boolean delById(int campId);

    @Update("update camps set rating=#{rating} " +
            "where camp_id=#{campId}")
    boolean updateCampRating(int rating, int campId);
}
