package com.jiumai.base.biz.service;

import com.jiumai.base.biz.dto.GridPersonnelDTO;
import com.jiumai.base.biz.entity.GridPersonnel;
import com.jiumai.base.biz.query.GridPersonnelQuery;
import com.jiumai.base.biz.vo.GridPersonnelVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网格人员信息 服务类
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface GridPersonnelService extends IService<GridPersonnel> {

    /**
     * 分页查询网格人员信息
     * @param query
     * @return
     */
    Page<GridPersonnelVO> findGridPersonnelPage(GridPersonnelQuery query);

    /**
     * 添加或更新网格人员信息
     * @param gridPersonnelDTO
     * @return
     */
    Long saveOrUpdateGridPersonnel(GridPersonnelDTO gridPersonnelDTO);

    /**
     * 通过id查询成功网格人员信息详情
     * @param id
     * @return
     */
    GridPersonnelVO getGridPersonnelById(Long id);
}
