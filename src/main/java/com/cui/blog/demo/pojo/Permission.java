package com.cui.blog.demo.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类
 */
@Entity(name = "sys_permission")
public class Permission implements Serializable {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
    private String id;

    /**
     * 父权限
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 权限地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 角色权限多对多，权限为维护端
     */
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "permissions")
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
