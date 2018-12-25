package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends BaseRepository<User, String> {
    /**
     * 根据用户名查询用户实体
     * @param userName
     * @return
     * @throws Exception
     */
    User findByusername(String userName) throws Exception;


    /**
     *根据用户名模糊查询用户列表
     * @param username
     * @param pageable
     * @return
     * @throws Exception
     */
    Page<User> findByUsernameLike(String username, Pageable pageable) throws Exception;

}
