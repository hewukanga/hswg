package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OvertimeProcessConfigDTO;
import com.jiumai.base.biz.entity.OvertimeProcessConfig;
import com.jiumai.base.biz.query.OvertimeProcessConfigQuery;
import com.jiumai.base.biz.vo.OvertimeProcessConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 加班配置表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OvertimeProcessConfigService extends IService<OvertimeProcessConfig> {

    /**
     * 分页查询加班配置表
     * @param query
     * @return
     */
    Page<OvertimeProcessConfigVO> findOvertimeProcessConfigPage(OvertimeProcessConfigQuery query);

    /**
     * 添加或更新加班配置表
     * @param overtimeProcessConfigDTO
     * @return
     */
    Long saveOrUpdateOvertimeProcessConfig(OvertimeProcessConfigDTO overtimeProcessConfigDTO);

    /**
     * 通过id查询成功加班配置表详情
     * @param id
     * @return
     */
    OvertimeProcessConfigVO getOvertimeProcessConfigById(Long id);
}
