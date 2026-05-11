package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Proceedings;
import com.jiumai.base.biz.query.ProceedingsQuery;
import com.jiumai.base.biz.vo.ProceedingsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会议议事 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProceedingsMapper extends BaseMapper<Proceedings> {

    /**
     * 分页查询会议议事
     * @param page
     * @param query
     * @return
     */
    List<ProceedingsVO> findProceedingsPage(Page<ProceedingsVO> page, @Param("query") ProceedingsQuery query);
}
