package com.cui.blog.demo.controller;

import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Service.RoleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.pojo.Role;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;


    @RequestMapping("/index")
    public String index(Model model){
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
