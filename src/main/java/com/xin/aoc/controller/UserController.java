package com.xin.aoc.controller;

import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String login(@RequestParam(required=false,value="username") String username,
                        @RequestParam(required=false,value="password") String password,
                        HttpServletRequest request) {
        if (username != null && password != null) {
            UserInfo userInfo = userInfoService.getUserInfo(username, password);
            if (userInfo != null) {
                userInfo.setPassword("");
                HttpSession session = request.getSession();
                session.setAttribute("login_user", userInfo);
                return "redirect:/";
            }
            request.setAttribute("msg", "username or password is error!");
        }
        return "login";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }


//    @RequestMapping("/register")
//    public String register(@ModelAttribute("UserInfo") UserInfo user){
//        if (user.getUserName() != null && user.getPassword()  != null) {
//
//        }
//        return "/register";
//    }
    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String add(@ModelAttribute("UserInfo") @Validated UserInfo user,BindingResult rs){
        if(rs.hasErrors()){
            for (ObjectError error : rs.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "redirect:/register";
        }
        userInfoService.addUserInfo(user);
        System.out.println(user.getPassword());
        return "user/index";
    }
}
