package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.Help;
import com.jiumai.base.biz.query.HelpQuery;
import com.jiumai.base.biz.vo.HelpVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 帮扶 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface HelpMapper extends BaseMapper<Help> {

    /**
     * 分页查询帮扶
     * @param page
     * @param query
     * @return
     */
    List<HelpVO> findHelpPage(Page<HelpVO> page, @Param("query") HelpQuery query);
}
