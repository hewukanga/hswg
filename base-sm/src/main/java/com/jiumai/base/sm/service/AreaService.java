package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.dto.AreaDTO;
import com.jiumai.base.sm.entity.SmArea;
import com.jiumai.base.sm.query.AreaQuery;
import java.util.List;

public interface AreaService extends IService<SmArea> {

    /**
     * 查询有效区域
     *
     * @return
     * @throws Exception
     */
    List<AreaDTO> findAreaList(Long opId) throws Exception;

    /**
     * 获取区域树
     *
     * @return
     * @throws Exception
     */
    List<AreaDTO> findAreaTree() throws Exception;

    /**
     * 根据ID获取区域
     *
     * @param areaId
     * @return
     */
    SmArea getAreaById(long areaId);

    /**
     * 查询操作员可访问的数据，不包含街道
     *
     * @return
     * @throws Exception
     */
    List<AreaDTO> findOpAccessAreaTree(Long opId) throws Exception;

    /**
     * 保存或更新区域
     *
     * @throws Exception
     */
    void saveOrUpdateArea(SmArea smArea) throws Exception;

    /**
     * 查询区域分页列表
     *
     * @param areaQuery
     * @return
     */
    IPage<AreaDTO> findAreaPaging(AreaQuery areaQuery) throws Exception;


}
