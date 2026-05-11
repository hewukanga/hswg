package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CleaningSituationDTO;
import com.jiumai.base.biz.entity.CleaningSituation;
import com.jiumai.base.biz.query.CleaningSituationQuery;
import com.jiumai.base.biz.vo.CleaningSituationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保洁情况 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningSituationService extends IService<CleaningSituation> {

    /**
     * 分页查询保洁情况
     * @param query
     * @return
     */
    Page<CleaningSituationVO> findCleaningSituationPage(CleaningSituationQuery query);

    /**
     * 添加或更新保洁情况
     * @param cleaningSituationDTO
     * @return
     */
    Long saveOrUpdateCleaningSituation(CleaningSituationDTO cleaningSituationDTO);

    /**
     * 通过id查询成功保洁情况详情
     * @param id
     * @return
     */
    CleaningSituationVO getCleaningSituationById(Long id);
}
