package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.FamilyCardInfoDTO;
import com.jiumai.base.biz.entity.FamilyCardInfo;
import com.jiumai.base.biz.query.FamilyCardInfoQuery;
import com.jiumai.base.biz.vo.FamilyCardInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 亲情卡数据 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface FamilyCardInfoService extends IService<FamilyCardInfo> {

    /**
     * 分页查询亲情卡数据
     * @param query
     * @return
     */
    Page<FamilyCardInfoVO> findFamilyCardInfoPage(FamilyCardInfoQuery query);

    /**
     * 添加或更新亲情卡数据
     * @param familyCardInfoDTO
     * @return
     */
    Long saveOrUpdateFamilyCardInfo(FamilyCardInfoDTO familyCardInfoDTO);

    /**
     * 通过id查询成功亲情卡数据详情
     * @param id
     * @return
     */
    FamilyCardInfoVO getFamilyCardInfoById(Long id);
}
