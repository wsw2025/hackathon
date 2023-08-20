package com.xin.aoc.controller.community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.mapper.LikeMapper;
import com.xin.aoc.mapper.RatingMapper;
import com.xin.aoc.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@Controller
public class LikeController {
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CampMapper campMapper;

    @PostMapping("/user/like")
    public String like(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Integer value = (Integer) payload.get("value");
        int postId = Integer.parseInt((String) payload.get("postId"));

        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(user.getUserId());

        if(value==0) {
            //unlike
            likeMapper.unlike(like);
            System.out.println("unliked " +  postId);
        }else{
            //like
            if(likeMapper.checkExist(user.getUserId(), postId)==0){
               System.out.println(user.getUserId() + " "+ postId+" "+likeMapper.checkExist(user.getUserId(),postId));
               likeMapper.like(like);
            }
            System.out.println(likeMapper.checkExist(2, 6));

        }
        return "redirect:/community";
    }



    @PostMapping("/user/comment_like")
    public String commentlike(@RequestBody Map<String, Object> payload,
                       HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Integer value = (Integer) payload.get("value");
        int commentId = Integer.parseInt((String) payload.get("commentId"));

        CommentLike like = new CommentLike();
        like.setCommentId(commentId);
        like.setUserId(user.getUserId());

        if(value==0) {
            //unlike
            likeMapper.commentUnlike(like);
        }else{
            //like
            if(likeMapper.commentCheckExist(user.getUserId(), commentId)==0){
                likeMapper.commentLike(like);
            }
        }
        return "community";
    }
}
