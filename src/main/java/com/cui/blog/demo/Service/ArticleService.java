package com.cui.blog.demo.Service;

import com.cui.blog.demo.Repository.ArticleRepository;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.GlobalParamter;
import com.cui.blog.demo.utils.JsonUtil;
import com.cui.blog.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        //保存新博客后最新列表发生变化删除redis中的最新列表
        redisTemplateService.delete("resentBlog::1");
    }

    /**
     * 分页查询所有博客
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Page findAllBlog(Integer pageIndex,Integer pageSize){
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize);
        return articleRepository.findAll(pageableFactory.getPageable());
    }

    /**
     * 排序分页查询所有博客
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @return
     */
    public Page<Article> findAllBlogSortByKey(Integer pageIndex,Integer pageSize,String sortField){
        Sort sort = new Sort(Sort.Direction.DESC,sortField);
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize,sort);
        return articleRepository.findAll(pageableFactory.getPageable());
    }

    /**
     * 更新博客点击量
     * @param oid
     * @throws Exception
     */
    public void updateClickNumber(String oid) throws Exception{
        articleRepository.updateClickNumber(oid);
        //点击后热门博客列表缓存与数据库不同步
        redisTemplateService.delete("hostBlog::1");
    }

    /**
     * 查找热门博客top5
     * @return
     * @throws Exception
     */
    @Cacheable(value = "hostBlog",key = "1")
    public List<Article> findTopFiveBolg() throws Exception{
        return articleRepository.findTopFiveArticle();
    }

    /**
     * 查询最新的前5篇博客
     * @return
     * @throws Exception
     */
    @Cacheable(value = "resentBlog",key = "1")
    public List<Article> findNewFiveBlog() throws Exception{
        return articleRepository.findNewFiveArticle();
    }
}
