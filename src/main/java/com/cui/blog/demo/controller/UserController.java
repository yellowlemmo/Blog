package com.cui.blog.demo.controller;

import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveuser")
    public String saveUser(User user){
        System.out.println(user);
        String md5Password = Utils.BCryptUtil(user.getPassword());
        user.setPassword(md5Password);
        userService.createUser(user);
        return "login";
    }
}
