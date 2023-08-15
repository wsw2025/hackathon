package com.xin.aoc.controller.community;

import com.xin.aoc.mapper.PostMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.PostForm;
import com.xin.aoc.mapper.PostMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Post;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.CompilerService;
import com.xin.aoc.service.ProblemService;
import com.xin.aoc.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Controller
public class PostController {

    @Autowired
    private PostMapper postMapper;

    @RequestMapping(value="/user/del")
    public String post( @RequestParam(required=false,value="id") int postId,
                        @RequestParam(required=false,value="user_id") int userId,
                        HttpServletRequest request,
                        Model model
    ){

        postMapper.del(postId);
        return "redirect:/community";
    }
}
