package com.cui.blog.demo.ElasticSearch.EsService.impl;

import com.cui.blog.demo.ElasticSearch.EsPojo.EsBolg;
import com.cui.blog.demo.ElasticSearch.EsRepository.EsBlogRepository;
import com.cui.blog.demo.ElasticSearch.EsService.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * bolg检索service实现类
 */
@Service
@Transactional
public class EsBlogServiceImpl implements EsBlogService {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Override
    public void save(EsBolg esBolg) throws Exception {
        esBlogRepository.save(esBolg);
    }

    @Override
    public void delete(EsBolg esBolg) throws Exception {
        esBlogRepository.delete(esBolg);
    }

    @Override
    public List<EsBolg> search(String searchContext) throws Exception {
        return esBlogRepository.queryEsBolgsByTitleIsNearOrContextIsNear(searchContext);
    }
}
