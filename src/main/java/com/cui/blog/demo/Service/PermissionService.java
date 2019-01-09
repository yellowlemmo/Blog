package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.PermissionRepository;
import com.cui.blog.demo.pojo.Permission;
import com.cui.blog.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限service
 */
@Service
@Transactional
public class PermissionService  {

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 查询所有权限
     * @return
     */
    public List<Permission> findAll(){
        List<Permission> list = permissionRepository.findAll();
        return list;
    }

    /**
     * 保存权限
     * @param permission
     */
    public void savePermission(Permission permission){
        permissionRepository.saveAndFlush(permission);
    }

    public List<Permission> findPermission(List<Role> roles){
        return permissionRepository.findByRoles(roles);
    }
}
