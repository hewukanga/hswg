package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PostConfig;
import com.jiumai.base.biz.query.PostConfigQuery;
import com.jiumai.base.biz.vo.PostConfigVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 岗位配置信息表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostConfigMapper extends BaseMapper<PostConfig> {

    /**
     * 分页查询岗位配置信息表
     * @param page
     * @param query
     * @return
     */
    List<PostConfigVO> findPostConfigPage(Page<PostConfigVO> page, @Param("query") PostConfigQuery query);
}
