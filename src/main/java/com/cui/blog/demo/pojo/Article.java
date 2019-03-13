package com.cui.blog.demo.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *博客实体类
 */
@Entity(name = "sys_article")
public class Article implements Serializable {

    /**
     * 博客主键
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String article_id;

    /**
     * 博客标题
     */
    @Column(name = "title")
    @NotEmpty
    private String title;

    /**
     *博客内容
     */
    @Column(name = "context")
    @NotEmpty
    private String context;

    /**
     * 博客创建时间
     */
    @Column(name = "createDate")
    @NotNull
    private Timestamp createDate;


    /**
     * 点击量

     */
    @Column(name = "click_number")
    private int clickNum;

    /**
     * 博客作者主键
     */
    @Column(name = "author",insertable = false,updatable = false)
    private String author;

    /**
     * 博客作者
     */
    @ManyToOne
    @JoinColumn(name = "author")
    private User user;

    /**
     * 博客分类主键
     */
    @Column(name = "article_classify",insertable = false,updatable = false)
    private String articleClassifyId;

    /**
     * 博客分类
     */
    @ManyToOne
    @JoinColumn(name = "article_classify")
    private ArticleClassify articleClassify;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticleClassifyId() {
        return articleClassifyId;
    }

    public void setArticleClassityId(String articleClassityId) {
        this.articleClassifyId = articleClassityId;
    }

    public ArticleClassify getArticleClassify() {
        return articleClassify;
    }

    public void setArticleClassify(ArticleClassify articleClassify) {
        this.articleClassify = articleClassify;
    }
}
