package com.xin.aoc.service.impl;

import com.xin.aoc.form.UserForm;
import com.xin.aoc.mapper.UserInfoMapper;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(String username, String password) {
        return userInfoMapper.getUserInfo(username, password);
    }

    public void addUserInfo(UserForm user) {
        userInfoMapper.insert(user);
    }
}
