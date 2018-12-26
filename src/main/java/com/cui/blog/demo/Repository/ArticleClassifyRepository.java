package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.ArticleClassify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleClassifyRepository extends BaseRepository<ArticleClassify,String> {

    /**
     * 根据分类名称查找
     * @param classifyName
     * @param pageable
     * @return
     */
    Page<ArticleClassify> findByClassifyNameLike(String classifyName, Pageable pageable) throws Exception;
}
