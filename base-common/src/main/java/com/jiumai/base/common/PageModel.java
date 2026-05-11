package com.jiumai.base.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 自定义分页模型
 * @author wangjian
 *
 * @param <T>
 */
public class PageModel<T> extends Page<T> {
	private static final long serialVersionUID = 1L;
	
	protected List<T> list;
	protected Integer page = 1;
	protected Integer limit = 10;
	protected Integer offset;
	public List<T> getList() {
		return list;
	}

	public PageModel<T> setList(List<T> list) {
		this.list = list;
		return this;
	}

	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		if (limit == 0 || offset == 0) {
            this.setPage(1);
        } else {
            this.setPage(offset / limit + 1);
        }
        this.offset = offset;
	}

	public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        setCurrent(page);
    }

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
		setSize(limit);
	}



	
	

}
