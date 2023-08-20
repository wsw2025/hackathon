package com.xin.aoc.controller.main;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.form.PostForm;
import com.xin.aoc.mapper.*;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Comment;
import com.xin.aoc.model.Post;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.CompilerService;
import com.xin.aoc.service.ProblemService;
import com.xin.aoc.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommunityController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CollectMapper collectMapper;

    @GetMapping(value="/community")
    public String community(@RequestParam(required = false, defaultValue = "1", value = "page")
                            Integer page,
                            @RequestParam(required = false,  value = "key") String key,
                            Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        session.setAttribute("login_user", user);        if (page == null || page <= 0) page = 1;
        int size = 3;
        PageHelper.startPage(page, size);
        List<Post> posts = postMapper.getPosts();


        if(key!=null){
            System.out.println(key);
            posts = postMapper.getPostsByKey(key);
        }

        for(Post p:posts){
            p.setUsername(userInfoMapper.getUserName(p.getUserId()));
            p.setUserImage(userInfoMapper.getUserImage(p.getUserId()));
            p.setLikes(likeMapper.getLikeCount(p.getPostId()));
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
            List<Comment> comments = commentMapper.getComments(p.getPostId());
            for(Comment c:comments){
                c.setUsername(userInfoMapper.getUserName(c.getUserId()));
                c.setUserImage(userInfoMapper.getUserImage(c.getUserId()));
                c.setLikes(likeMapper.getCommentLikeCount(c.getCommentId()));
                if(user!=null && likeMapper.commentCheckExist(user.getUserId(),c.getCommentId())!=0){
                    c.setLiked(true);
                }else{
                    c.setLiked(false);
                }
            }
            List<String> images = new ArrayList<>();
            if(p.getImage()!=0){
                for (int i = 0; i < p.getImage(); i++) {
                    String directoryPath = "post_images"; // Replace with the actual directory path
                    File directory = new File(directoryPath);
                    File[] files = directory.listFiles();
                    String imageNamePrefix = p.getPostId() + "_" + i;
                    // Search for files with either .jpeg or .jpg extensions
                    boolean fileExists = false;
                    for (File file : files) {
                        if (file.isFile() && file.getName().startsWith(imageNamePrefix) && (file.getName().endsWith(".jpeg"))) {
                            fileExists = true;
                            break;
                        }
                    }
                    String imagePath = "";
                    if (fileExists) {
                         imagePath = "/post_images/" + p.getPostId() + "_" + i + ".jpeg?t=1692497993666"; ;
                    } else{
                         imagePath = "/post_images/" + p.getPostId() + "_" + i + ".png?t=1692497993666"; ;
                    }
                    images.add(imagePath);
                    logger.info(imagePath+" <-- this is the actual image path");
                }
            }
            p.setComments(comments);
            p.setImages(images);
            logger.info("image count: "+ p.getImage());
        }

        PageInfo<Post> pageInfo = new PageInfo<Post>(posts, size);
        model.addAttribute("pageInfo", pageInfo);
        return "community";
    }


}
