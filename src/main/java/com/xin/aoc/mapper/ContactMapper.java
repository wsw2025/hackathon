package com.xin.aoc.mapper;

import org.apache.ibatis.annotations.Insert;

public interface ContactMapper {

    @Insert("insert into messages (name, email, message,time) values (#{name},#{email},#{message},#{time})")
    void insert(String name, String email, String message, String time);

}
