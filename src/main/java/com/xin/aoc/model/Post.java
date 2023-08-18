package com.xin.aoc.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Post {
    private int postId;
    private String title;
    private String content;
    private String postDate;
    private int userId;
    private int image;
    private int video;
    private int likes;
    private String username;
    private String userImage;
    private boolean liked;
    private boolean collected;
}
