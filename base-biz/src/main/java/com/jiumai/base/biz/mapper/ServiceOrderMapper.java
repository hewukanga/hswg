package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ServiceOrder;
import com.jiumai.base.biz.query.ServiceOrderQuery;
import com.jiumai.base.biz.vo.ServiceOrderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务工单表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ServiceOrderMapper extends BaseMapper<ServiceOrder> {

    /**
     * 分页查询服务工单表
     * @param page
     * @param query
     * @return
     */
    List<ServiceOrderVO> findServiceOrderPage(Page<ServiceOrderVO> page, @Param("query") ServiceOrderQuery query);
}
