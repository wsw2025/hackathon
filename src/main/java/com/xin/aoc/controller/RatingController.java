package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.mapper.RatingMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Rating;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class RatingController {
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private CampMapper campMapper;
    @PostMapping("/rate")
    public String rateCamp(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request, Model model) {

        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        String valueStr = (String) payload.get("value");
        int campId = (int) payload.get("campId");

        int value = Integer.parseInt(valueStr);

        Rating rating = new Rating();
        rating.setCampId(campId);
        rating.setRating(value);
        rating.setUserId(user.getUserId());

        if(ratingMapper.checkExist(user.getUserId(), campId)!=0) {
            ratingMapper.update(rating);
            System.out.println("updated");
        }else{
            ratingMapper.addRating(rating);
            ratingMapper.addRatingCount(campId);
        }

        System.out.println(campMapper.getRatingCampById(campId));
        campMapper.updateCampRating(campMapper.getRatingCampById(campId),campId);
        return "redirect:/camp?id="+ campId;
    }
}
