package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PictureDocument;
import com.jiumai.base.biz.query.PictureDocumentQuery;
import com.jiumai.base.biz.vo.PictureDocumentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文档管理表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PictureDocumentMapper extends BaseMapper<PictureDocument> {

    /**
     * 分页查询文档管理表
     * @param page
     * @param query
     * @return
     */
    List<PictureDocumentVO> findPictureDocumentPage(Page<PictureDocumentVO> page, @Param("query") PictureDocumentQuery query);
}
