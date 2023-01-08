package com.xin.aoc.service;

import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import org.h2.engine.User;

import java.util.List;

public interface UserInfoService {
    public UserInfo getUserInfo(String username, String password);

    public void addUserInfo(UserForm user);

    public List<UserInfo> getAllUserInfo();

}
