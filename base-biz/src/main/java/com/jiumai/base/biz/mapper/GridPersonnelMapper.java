package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.GridPersonnel;
import com.jiumai.base.biz.query.GridPersonnelQuery;
import com.jiumai.base.biz.vo.GridPersonnelVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 网格人员信息 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface GridPersonnelMapper extends BaseMapper<GridPersonnel> {

    /**
     * 分页查询网格人员信息
     * @param page
     * @param query
     * @return
     */
    List<GridPersonnelVO> findGridPersonnelPage(Page<GridPersonnelVO> page, @Param("query") GridPersonnelQuery query);
}
