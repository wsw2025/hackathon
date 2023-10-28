package com.xin.aoc.controller.core;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request) {
        return "index";
    }
}
