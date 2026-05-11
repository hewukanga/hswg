package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.IntegralRecordDTO;
import com.jiumai.base.biz.entity.IntegralRecord;
import com.jiumai.base.biz.query.IntegralRecordQuery;
import com.jiumai.base.biz.vo.IntegralRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 积分记录 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface IntegralRecordService extends IService<IntegralRecord> {

    /**
     * 分页查询积分记录
     * @param query
     * @return
     */
    Page<IntegralRecordVO> findIntegralRecordPage(IntegralRecordQuery query);

    /**
     * 添加或更新积分记录
     * @param integralRecordDTO
     * @return
     */
    Long saveOrUpdateIntegralRecord(IntegralRecordDTO integralRecordDTO);

    /**
     * 通过id查询成功积分记录详情
     * @param id
     * @return
     */
    IntegralRecordVO getIntegralRecordById(Long id);
}
