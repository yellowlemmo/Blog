package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.RoleRepository;
import com.cui.blog.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRole(){
       List<Role> roles = roleRepository.findAll();
        return roles;
    }

    /**
     * 根据角色id查找角色对象
     * @param id
     * @return
     */
    public Role findRoleById(String id){
        Role role =roleRepository.findById(id).get();
        return role;
    }

    /**
     * 根据角色名称查找角色对象
     * @param name
     * @return
     * @throws Exception
     */
    public Role findRoleByName(String name) throws Exception{
        Role role = roleRepository.findByName(name);
        return role;
    }

}
