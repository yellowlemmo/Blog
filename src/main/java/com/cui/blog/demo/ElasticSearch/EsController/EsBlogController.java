package com.cui.blog.demo.ElasticSearch.EsController;


import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;
import com.cui.blog.demo.ElasticSearch.EsService.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/elasticsearch")
public class EsBlogController {

    @Autowired
    private EsBlogService esBlogService;

    @RequestMapping(value = "/blog")
    public String search(String searchContext) throws Exception{
        List<EsBolg> esBolgs = esBlogService.search(searchContext);
        return esBolgs.get(0).toString();
    }
}
