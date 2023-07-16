package com.xin.aoc.model;

import lombok.Data;

@Data
public class Discussion {
    private int discussionId;
    private String curDate;
    private int userId;
    private String nickName;
    private String image;
    private int campId;
    private String content;
    private int status;
    private int likeCount;
    private boolean liked;
}
