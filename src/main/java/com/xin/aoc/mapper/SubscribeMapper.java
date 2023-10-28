package com.xin.aoc.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SubscribeMapper {

    @Insert("insert into emails (email) values (#{email})")
    void insert(String email);

}
