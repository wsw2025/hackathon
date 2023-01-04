package com.xin.aoc.mapper;

import com.xin.aoc.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper {
    @Select("select * from user_info where " +
            "user_name=#{userName} and password=#{password}")
    public UserInfo getUserInfo(@Param("userName") String username,
                                @Param("password") String password);

    @Insert("insert into user_info (user_name, email, password, nick_name) values (#{userName},#{email},#{password},#{nickName})")
    void insert(UserInfo User);
}
