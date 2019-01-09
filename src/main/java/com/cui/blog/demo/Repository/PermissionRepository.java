package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.Permission;
import com.cui.blog.demo.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限Repository接口
 */
@Repository
public interface PermissionRepository extends BaseRepository<Permission,String> {

    /**
     * 根据角色查询权限
     * @param roles
     * @return
     */
    List<Permission> findByRoles(List<Role> roles);
}
