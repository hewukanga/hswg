package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PostConfigDTO;
import com.jiumai.base.biz.entity.PostConfig;
import com.jiumai.base.biz.query.PostConfigQuery;
import com.jiumai.base.biz.vo.PostConfigVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位配置信息表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostConfigService extends IService<PostConfig> {

    /**
     * 分页查询岗位配置信息表
     * @param query
     * @return
     */
    Page<PostConfigVO> findPostConfigPage(PostConfigQuery query);

    /**
     * 添加或更新岗位配置信息表
     * @param postConfigDTO
     * @return
     */
    Long saveOrUpdatePostConfig(PostConfigDTO postConfigDTO);

    /**
     * 通过id查询成功岗位配置信息表详情
     * @param id
     * @return
     */
    PostConfigVO getPostConfigById(Long id);
}
