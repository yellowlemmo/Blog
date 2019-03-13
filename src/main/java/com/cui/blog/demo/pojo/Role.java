package com.cui.blog.demo.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * 用户角色实体类
 */
@Entity(name = "sys_role")
public class Role implements Serializable {

    /**
     * 用户角色id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 用户角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户角色中文名
     */
    @Column(name = "ch_name")
    private String chName;

    /**
     * 角色权限多对多，角色为被维护端
     */
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            //中间表名
            name = "sys_permission_role",
            // 指定当前表在中间表的外键名称和外键所指向的当前表主键
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            // 指定另一方在中间表的外键名称和外键所指向的主键
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    private List<Permission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
