package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.ProceedingsDTO;
import com.jiumai.base.biz.entity.Proceedings;
import com.jiumai.base.biz.query.ProceedingsQuery;
import com.jiumai.base.biz.vo.ProceedingsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会议议事 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ProceedingsService extends IService<Proceedings> {

    /**
     * 分页查询会议议事
     * @param query
     * @return
     */
    Page<ProceedingsVO> findProceedingsPage(ProceedingsQuery query);

    /**
     * 添加或更新会议议事
     * @param proceedingsDTO
     * @return
     */
    Long saveOrUpdateProceedings(ProceedingsDTO proceedingsDTO);

    /**
     * 通过id查询成功会议议事详情
     * @param id
     * @return
     */
    ProceedingsVO getProceedingsById(Long id);
}
