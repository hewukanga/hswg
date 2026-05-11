package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PostAddress;
import com.jiumai.base.biz.query.PostAddressQuery;
import com.jiumai.base.biz.vo.PostAddressVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 岗位地址信息表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostAddressMapper extends BaseMapper<PostAddress> {

    /**
     * 分页查询岗位地址信息表
     * @param page
     * @param query
     * @return
     */
    List<PostAddressVO> findPostAddressPage(Page<PostAddressVO> page, @Param("query") PostAddressQuery query);
}
