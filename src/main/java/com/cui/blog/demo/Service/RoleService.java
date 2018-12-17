package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.RoleRepository;
import com.cui.blog.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRole(){
       List<Role> roles = roleRepository.findAll();
        return roles;
    }
}
