package com.cui.blog.demo.controller;

import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;
import com.cui.blog.demo.ElasticSearch.EsService.EsBlogService;
import com.cui.blog.demo.Repository.UserRepository;
import com.cui.blog.demo.Service.ArticleClassifyService;
import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.ArticleClassify;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private EsBlogService esBlogService;

    /**
     * 博客分类service
     * @return
     */
    @Autowired
    private ArticleClassifyService articleClassifyService;

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
        articleService.saveBlog(article);
        EsBolg  esBolg = new EsBolg(article.getArticle_id(),article.getTitle(),article.getContext());
        //索引博客
        esBlogService.save(esBolg);
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


}
