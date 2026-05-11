package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PostPersonInfo;
import com.jiumai.base.biz.query.PostPersonInfoQuery;
import com.jiumai.base.biz.vo.PostPersonInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 岗位人员信息表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostPersonInfoMapper extends BaseMapper<PostPersonInfo> {

    /**
     * 分页查询岗位人员信息表
     * @param page
     * @param query
     * @return
     */
    List<PostPersonInfoVO> findPostPersonInfoPage(Page<PostPersonInfoVO> page, @Param("query") PostPersonInfoQuery query);
}
