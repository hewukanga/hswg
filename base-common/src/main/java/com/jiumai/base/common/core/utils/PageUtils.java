package com.jiumai.base.common.core.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiumai.base.common.core.query.PageParam;

/**
 * 分页工具类
 * @param <T>
 */
public class PageUtils<T> {

    public Page<T> getPageByPageParam(PageParam pageParam) {
        Page<T> page = new Page<T>();
        page.setCurrent(pageParam.getPage());
        page.setSize(pageParam.getLimit());
        return page;
    }

    public <Y> Page<T> getNewPage(Page<Y> oldPage) {
        Page<T> newPage = new Page<T>();
        newPage.setPages(oldPage.getPages());
        newPage.setSize(oldPage.getSize());
        newPage.setTotal(oldPage.getTotal());
        newPage.setCurrent(oldPage.getCurrent());
        return newPage;
    }
}
