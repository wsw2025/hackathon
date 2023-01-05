package com.xin.aoc.controller;

import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
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

    @GetMapping (value="/register")
    public String register(@ModelAttribute("UserInfo") @Validated UserForm user){
        return "register";

    }
    @PostMapping(value="/register")
    public String add(@ModelAttribute("UserInfo") @Validated UserForm user,BindingResult rs){
        logger.info("!!!!", user.toString());
        if (user.getUserName() != null && user.getPassword()  != null) {
            if (rs.hasErrors()) {
                for (ObjectError error : rs.getAllErrors()) {
                    System.out.println(error.getDefaultMessage());
                }
                return "register";
            }
            userInfoService.addUserInfo(user);
            logger.info(user.getPassword());
            return "redirect:user/index";
        }

        return "register";

    }
}
