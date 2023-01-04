package com.xin.aoc.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserInfo {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String nickName;
    private int isAdmin;
}
