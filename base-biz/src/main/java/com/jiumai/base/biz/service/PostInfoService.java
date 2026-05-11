package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PostInfoDTO;
import com.jiumai.base.biz.entity.PostInfo;
import com.jiumai.base.biz.query.PostInfoQuery;
import com.jiumai.base.biz.vo.PostInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostInfoService extends IService<PostInfo> {

    /**
     * 分页查询岗位信息表
     * @param query
     * @return
     */
    Page<PostInfoVO> findPostInfoPage(PostInfoQuery query);

    /**
     * 添加或更新岗位信息表
     * @param postInfoDTO
     * @return
     */
    Long saveOrUpdatePostInfo(PostInfoDTO postInfoDTO);

    /**
     * 通过id查询成功岗位信息表详情
     * @param id
     * @return
     */
    PostInfoVO getPostInfoById(Long id);
}
