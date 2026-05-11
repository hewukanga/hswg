package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.OilCardDTO;
import com.jiumai.base.biz.entity.OilCard;
import com.jiumai.base.biz.query.OilCardQuery;
import com.jiumai.base.biz.vo.OilCardVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 油卡表 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface OilCardService extends IService<OilCard> {

    /**
     * 分页查询油卡表
     * @param query
     * @return
     */
    Page<OilCardVO> findOilCardPage(OilCardQuery query);

    /**
     * 添加或更新油卡表
     * @param oilCardDTO
     * @return
     */
    Long saveOrUpdateOilCard(OilCardDTO oilCardDTO);

    /**
     * 通过id查询成功油卡表详情
     * @param id
     * @return
     */
    OilCardVO getOilCardById(Long id);
}
