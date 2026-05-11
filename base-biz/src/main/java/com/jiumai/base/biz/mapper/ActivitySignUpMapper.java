package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.ActivitySignUp;
import com.jiumai.base.biz.query.ActivitySignUpQuery;
import com.jiumai.base.biz.vo.ActivitySignUpVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动报名表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface ActivitySignUpMapper extends BaseMapper<ActivitySignUp> {

    /**
     * 分页查询活动报名表
     * @param page
     * @param query
     * @return
     */
    List<ActivitySignUpVO> findActivitySignUpPage(Page<ActivitySignUpVO> page, @Param("query") ActivitySignUpQuery query);
}
