package com.xin.aoc.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserForm {
    @Length(min = 2, max = 32, message = "username length must be 2-15 characters long")
    private String userName;

    @NotEmpty(message="password can't be empty")
    @Length(min = 2, max = 32, message = "password length must be 2-15 characters long")
    private String password;

    @Email(message = "not is email.")
    private String email;

    @Length(min = 2, max = 32, message = "nickName length must be 2-15 characters long")
    private String nickName;
}
