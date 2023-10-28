package com.xin.aoc.controller.core;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.ContactMapper;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class ContactController {

    @Autowired
    ContactMapper contactMapper;

    @GetMapping(value = "/contact")
    public String index(Model model, HttpServletRequest request) {
        return "contact";
    }

    @PostMapping(value = "/message")
    public String index(@RequestParam(value="email") String email, @RequestParam(value="name") String name,
                        @RequestParam(value="message") String message,
                        Model model, HttpServletRequest request) {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
        String time = currentTime.format(formatter);

        System.out.println(email);
        contactMapper.insert(name,email,message,time);
        return "contact";
    }
}
