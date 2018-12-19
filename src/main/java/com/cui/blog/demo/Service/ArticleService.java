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

    /**
     * 根据用户id查询博客
     * @param userId
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page findallByuser(String userId, Pageable pageable) throws  Exception{
        Page page = articleRepository.findallByuser(userId,pageable);
        return page;
    }

    /**
     * 保存博客
     * @param article
     */
    public void saveBlog(Article article){
        articleRepository.saveAndFlush(article);
    }

    /**
     * 分页查询所有博客
     * @param pageable
     * @return
     */
    public Page findAllBlog(Pageable pageable){
        return articleRepository.findAll(pageable);
    }
}
