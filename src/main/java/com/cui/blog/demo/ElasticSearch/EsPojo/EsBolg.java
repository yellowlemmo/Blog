package com.cui.blog.demo.ElasticSearch.EsPojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


/**
 * Es搜素bolg文档类
 */

@Document(indexName = "blog",type = "blog")
public class EsBolg {


    /**
     * id
     */
    @Id
    private String id;

    /**
     * 标题
     */
    @Field
    private String title;

    /**
     * 内容
     */
    @Field
    private String context;

    public EsBolg(){
    }


    public EsBolg(String id,String title,String context){
        this.id = id;
        this.title = title;
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "EsBolg{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
