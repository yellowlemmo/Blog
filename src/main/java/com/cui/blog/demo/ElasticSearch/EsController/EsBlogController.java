package com.cui.blog.demo.ElasticSearch.EsController;


import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;
import com.cui.blog.demo.ElasticSearch.EsService.EsBlogService;
import com.cui.blog.demo.utils.GlobalParamter;
import com.cui.blog.demo.utils.HttpClientUtil;
import com.cui.blog.demo.utils.JsonUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/elasticsearch")
public class EsBlogController {


    /**
     * 全文检索
     * @param searchContext
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/blog")
    @ResponseBody
    public String search(String searchContext) throws Exception{
        /**
         * get方式
         */
//        List<NameValuePair> pairList = new ArrayList<>();
//        pairList.add(new BasicNameValuePair("q",searchContext));
//        pairList.add(new BasicNameValuePair("q",searchContext));
//        String response = HttpClientUtil.doGet(GlobalParamter.ELASTICSEARCH_URL,pairList);

        /**
         * post方式
         */
        String jsonparam = JsonUtil.elasticsearchPostBody(searchContext);
        String response = HttpClientUtil.doPost(GlobalParamter.ELASTICSEARCH_URL,jsonparam);
        return response;
    }
}
