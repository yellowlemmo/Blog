package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.User;

public interface UserRepository extends BaseRepository<User, String> {
    User findByusername(String userName);
}
