package com.xin.aoc.model;

import lombok.Data;

@Data
public class Comment {
    private int userId;
    private int commentId;
    private int postId;
    private int likes;
    private String content;
    private String postDate;
    private String username;
    private String userImage;
    private boolean liked;
}
