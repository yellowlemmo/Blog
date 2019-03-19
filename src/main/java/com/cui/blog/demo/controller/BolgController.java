package com.cui.blog.demo.controller;
//
//import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;
//import com.cui.blog.demo.ElasticSearch.EsService.EsBlogService;
import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.Service.ArticleClassifyService;
import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.ArticleClassify;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.GlobalParamter;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import com.cui.blog.demo.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BolgController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BolgController.class);
    /**
     * 博客Service接口
     */
    @Autowired
    private ArticleService articleService;

    /**
     *用户service
     */
    @Autowired
    private UserService userService;

//    @Autowired
//    private EsBlogService esBlogService;

    /**
     * 博客分类service
     * @return
     */
    @Autowired
    private ArticleClassifyService articleClassifyService;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping(value = "/add")
    public String addNewBlog(Model model) throws Exception{
        logger.info("初始化新增博客页面");
        List<ArticleClassify> articleClassifyList = articleClassifyService.articleClassifyList();
        model.addAttribute("articleClassifyList",articleClassifyList);
        return "addBlog";
    }

    /**
     * 保存博客
     * @param article
     * @return
     */
    @PostMapping(value = "/save")
    public String saveBlog(Article article,String classifyId) throws Exception{
        logger.info("保存博客");
        if (article == null){
            logger.error("保存博客失败");
            return "error";
        }
        //文章分类
        ArticleClassify articleClassify = articleClassifyService.findArticleClassify(classifyId);
        article.setUser((User)SpringSecurityUtil.currentUser(session));
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        article.setCreateDate(createTime);
        article.setArticleClassify(articleClassify);
        article.setClickNum(GlobalParamter.ZERO);
        articleService.saveBlog(article);
//        EsBolg  esBolg = new EsBolg(article.getArticle_id(),article.getTitle(),article.getContext());
//        //索引博客
//        esBlogService.save(esBolg);
        logger.info("保存博客成功");
        return "redirect:/index";
    }

    /**
     * 查看博客内容
     * @param model
     * @param blogId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/blogContext")
    public String blogContextView(Model model,String blogId) throws Exception{
        logger.info("查看博客内容");
        Article blog = articleService.findById(blogId);
        model.addAttribute("blog",blog);
        return "/blog/blogView";
    }

    /**
     * 博客点击量更新
     * @param oid
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updateClickNumber",produces = "application/json")
    @ResponseBody
    public String updateClickNumber(String oid) throws Exception{
        logger.info("点击量更新");
        String message = null;
        try {
            articleService.updateClickNumber(oid);
            message = "success";
            logger.info("点击量更新成功");
        }catch (Exception e){
            e.printStackTrace();
            message = e.toString();
            logger.info("点击量更新失败");
        }
        return message;
    }

    //测试查找缓存

//    @RequestMapping(value = "/find")
//    @ResponseBody
//    public String getBlog(String oid) throws Exception{
//        logger.info("blog查询");
//        articleService.findById(oid);
//        return articleService.findById(oid).getContext();
//    }


}
