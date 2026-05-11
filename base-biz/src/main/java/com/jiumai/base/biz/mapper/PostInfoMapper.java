package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PostInfo;
import com.jiumai.base.biz.query.PostInfoQuery;
import com.jiumai.base.biz.vo.PostInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 岗位信息表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostInfoMapper extends BaseMapper<PostInfo> {

    /**
     * 分页查询岗位信息表
     * @param page
     * @param query
     * @return
     */
    List<PostInfoVO> findPostInfoPage(Page<PostInfoVO> page, @Param("query") PostInfoQuery query);
}
