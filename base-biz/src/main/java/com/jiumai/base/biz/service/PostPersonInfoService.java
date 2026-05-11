package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.PostPersonInfoDTO;
import com.jiumai.base.biz.entity.PostPersonInfo;
import com.jiumai.base.biz.query.PostPersonInfoQuery;
import com.jiumai.base.biz.vo.PostPersonInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位人员信息表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PostPersonInfoService extends IService<PostPersonInfo> {

    /**
     * 分页查询岗位人员信息表
     * @param query
     * @return
     */
    Page<PostPersonInfoVO> findPostPersonInfoPage(PostPersonInfoQuery query);

    /**
     * 添加或更新岗位人员信息表
     * @param postPersonInfoDTO
     * @return
     */
    Long saveOrUpdatePostPersonInfo(PostPersonInfoDTO postPersonInfoDTO);

    /**
     * 通过id查询成功岗位人员信息表详情
     * @param id
     * @return
     */
    PostPersonInfoVO getPostPersonInfoById(Long id);
}
