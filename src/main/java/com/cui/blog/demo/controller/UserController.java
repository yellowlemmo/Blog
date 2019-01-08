package com.cui.blog.demo.controller;

import com.cui.blog.demo.Service.RoleService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.pojo.Role;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 用户service
     */
    @Autowired
    private UserService userService;

    /**
     * 用户角色service
     * @param user
     * @return
     */
    @Autowired
    private RoleService roleService;


    @PostMapping(value = "/saveuser")
    public String saveUser(User user) throws Exception {
        logger.info("注册用户");
        Role role = roleService.findRoleByName("Role_user");
        user.setRole(role);
        String Bcrpassword = Utils.BCryptUtil(user.getPassword());
        user.setPassword(Bcrpassword);
        userService.createUser(user);
        logger.info("注册用户成功");
        return "login";
    }
}
