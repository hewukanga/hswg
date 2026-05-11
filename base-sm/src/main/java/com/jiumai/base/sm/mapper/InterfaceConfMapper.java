package com.jiumai.base.sm.mapper;

import com.jiumai.base.sm.entity.InterfaceConf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.jiumai.base.sm.dto.InterfaceConfDTO;
import com.jiumai.base.sm.query.InterfaceConfQuery;
import java.util.List;

/**
 * <p>
 * 接口管理 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2023-08-21
 */
public interface InterfaceConfMapper extends BaseMapper<InterfaceConf> {

    /**
    * 分页查询接口管理
    * @param page
    * @param query
    * @return
    */
    List<InterfaceConfDTO> findInterfaceConfPage(Page<InterfaceConfDTO> page, @Param("query") InterfaceConfQuery query);
}
