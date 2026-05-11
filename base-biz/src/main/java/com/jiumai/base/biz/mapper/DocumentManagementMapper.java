package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.DocumentManagement;
import com.jiumai.base.biz.query.DocumentManagementQuery;
import com.jiumai.base.biz.vo.DocumentManagementVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文档管理 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface DocumentManagementMapper extends BaseMapper<DocumentManagement> {

    /**
     * 分页查询文档管理
     * @param page
     * @param query
     * @return
     */
    List<DocumentManagementVO> findDocumentManagementPage(Page<DocumentManagementVO> page, @Param("query") DocumentManagementQuery query);
}
