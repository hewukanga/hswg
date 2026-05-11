package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OneRepairDTO;
import com.jiumai.base.biz.entity.OneRepair;
import com.jiumai.base.biz.query.OneRepairQuery;
import com.jiumai.base.biz.vo.OneRepairVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 一键报修表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OneRepairService extends IService<OneRepair> {

    /**
     * 分页查询一键报修表
     * @param query
     * @return
     */
    Page<OneRepairVO> findOneRepairPage(OneRepairQuery query);

    /**
     * 添加或更新一键报修表
     * @param oneRepairDTO
     * @return
     */
    Long saveOrUpdateOneRepair(OneRepairDTO oneRepairDTO);

    /**
     * 通过id查询成功一键报修表详情
     * @param id
     * @return
     */
    OneRepairVO getOneRepairById(Long id);
}
