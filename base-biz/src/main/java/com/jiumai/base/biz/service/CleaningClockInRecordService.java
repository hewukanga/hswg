package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CleaningClockInRecordDTO;
import com.jiumai.base.biz.entity.CleaningClockInRecord;
import com.jiumai.base.biz.query.CleaningClockInRecordQuery;
import com.jiumai.base.biz.vo.CleaningClockInRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保洁打卡记录 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningClockInRecordService extends IService<CleaningClockInRecord> {

    /**
     * 分页查询保洁打卡记录
     * @param query
     * @return
     */
    Page<CleaningClockInRecordVO> findCleaningClockInRecordPage(CleaningClockInRecordQuery query);

    /**
     * 添加或更新保洁打卡记录
     * @param cleaningClockInRecordDTO
     * @return
     */
    Long saveOrUpdateCleaningClockInRecord(CleaningClockInRecordDTO cleaningClockInRecordDTO);

    /**
     * 通过id查询成功保洁打卡记录详情
     * @param id
     * @return
     */
    CleaningClockInRecordVO getCleaningClockInRecordById(Long id);
}
