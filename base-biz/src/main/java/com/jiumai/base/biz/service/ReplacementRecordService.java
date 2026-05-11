package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ReplacementRecordDTO;
import com.jiumai.base.biz.entity.ReplacementRecord;
import com.jiumai.base.biz.query.ReplacementRecordQuery;
import com.jiumai.base.biz.vo.ReplacementRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * pm_replacement_record 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ReplacementRecordService extends IService<ReplacementRecord> {

    /**
     * 分页查询pm_replacement_record
     * @param query
     * @return
     */
    Page<ReplacementRecordVO> findReplacementRecordPage(ReplacementRecordQuery query);

    /**
     * 添加或更新pm_replacement_record
     * @param replacementRecordDTO
     * @return
     */
    Long saveOrUpdateReplacementRecord(ReplacementRecordDTO replacementRecordDTO);

    /**
     * 通过id查询成功pm_replacement_record详情
     * @param id
     * @return
     */
    ReplacementRecordVO getReplacementRecordById(Long id);
}
