package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.SkilledCraftsmenDTO;
import com.jiumai.base.biz.entity.SkilledCraftsmen;
import com.jiumai.base.biz.query.SkilledCraftsmenQuery;
import com.jiumai.base.biz.vo.SkilledCraftsmenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 能工巧匠信息 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface SkilledCraftsmenService extends IService<SkilledCraftsmen> {

    /**
     * 分页查询能工巧匠信息
     * @param query
     * @return
     */
    Page<SkilledCraftsmenVO> findSkilledCraftsmenPage(SkilledCraftsmenQuery query);

    /**
     * 添加或更新能工巧匠信息
     * @param skilledCraftsmenDTO
     * @return
     */
    Long saveOrUpdateSkilledCraftsmen(SkilledCraftsmenDTO skilledCraftsmenDTO);

    /**
     * 通过id查询成功能工巧匠信息详情
     * @param id
     * @return
     */
    SkilledCraftsmenVO getSkilledCraftsmenById(Long id);
}
