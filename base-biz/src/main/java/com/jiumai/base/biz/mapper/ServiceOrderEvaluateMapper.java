package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ServiceOrderEvaluate;
import com.jiumai.base.biz.query.ServiceOrderEvaluateQuery;
import com.jiumai.base.biz.vo.ServiceOrderEvaluateVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务工单评价表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ServiceOrderEvaluateMapper extends BaseMapper<ServiceOrderEvaluate> {

    /**
     * 分页查询服务工单评价表
     * @param page
     * @param query
     * @return
     */
    List<ServiceOrderEvaluateVO> findServiceOrderEvaluatePage(Page<ServiceOrderEvaluateVO> page, @Param("query") ServiceOrderEvaluateQuery query);
}
