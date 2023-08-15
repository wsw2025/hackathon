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
public class CollectController {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private CampMapper campMapper;

    @PostMapping("/user/collect")
    public String like(@RequestBody Map<String, Object> payload,
                       HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Integer value = (Integer) payload.get("value");
        int postId = Integer.parseInt((String) payload.get("postId"));
        if(user==null)  return "redirect:/community";

        Collect collect = new Collect();
        collect.setPostId(postId);
        collect.setUserId(user.getUserId());

        if(value==0) {
            //unlike
            collectMapper.uncollect(collect);
            System.out.println("post id: " +  postId);
        }else{
            //like
            collectMapper.collect(collect);
        }
        return "redirect:/community";
    }
}
