package com.cui.blog.demo.controller;

import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import com.cui.blog.demo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserSpaceController extends BaseController {

    /**
     * 博客service接口
     */
    @Autowired
    private ArticleService articleService;


    /**
     * 用户service接口
     */
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserSpaceController.class);

    @RequestMapping(value = "/userspace")
    public String userSpace(Model model,
                            @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                            @RequestParam(value = "pageSize",defaultValue = "5") int pageSize ) throws Exception {
        logger.info("进入个人中心");
        User user = getCurrrentUser();
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize);
        Page<Article> page = articleService.findallByuser(user,pageableFactory.getPageable());
        model.addAttribute("user",user);
        model.addAttribute("page",page);
        return "userHome";
    }

    /**
     * 进入用户自定义页面
     * @param model
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setting")
    public String settingMyself(Model model,@RequestParam(value = "userId") String userId) throws Exception{
        logger.info("进入用户自定义");
        User user = userService.findUserById(userId);
        model.addAttribute("user",user);
        return "userSetting";
    }

    @RequestMapping(value = "/saveSetting")
    @ResponseBody
    public String saveUserSetting(User user) throws Exception{
        userService.updateUserById(user.getUsername(),user.getPassword(),user.getEmail(),user.getId());
        return user.getId()+"---"+user.getUsername();
    }
}
