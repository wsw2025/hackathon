package com.xin.aoc.controller.core;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.SubscribeMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.UserInfo;
//import com.xin.aoc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class SubscribeController {
    @Autowired
    SubscribeMapper subscribeMapper;
    @PostMapping(value = "/subscribe")
    public String index(@RequestParam(value="email") String email,
                        Model model, HttpServletRequest request) {
        System.out.println(email);
        subscribeMapper.insert(email);
        return "index";
    }
}
