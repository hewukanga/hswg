package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PostAddressDTO;
import com.jiumai.base.biz.entity.PostAddress;
import com.jiumai.base.biz.query.PostAddressQuery;
import com.jiumai.base.biz.vo.PostAddressVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位地址信息表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostAddressService extends IService<PostAddress> {

    /**
     * 分页查询岗位地址信息表
     * @param query
     * @return
     */
    Page<PostAddressVO> findPostAddressPage(PostAddressQuery query);

    /**
     * 添加或更新岗位地址信息表
     * @param postAddressDTO
     * @return
     */
    Long saveOrUpdatePostAddress(PostAddressDTO postAddressDTO);

    /**
     * 通过id查询成功岗位地址信息表详情
     * @param id
     * @return
     */
    PostAddressVO getPostAddressById(Long id);
}
