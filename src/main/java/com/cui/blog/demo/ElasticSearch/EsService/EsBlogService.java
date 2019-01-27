package com.cui.blog.demo.ElasticSearch.EsService;

import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;

import java.util.List;

/**
 * blog检索service接口
 */

public interface EsBlogService {

    /**
     * 索引新的blog
     * @param esBolg
     * @throws Exception
     */
    void save(EsBolg esBolg) throws Exception;

    /**
     * 删除索引
     * @param esBolg
     * @throws Exception
     */
    void delete(EsBolg esBolg) throws Exception;

    /**
     * 搜素索引
     * @param searchContext
     * @return
     * @throws Exception
     */
    List<EsBolg> search(String searchContext) throws Exception;


}
