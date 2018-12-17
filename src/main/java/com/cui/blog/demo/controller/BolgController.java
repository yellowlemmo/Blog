package com.cui.blog.demo.controller;

import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/blog")
public class BolgController extends BaseController {

    @RequestMapping(value = "/add")
    public String addNewBlog(){

        return "addBlog";
    }

    @PostMapping(value = "/save")
    public String saveBlog(Article article){
        if (article == null){
            return "error";
        }
        User user = SpringSecurityUtil.currentUser(session);
        article.setAuthor(user.getId());

        return "index";
    }
}
