package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.WelcomeCheckOpDTO;
import com.jiumai.base.biz.entity.WelcomeCheckOp;
import com.jiumai.base.biz.query.WelcomeCheckOpQuery;
import com.jiumai.base.biz.vo.WelcomeCheckOpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 迎检通知人员表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface WelcomeCheckOpService extends IService<WelcomeCheckOp> {

    /**
     * 分页查询迎检通知人员表
     * @param query
     * @return
     */
    Page<WelcomeCheckOpVO> findWelcomeCheckOpPage(WelcomeCheckOpQuery query);

    /**
     * 添加或更新迎检通知人员表
     * @param welcomeCheckOpDTO
     * @return
     */
    Long saveOrUpdateWelcomeCheckOp(WelcomeCheckOpDTO welcomeCheckOpDTO);

    /**
     * 通过id查询成功迎检通知人员表详情
     * @param id
     * @return
     */
    WelcomeCheckOpVO getWelcomeCheckOpById(Long id);
}
