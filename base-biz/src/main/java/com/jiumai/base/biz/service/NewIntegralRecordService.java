package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.NewIntegralRecordDTO;
import com.jiumai.base.biz.entity.NewIntegralRecord;
import com.jiumai.base.biz.query.NewIntegralRecordQuery;
import com.jiumai.base.biz.vo.NewIntegralRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 新积分记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface NewIntegralRecordService extends IService<NewIntegralRecord> {

    /**
     * 分页查询新积分记录表
     * @param query
     * @return
     */
    Page<NewIntegralRecordVO> findNewIntegralRecordPage(NewIntegralRecordQuery query);

    /**
     * 添加或更新新积分记录表
     * @param newIntegralRecordDTO
     * @return
     */
    Long saveOrUpdateNewIntegralRecord(NewIntegralRecordDTO newIntegralRecordDTO);

    /**
     * 通过id查询成功新积分记录表详情
     * @param id
     * @return
     */
    NewIntegralRecordVO getNewIntegralRecordById(Long id);
}
