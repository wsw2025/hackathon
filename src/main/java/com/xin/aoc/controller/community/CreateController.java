package com.xin.aoc.controller.community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.PostForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.mapper.CollectMapper;
import com.xin.aoc.mapper.LikeMapper;
import com.xin.aoc.mapper.PostMapper;
import com.xin.aoc.mapper.UserInfoMapper;
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
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CreateController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CollectMapper collectMapper;
    @GetMapping(value="/user/write")
    public String community(
                            @ModelAttribute("PostForm") PostForm post
                            ){
        return "community/write";
    }

    private void handleImageUpload(List<MultipartFile> uploadedFiles, int postId) {
        String UPLOADED_FOLDER = "./post_images/";
        File folder = new File(UPLOADED_FOLDER);
        if (!folder.exists())
            folder.mkdirs();
        int index=0;
        for (MultipartFile file : uploadedFiles) {
            if (!file.isEmpty()) {
                // Process the image file
                String originalFilename = file.getOriginalFilename();
                int dotIndex = originalFilename.lastIndexOf(".");
                String fileExtension = originalFilename.substring(dotIndex);
                File newFile = new File(folder, postId + "_" + index + fileExtension);

                if (newFile.exists()) {
                    newFile.delete();
                }
                try {
                    byte[] bytes = file.getBytes();
                    String url = "/post_images/" + postId + "_" + index + fileExtension + "?t=" + System.currentTimeMillis();
                    Files.write(newFile.toPath(), bytes);
                    index++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logger.info(originalFilename+"profile_pic name");
            }
        }
    }
    @PostMapping(value="/user/write")
    public String communitywrite(@ModelAttribute("PostForm") @Validated PostForm post,
                                 BindingResult rs, MultipartHttpServletRequest request, Model model){

        if (rs.hasErrors()) {
            for (ObjectError error : rs.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "community/write";
        }

        //set time, userid
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedDate = date.format(formatter);;
        post.setPostDate(formattedDate);
        post.setUserId(user.getUserId());

        //add post to database;
        postMapper.insert(post);
        int postId = post.getPostId();
        logger.info("new post id:"+ postId);

        MultiValueMap<String, MultipartFile> fileMap = request.getMultiFileMap();
        List<MultipartFile> uploadedFiles = fileMap.get("images");
        logger.info(uploadedFiles.size() + " <-- number of images");

        if (uploadedFiles != null && !uploadedFiles.isEmpty() && !uploadedFiles.get(0).isEmpty()) {
            logger.info("image files not null!");
            postMapper.addImg(postId, uploadedFiles.size());
            handleImageUpload(uploadedFiles, postId);
        }

        model.addAttribute("write_msg", "Uploading succeeded!");
        return "community/write";
    }

    @GetMapping(value="/user/edit")
    public String editPst(@RequestParam(value="id") int id,
                         @ModelAttribute("PostForm") PostForm post,
                          Model model, HttpServletRequest request){
        Post old = postMapper.getPostById(id);
        logger.info("!!!!" + old.getTitle());
        post.setPostId(old.getPostId());
        post.setContent(old.getContent());
        post.setTitle(old.getTitle());

        model.addAttribute("title", old.getTitle());
        model.addAttribute("content", old.getContent());
        model.addAttribute("postId", old.getPostId());
        return "community/edit";
    }


    @PostMapping("/user/edit")
    public String editpost(@RequestParam(value="id") int id,
                           @ModelAttribute("PostForm") @Validated PostForm post,
                           BindingResult rs, HttpServletRequest request, Model model) {
        if (rs.hasErrors()) {
            logger.info("post content invalid");
            return "community/edit";
        }
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedDate = date.format(formatter);;
        post.setPostDate(formattedDate);
        post.setPostId(id);

        if (postMapper.update(post)){
            logger.info("edit succeed");
            model.addAttribute("edit_msg", "Editing succeeded!");
        } else {
            logger.info("edit failed");
            model.addAttribute("edit_msg", "Editing failed!");
        }
        return "community/edit";
    }


    @GetMapping(value="/user/posts")
    public String community(@RequestParam(required = false, defaultValue = "1", value = "page")
                            Integer page,
                            @RequestParam(required = false,  value = "key") String key,
                            Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        session.setAttribute("login_user", user);
        if (page == null || page <= 0) page = 1;
        int size = 3;
        PageHelper.startPage(page, size);
        List<Post> posts = postMapper.getPostByUserId(user.getUserId());
        if(key!=null){
            System.out.println(key);
            posts = postMapper.getPostsByKeyUserId(key,user.getUserId());
        }
        for(Post p:posts){
            p.setUsername(userInfoMapper.getUserName(p.getUserId()));
            p.setUserImage(userInfoMapper.getUserImage(p.getUserId()));
            if(user!=null && likeMapper.checkExist(user.getUserId(),p.getPostId())!=0){
                p.setLiked(true);
            }else{
                p.setLiked(false);
            }
            if(user!=null && collectMapper.checkExist(user.getUserId(),p.getPostId())!=0){
                p.setCollected(true);
            }else{
                p.setCollected(false);
            }
        }

        PageInfo<Post> pageInfo = new PageInfo<Post>(posts, size);
        model.addAttribute("pageInfo", pageInfo);
        return "community/posts";
    }
}
