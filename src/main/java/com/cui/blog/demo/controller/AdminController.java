package com.cui.blog.demo.controller;


import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('Role_admin')")
    @RequestMapping(value = "/users")
    public String findUser(Model model,
                           String username,
                           @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize) throws Exception{
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize);
        Page<User> page = userService.findUserByUsername(username,pageableFactory.getPageable());
        model.addAttribute("page",page);
        return "/admin/userlist";
    }
}
