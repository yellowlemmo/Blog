package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends BaseRepository<Article,String> {

    @Query(value = "select a.* from sys_article a where a.author = ?1",nativeQuery = true)
    Page findallByuser(@Param("userId")String userId, Pageable pageable) throws  Exception;

}
