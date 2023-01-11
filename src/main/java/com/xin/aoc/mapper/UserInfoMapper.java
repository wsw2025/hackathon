package com.xin.aoc.mapper;

import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserInfoMapper {
    @Select("select * from user_info where user_name=#{userName}")
    public UserInfo getUserInfo(@Param("userName") String username);

    @Insert("insert into user_info (user_name, nick_name, email, password) " +
            "values (#{userName},#{nickName},#{email},#{password})")
    void insert(UserInfo user);

    @Update("update user_info set password=#{password} " +
            "where user_name=#{userName}")
    void change(UserInfo user);

    @Select("select user_name, score from user_info order by score desc")
    public List<UserInfo> getAllUserInfo();
}
