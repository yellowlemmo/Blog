package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.Role;

public interface RoleRepository extends BaseRepository<Role,String> {
    /**
     * 根据角色名称查找角色
     * @param name
     * @return
     * @throws Exception
     */
    Role findByName(String name) throws Exception;
}
