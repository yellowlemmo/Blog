package com.cui.blog.demo.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 权限实体类
 */
@Entity(name = "sys_permission")
public class Permission {

    /**
     * 主键
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
    private String id;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
