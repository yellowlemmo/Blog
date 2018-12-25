package com.cui.blog.demo.Service;


import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     *创建新用户
     * @param user
     */
    public void createUser(User user) throws Exception{
        userRepository.saveAndFlush(user);
    }

    /**
     *
     * @param userName
     * @return
     * @throws Exception
     */
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
