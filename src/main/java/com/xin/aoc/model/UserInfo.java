package com.xin.aoc.model;

import lombok.Data;

@Data
public class UserInfo {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String nickName;
    private int isAdmin;
    public int score;
}
