package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.TemporaryParkingDTO;
import com.jiumai.base.biz.entity.TemporaryParking;
import com.jiumai.base.biz.query.TemporaryParkingQuery;
import com.jiumai.base.biz.vo.TemporaryParkingVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 临停车(数泊推送) 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface TemporaryParkingService extends IService<TemporaryParking> {

    /**
     * 分页查询临停车(数泊推送)
     * @param query
     * @return
     */
    Page<TemporaryParkingVO> findTemporaryParkingPage(TemporaryParkingQuery query);

    /**
     * 添加或更新临停车(数泊推送)
     * @param temporaryParkingDTO
     * @return
     */
    Long saveOrUpdateTemporaryParking(TemporaryParkingDTO temporaryParkingDTO);

    /**
     * 通过id查询成功临停车(数泊推送)详情
     * @param id
     * @return
     */
    TemporaryParkingVO getTemporaryParkingById(Long id);
}
