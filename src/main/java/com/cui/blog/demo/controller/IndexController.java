package com.cui.blog.demo.controller;

import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Service.RoleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    /**
     * 用户角色service接口
     */
    @Autowired
    private RoleService roleService;

    /**
     * 用户service接口
     */
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    /**
     * 博客service接口
     */
    @Autowired
    private ArticleService articleService;


    @RequestMapping("/index")
    public String index(Model model){
        PageRequest pageable = PageRequest.of(0,5);
        Page page = articleService.findAllBlog(pageable);
        model.addAttribute("page",page);
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(Model model){
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("roles",roles);
        return "register";
    }

    @RequestMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("errorMessage","用户名密码不正确！");
        return "login";
    }
}
