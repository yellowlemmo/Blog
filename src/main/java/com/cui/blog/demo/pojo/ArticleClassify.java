package com.cui.blog.demo.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 文章类型实体类
 */

@Entity(name = "sys_article_classify")
public class ArticleClassify implements Serializable {

    static final long serialVersionUID = 42L;

    /**
     * 文章分类主键
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "classify_id")
    private String classifyId;

    /**
     * 文章分类名称
     */
    @Column(name = "classify_name")
    private String classifyName;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
