package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.WelcomeCheckFile;
import com.jiumai.base.biz.query.WelcomeCheckFileQuery;
import com.jiumai.base.biz.vo.WelcomeCheckFileVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 迎检附件表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface WelcomeCheckFileMapper extends BaseMapper<WelcomeCheckFile> {

    /**
     * 分页查询迎检附件表
     * @param page
     * @param query
     * @return
     */
    List<WelcomeCheckFileVO> findWelcomeCheckFilePage(Page<WelcomeCheckFileVO> page, @Param("query") WelcomeCheckFileQuery query);
}
