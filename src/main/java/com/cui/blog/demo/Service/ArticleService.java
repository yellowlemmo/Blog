package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.ArticleRepository;
import com.cui.blog.demo.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page findallByuser(String userId, Pageable pageable) throws  Exception{
        Page page = articleRepository.findallByuser(userId,pageable);
        return page;
    }

    public void saveBlog(Article article){
        articleRepository.saveAndFlush(article);
    }
}
