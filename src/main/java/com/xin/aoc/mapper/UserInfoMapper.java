package com.xin.aoc.mapper;

import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.h2.engine.User;

import java.util.List;

public interface UserInfoMapper {
    @Select("select * from user_info where " +
            "user_name=#{userName} and password=#{password}")
    public UserInfo getUserInfo(@Param("userName") String username,
                                @Param("password") String password);

    @Insert("insert into user_info (user_name, nick_name, email, password) values (#{userName},#{nickName},#{email},#{password})")
    void insert(UserForm user);

    @Select("select user_name, score from user_info order by score desc")
    public List<UserInfo> getAllUserInfo();
}
