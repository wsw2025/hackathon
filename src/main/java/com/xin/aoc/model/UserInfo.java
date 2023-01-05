package com.xin.aoc.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Setter
@Getter
public class UserInfo {
    private int userId;

    @NotEmpty(message="username can't be empty")
    @Length(min = 2, max = 32, message = "username length must be 2-15 characters long")
    private String userName;

    @NotEmpty(message="username can't be empty")
    @Length(min = 2, max = 32, message = "username length must be 2-15 characters long")
    private String password;

    private String email;
    private String nickName;
    private int isAdmin;
}
