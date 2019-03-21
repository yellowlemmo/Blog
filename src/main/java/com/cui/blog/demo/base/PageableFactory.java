package com.cui.blog.demo.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageableFactory {

    private Pageable pageable;

    private Sort sort;

    //普通分页
    public PageableFactory(int pageIndex,int pageSize){
        this.pageable =  PageRequest.of(pageIndex,pageSize);
    }

    //排序分页
    public PageableFactory(int pageIndex,int pageSize,Sort sort){
        this.pageable = PageRequest.of(pageIndex,pageSize,sort);
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
