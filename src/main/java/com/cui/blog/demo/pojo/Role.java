package com.cui.blog.demo.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 用户角色实体类
 */
@Entity(name = "sys_role")
public class Role {

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
}
