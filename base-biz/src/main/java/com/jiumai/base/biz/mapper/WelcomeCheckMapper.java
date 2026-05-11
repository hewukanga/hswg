package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.WelcomeCheck;
import com.jiumai.base.biz.query.WelcomeCheckQuery;
import com.jiumai.base.biz.vo.WelcomeCheckVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 迎检表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface WelcomeCheckMapper extends BaseMapper<WelcomeCheck> {

    /**
     * 分页查询迎检表
     * @param page
     * @param query
     * @return
     */
    List<WelcomeCheckVO> findWelcomeCheckPage(Page<WelcomeCheckVO> page, @Param("query") WelcomeCheckQuery query);
}
