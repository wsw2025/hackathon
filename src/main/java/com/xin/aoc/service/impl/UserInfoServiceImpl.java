package com.xin.aoc.service.impl;

import com.xin.aoc.controller.UserController;
import com.xin.aoc.mapper.UserInfoMapper;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(String username) {
        return userInfoMapper.getUserInfo(username);
    }

    public boolean addUserInfo(UserInfo user) {
        try {
            userInfoMapper.insert(user);
            return true;
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return false;
    }

    public boolean changeUserInfo(UserInfo user) {
        try {
            userInfoMapper.change(user);
            return true;
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return false;
    }

    public List<UserInfo> getAllUserInfo(){
        return userInfoMapper.getAllUserInfo();
    }
}
