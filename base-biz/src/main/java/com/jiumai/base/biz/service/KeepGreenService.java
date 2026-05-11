package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.KeepGreenDTO;
import com.jiumai.base.biz.entity.KeepGreen;
import com.jiumai.base.biz.query.KeepGreenQuery;
import com.jiumai.base.biz.vo.KeepGreenVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保绿 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface KeepGreenService extends IService<KeepGreen> {

    /**
     * 分页查询保绿
     * @param query
     * @return
     */
    Page<KeepGreenVO> findKeepGreenPage(KeepGreenQuery query);

    /**
     * 添加或更新保绿
     * @param keepGreenDTO
     * @return
     */
    Long saveOrUpdateKeepGreen(KeepGreenDTO keepGreenDTO);

    /**
     * 通过id查询成功保绿详情
     * @param id
     * @return
     */
    KeepGreenVO getKeepGreenById(Long id);
}
