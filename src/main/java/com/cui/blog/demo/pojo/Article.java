package com.cui.blog.demo.pojo;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity(name = "sys_article")
public class Article {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String article_id;

    @Column(name = "title")
    @NotEmpty
    private String title;

    @Column(name = "context")
    @NotEmpty
    private String context;

    @Column(name = "createDate")
    @NotEmpty
    private Date createDate;

    @Column(name = "author",insertable = false,updatable = false)
    private String author;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "author")
    private User user;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
}