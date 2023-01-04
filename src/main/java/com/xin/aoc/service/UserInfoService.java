package com.xin.aoc.service;

import com.xin.aoc.model.UserInfo;

public interface UserInfoService {
    public UserInfo getUserInfo(String username, String password);

    void addUserInfo (String name, String email, String password, String nickname);
}
