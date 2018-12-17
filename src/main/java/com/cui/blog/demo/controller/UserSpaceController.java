package com.cui.blog.demo.controller;

import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import com.cui.blog.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/user")
public class UserSpaceController extends BaseController {

    @RequestMapping(value = "/userspace")
    public String userSpace(Model model,Pageable pageable) throws Exception {
        User user = getCurrrentUser();
        model.addAttribute("user",user);
        return "userHome";
    }
}
