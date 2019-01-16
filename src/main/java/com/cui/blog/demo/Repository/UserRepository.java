package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * 通过用户名和邮箱查找用户
     * @param username
     * @param email
     * @return
     * @throws Exception
     */
    User findByUsernameAndEmail(String username,String email) throws Exception;

    /**
     * 修改用户密码
     * @param password
     * @param id
     * @throws Exception
     */
    @Query(value = "update sys_user set password=:password where id=:id",nativeQuery = true)
    @Modifying
    void updatePasswordById(@Param(value = "password") String password, @Param(value = "id") String id) throws Exception;

    /**
     * 根据id更新用户信息
     * @param username
     * @param password
     * @param email
     * @param id
     * @throws Exception
     */
    @Query(value = "update sys_user set username=:username,password=:password,email=:email where id=:id",nativeQuery = true)
    @Modifying
    void updateUserById(@Param(value = "username") String username,@Param(value = "password") String password,
                        @Param(value = "email") String email,@Param(value = "id") String id) throws Exception;

}
