package com.xin.aoc.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
public class UserForm {
    @Length(min = 2, max = 32, message = "username length must be 2-15 characters long")
    private String userName;

    @Length(min = 2, max = 32, message = "password length must be 2-15 characters long")
    private String password;

    @Email(message="email not valid")
    private String email;

    @Length(min = 2, max = 32, message = "password length must be 2-15 characters long")
    private String nickName;

}
