package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OilCardRecordDTO;
import com.jiumai.base.biz.entity.OilCardRecord;
import com.jiumai.base.biz.query.OilCardRecordQuery;
import com.jiumai.base.biz.vo.OilCardRecordVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 油卡上报记录表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OilCardRecordService extends IService<OilCardRecord> {

    /**
     * 分页查询油卡上报记录表
     * @param query
     * @return
     */
    Page<OilCardRecordVO> findOilCardRecordPage(OilCardRecordQuery query);

    /**
     * 添加或更新油卡上报记录表
     * @param oilCardRecordDTO
     * @return
     */
    Long saveOrUpdateOilCardRecord(OilCardRecordDTO oilCardRecordDTO);

    /**
     * 通过id查询成功油卡上报记录表详情
     * @param id
     * @return
     */
    OilCardRecordVO getOilCardRecordById(Long id);
}
