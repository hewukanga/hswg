package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.LawEnforcementInstrumentManageDTO;
import com.jiumai.base.biz.entity.LawEnforcementInstrumentManage;
import com.jiumai.base.biz.query.LawEnforcementInstrumentManageQuery;
import com.jiumai.base.biz.vo.LawEnforcementInstrumentManageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 执法仪管理 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface LawEnforcementInstrumentManageService extends IService<LawEnforcementInstrumentManage> {

    /**
     * 分页查询执法仪管理
     * @param query
     * @return
     */
    Page<LawEnforcementInstrumentManageVO> findLawEnforcementInstrumentManagePage(LawEnforcementInstrumentManageQuery query);

    /**
     * 添加或更新执法仪管理
     * @param lawEnforcementInstrumentManageDTO
     * @return
     */
    Long saveOrUpdateLawEnforcementInstrumentManage(LawEnforcementInstrumentManageDTO lawEnforcementInstrumentManageDTO);

    /**
     * 通过id查询成功执法仪管理详情
     * @param id
     * @return
     */
    LawEnforcementInstrumentManageVO getLawEnforcementInstrumentManageById(Long id);
}
