package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.UserOpinionDTO;
import com.jiumai.base.biz.entity.UserOpinion;
import com.jiumai.base.biz.query.UserOpinionQuery;
import com.jiumai.base.biz.vo.UserOpinionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 居民意见表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface UserOpinionService extends IService<UserOpinion> {

    /**
     * 分页查询居民意见表
     * @param query
     * @return
     */
    Page<UserOpinionVO> findUserOpinionPage(UserOpinionQuery query);

    /**
     * 添加或更新居民意见表
     * @param userOpinionDTO
     * @return
     */
    Long saveOrUpdateUserOpinion(UserOpinionDTO userOpinionDTO);

    /**
     * 通过id查询成功居民意见表详情
     * @param id
     * @return
     */
    UserOpinionVO getUserOpinionById(Long id);
}
