package com.xin.aoc.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class Post {
    private int postId;
    private String img;
    private String date;
    private String category;
}
