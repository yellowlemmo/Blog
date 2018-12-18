package com.cui.blog.demo.controller;

import com.cui.blog.demo.Repository.ArticleRepository;
import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/blog")
public class BolgController extends BaseController {

    /**
     * 博客Service接口
     */
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add")
    public String addNewBlog(){

        return "addBlog";
    }

    @PostMapping(value = "/save")
    public String saveBlog(Article article){
        if (article == null){
            return "error";
        }
        article.setUser((User)SpringSecurityUtil.currentUser(session));
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        article.setCreateDate(createTime);
        articleService.saveBlog(article);
        return "index";
    }
}
