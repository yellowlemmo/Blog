package com.cui.blog.demo.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class PageableFactory {

    private Pageable pageable;

    public PageableFactory(int pageIndex,int pageSize){
        this.pageable =  PageRequest.of(pageIndex,pageSize);
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
