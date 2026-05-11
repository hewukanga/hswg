package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.HelpDTO;
import com.jiumai.base.biz.entity.Help;
import com.jiumai.base.biz.query.HelpQuery;
import com.jiumai.base.biz.vo.HelpVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 帮扶 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface HelpService extends IService<Help> {

    /**
     * 分页查询帮扶
     * @param query
     * @return
     */
    Page<HelpVO> findHelpPage(HelpQuery query);

    /**
     * 添加或更新帮扶
     * @param helpDTO
     * @return
     */
    Long saveOrUpdateHelp(HelpDTO helpDTO);

    /**
     * 通过id查询成功帮扶详情
     * @param id
     * @return
     */
    HelpVO getHelpById(Long id);
}
