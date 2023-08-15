package com.xin.aoc.form;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Data
public class PostForm {
    private int postId;

    @Length(min = 1, max = 50, message = "title length must be 1-100 characters long")
    private String title;

    @Length(min = 1, max = 500000000, message = "content length must be 3-500000000 characters long")
    private String content;

    private String postDate;
    private int userId;
    private int image;
    private int video;
    private int likes = 0;
}
