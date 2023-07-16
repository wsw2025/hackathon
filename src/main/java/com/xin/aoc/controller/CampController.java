package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.mapper.RatingMapper;
import com.xin.aoc.mapper.SubmissionMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.Submission;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.*;

import com.xin.aoc.service.impl.CompilerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jsqlparser.expression.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CampController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProblemService problemService;
    @Autowired
    private IndexController indexController;
    @Autowired
    private RecordService recordService;
    @Autowired
    private CompilerService compilerService;
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private SubmissionMapper submissionMapper;
    @Autowired
    private CampMapper campMapper;
    @Autowired
    private RatingMapper ratingMapper;

    @GetMapping(value="/camp")
    public String add(@RequestParam(required=false,value="id") int id, Model model, HttpServletRequest request){
        Camp camp = campMapper.getCampById(id);

        model.addAttribute("campInfo", camp);
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        int rate = 0;

        if(user!=null) {
            if(ratingMapper.checkExist(user.getUserId(),id)>0){
                rate = ratingMapper.getRatingByUser(user.getUserId(),id);
            }
        }
        model.addAttribute("rating",rate);
        model.addAttribute("unrating",10-rate);
        camp.setUnrating(10-camp.getRating());
        return "camp";
    }

}