package com.cui.blog.demo.ElasticSearch.EsRepository;

import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客检索接口
 */

@Repository
public interface EsBlogRepository extends ElasticsearchRepository<EsBolg,String> {

    List<EsBolg> queryEsBolgsByTitleIsNearOrContextIsNear(String searchContext);
}
