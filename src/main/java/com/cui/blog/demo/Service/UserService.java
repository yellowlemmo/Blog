package com.cui.blog.demo.Service;


import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //保存用户
    public void createUser(User user){
        userRepository.saveAndFlush(user);
    }

    //根据用户名查找用户
    public User findUserByUserName(String userName){
        User user = userRepository.findByusername(userName);
        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username);
        if(user == null) {
            throw new UsernameNotFoundException("找不到用户信息");
        }else{
            return user;
        }
    }

}
