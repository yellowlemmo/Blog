package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.ArticleClassifyRepository;
import com.cui.blog.demo.pojo.ArticleClassify;
import com.cui.blog.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 博客分类service
 */
@Service
@Transactional
public class ArticleClassifyService {

    @Autowired
    private ArticleClassifyRepository articleClassifyRepository;

    /**
     * 新增博客分类
     * @param articleClassify
     * @throws Exception
     */
    public void saveArticleClassify(ArticleClassify articleClassify) throws Exception{
        articleClassifyRepository.saveAndFlush(articleClassify);
    }

    /**
     * 根据id获取博客分类
     * @return
     * @throws Exception
     */
    public ArticleClassify findArticleClassify(String classifyId) throws Exception{
        return articleClassifyRepository.findById(classifyId).get();
    }

    /**
     * 分页查询文章分类列表
     * @param classifyName
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page articleClassifyList(String classifyName, Pageable pageable) throws Exception{
        Page<ArticleClassify> page = null;
        if(StringUtils.isNotEmpty(classifyName)){
            page = articleClassifyRepository.findByClassifyNameLike(classifyName,pageable);
        }else {
            page = articleClassifyRepository.findAll(pageable);
        }

        return page;
    }

    /**
     *查询博客分类list
     * @return
     */
    public List<ArticleClassify> articleClassifyList() throws Exception{
        return articleClassifyRepository.findAll();
    }
}
