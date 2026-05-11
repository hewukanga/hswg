package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.UserOpinion;
import com.jiumai.base.biz.query.UserOpinionQuery;
import com.jiumai.base.biz.vo.UserOpinionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 居民意见表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserOpinionMapper extends BaseMapper<UserOpinion> {

    /**
     * 分页查询居民意见表
     * @param page
     * @param query
     * @return
     */
    List<UserOpinionVO> findUserOpinionPage(Page<UserOpinionVO> page, @Param("query") UserOpinionQuery query);
}
