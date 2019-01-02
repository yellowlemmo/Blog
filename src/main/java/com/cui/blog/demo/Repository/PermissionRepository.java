package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.Permission;
import org.springframework.stereotype.Repository;

/**
 * 权限Repository接口
 */
@Repository
public interface PermissionRepository extends BaseRepository<Permission,String> {

}
