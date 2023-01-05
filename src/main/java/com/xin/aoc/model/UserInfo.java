package com.xin.aoc.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


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
