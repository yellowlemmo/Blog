package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.ArticleRepository;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.GlobalParamter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ArticleService {

    /**
     * 博客repository
     */
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * redisTemplate缓存接口
     */
    @Autowired
    private RedisTemplateService redisTemplateService;

    /**
     * 根据用户id查询博客
     * @param user
     * @param pageable
     * @return
     * @throws Exception
     */
    public Page findallByuser(User user, Pageable pageable) throws  Exception{
        Page page = articleRepository.queryArticleByUser(user,pageable);
        return page;
    }

    @Cacheable(value="Blogs",key = "#blogId")
    public Article findById(String blogId) throws Exception{
        return articleRepository.findById(blogId).get();
    }

    /**
     * 保存博客
     * @param article
     */
    public void saveBlog(Article article) throws Exception{
        //保存博客详细信息缓存
        articleRepository.saveAndFlush(article);
        redisTemplateService.saveRedisCache(article.getArticle_id(),article,
                GlobalParamter.REDIS_TIMROUT,GlobalParamter.REDIS_TIMETYPE);
    }

    /**
     * 分页查询所有博客
     * @param pageable
     * @return
     */
    public Page findAllBlog(Pageable pageable){
        return articleRepository.findAll(pageable);
    }

    /**
     * 更新博客点击量
     * @param oid
     * @throws Exception
     */
    public void updateClickNumber(String oid) throws Exception{
         articleRepository.updateClickNumber(oid);
    }

    /**
     * 查找热门博客top5
     * @return
     * @throws Exception
     */
    public List<Article> findTopFiveBolg() throws Exception{
        return articleRepository.findTopFiveArticle();
    }

    /**
     * 查询最新的前5篇博客
     * @return
     * @throws Exception
     */
    public List<Article> findNewFiveBlog() throws Exception{
        return articleRepository.findNewFiveArticle();
    }
}
