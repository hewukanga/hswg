package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.WelcomeCheckOp;
import com.jiumai.base.biz.query.WelcomeCheckOpQuery;
import com.jiumai.base.biz.vo.WelcomeCheckOpVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 迎检通知人员表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface WelcomeCheckOpMapper extends BaseMapper<WelcomeCheckOp> {

    /**
     * 分页查询迎检通知人员表
     * @param page
     * @param query
     * @return
     */
    List<WelcomeCheckOpVO> findWelcomeCheckOpPage(Page<WelcomeCheckOpVO> page, @Param("query") WelcomeCheckOpQuery query);
}
