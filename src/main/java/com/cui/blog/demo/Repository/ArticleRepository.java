package com.cui.blog.demo.Repository;

import com.cui.blog.demo.base.BaseRepository;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends BaseRepository<Article,String>{

//    @Query(value = "select a.* from sys_article a where a.author = ?1",nativeQuery = true)
//    Page findallByuser(@Param("userId")String userId, Pageable pageable) throws  Exception;

    /**
     * 根据用户查询博客
     * @param user
     * @param pageable
     * @return
     */
    Page<Article> queryArticleByUser(User user,Pageable pageable);

    /**
     * 更新点击量
     * @param oid
     * @throws Exception
     */
    @Query(value = "update sys_article set click_number = click_number+1  where id=:id",nativeQuery = true)
    @Modifying
    void updateClickNumber(@Param(value = "id")  String oid) throws Exception;


    /**
     * 查询热门文章top5
     * @return
     * @throws Exception
     */
    @Query(value = "select * from sys_article ORDER BY click_number desc LIMIT 5",nativeQuery = true)
    @Modifying
    List<Article> findTopFiveArticle() throws Exception;

    /**
     * 查询最新发布的5篇文章
     * @return
     * @throws Exception
     */
    @Query(value = "select * from sys_article ORDER BY create_date desc LIMIT 5",nativeQuery = true)
    @Modifying
    List<Article> findNewFiveArticle() throws Exception;

}
