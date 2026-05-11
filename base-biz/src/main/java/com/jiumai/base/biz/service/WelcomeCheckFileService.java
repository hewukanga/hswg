package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.WelcomeCheckFileDTO;
import com.jiumai.base.biz.entity.WelcomeCheckFile;
import com.jiumai.base.biz.query.WelcomeCheckFileQuery;
import com.jiumai.base.biz.vo.WelcomeCheckFileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 迎检附件表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface WelcomeCheckFileService extends IService<WelcomeCheckFile> {

    /**
     * 分页查询迎检附件表
     * @param query
     * @return
     */
    Page<WelcomeCheckFileVO> findWelcomeCheckFilePage(WelcomeCheckFileQuery query);

    /**
     * 添加或更新迎检附件表
     * @param welcomeCheckFileDTO
     * @return
     */
    Long saveOrUpdateWelcomeCheckFile(WelcomeCheckFileDTO welcomeCheckFileDTO);

    /**
     * 通过id查询成功迎检附件表详情
     * @param id
     * @return
     */
    WelcomeCheckFileVO getWelcomeCheckFileById(Long id);
}
