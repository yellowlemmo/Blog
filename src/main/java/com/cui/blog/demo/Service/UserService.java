package com.cui.blog.demo.Service;


import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.GlobalParamter;
import com.cui.blog.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class UserService implements UserDetailsService {

    /**
     * 用户repository接口
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * redisTemplate接口
     */
    @Autowired
    private RedisTemplateService redisTemplateService;

    /**
     *创建新用户
     * @param user
     */
    public void createUser(User user) throws Exception{
        userRepository.saveAndFlush(user);
        //保存用户缓存时key值是由当前用户id拼接
        redisTemplateService.saveRedisCache(user.getId(),user,
                GlobalParamter.REDIS_TIMROUT,GlobalParamter.REDIS_TIMETYPE);

    }

    /**
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @Cacheable(value = "Users",key = "#userName")
    public User findUserByUserName(String userName) throws Exception {
        User user = userRepository.findByusername(userName);
        return user;
    }

    /**
     * 根据用户名分页查询用户列表
     * @param username
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page<User> findUserByUsername(String username, Pageable pageable) throws Exception{
        Page<User> page = null;
        if(!StringUtils.isNotEmpty(username)) {
            page = userRepository.findAll(pageable);
        }else {
            page = userRepository.findByUsernameLike("%"+username+"%",pageable);
        }
        return page;
    }

    /**
     * 通过用户名和邮箱查找用户
     * @param username
     * @param eamil
     * @return
     */
    public User findUserByUsernameAndEmail(String username,String eamil) throws Exception{
        return userRepository.findByUsernameAndEmail(username,eamil);
    }

    @Cacheable(value = "Users",key = "#id")
    public User findUserById(String id) throws Exception{
        return userRepository.findById(id).get();
    }

    /**
     * 更新密码
     * @param password
     * @param id
     * @throws Exception
     */

    public void updatePasswordById(String password,String id) throws Exception{
        userRepository.updatePasswordById(password,id);
    }

    /**
     * 更新用户信息
     * @param username
     * @param password
     * @param email
     * @param id
     * @throws Exception
     */
    public void updateUserById(String username,String password,String email,String id) throws Exception{
        userRepository.updateUserById(username,password,email,id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findByusername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user == null) {
            throw new UsernameNotFoundException("找不到用户信息");
        }else{
            return user;
        }
    }

}
