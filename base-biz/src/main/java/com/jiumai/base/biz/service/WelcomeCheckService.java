package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.WelcomeCheckDTO;
import com.jiumai.base.biz.entity.WelcomeCheck;
import com.jiumai.base.biz.query.WelcomeCheckQuery;
import com.jiumai.base.biz.vo.WelcomeCheckVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 迎检表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface WelcomeCheckService extends IService<WelcomeCheck> {

    /**
     * 分页查询迎检表
     * @param query
     * @return
     */
    Page<WelcomeCheckVO> findWelcomeCheckPage(WelcomeCheckQuery query);

    /**
     * 添加或更新迎检表
     * @param welcomeCheckDTO
     * @return
     */
    Long saveOrUpdateWelcomeCheck(WelcomeCheckDTO welcomeCheckDTO);

    /**
     * 通过id查询成功迎检表详情
     * @param id
     * @return
     */
    WelcomeCheckVO getWelcomeCheckById(Long id);
}
