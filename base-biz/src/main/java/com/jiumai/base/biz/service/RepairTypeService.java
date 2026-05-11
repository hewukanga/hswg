package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.RepairTypeDTO;
import com.jiumai.base.biz.entity.RepairType;
import com.jiumai.base.biz.query.RepairTypeQuery;
import com.jiumai.base.biz.vo.RepairTypeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 维修项目配置表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface RepairTypeService extends IService<RepairType> {

    /**
     * 分页查询维修项目配置表
     * @param query
     * @return
     */
    Page<RepairTypeVO> findRepairTypePage(RepairTypeQuery query);

    /**
     * 添加或更新维修项目配置表
     * @param repairTypeDTO
     * @return
     */
    Long saveOrUpdateRepairType(RepairTypeDTO repairTypeDTO);

    /**
     * 通过id查询成功维修项目配置表详情
     * @param id
     * @return
     */
    RepairTypeVO getRepairTypeById(Long id);
}
