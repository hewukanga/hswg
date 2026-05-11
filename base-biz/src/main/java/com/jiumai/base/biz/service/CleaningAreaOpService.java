package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.CleaningAreaOpDTO;
import com.jiumai.base.biz.entity.CleaningAreaOp;
import com.jiumai.base.biz.query.CleaningAreaOpQuery;
import com.jiumai.base.biz.vo.CleaningAreaOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保洁区域人员 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface CleaningAreaOpService extends IService<CleaningAreaOp> {

    /**
     * 分页查询保洁区域人员
     * @param query
     * @return
     */
    Page<CleaningAreaOpVO> findCleaningAreaOpPage(CleaningAreaOpQuery query);

    /**
     * 添加或更新保洁区域人员
     * @param cleaningAreaOpDTO
     * @return
     */
    Long saveOrUpdateCleaningAreaOp(CleaningAreaOpDTO cleaningAreaOpDTO);

    /**
     * 通过id查询成功保洁区域人员详情
     * @param id
     * @return
     */
    CleaningAreaOpVO getCleaningAreaOpById(Long id);
}
