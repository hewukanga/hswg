package com.jiumai.base.biz.mapper;

import com.jiumai.base.biz.entity.PictureDocumentFile;
import com.jiumai.base.biz.query.PictureDocumentFileQuery;
import com.jiumai.base.biz.vo.PictureDocumentFileVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 迎检附件表 Mapper 接口
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
public interface PictureDocumentFileMapper extends BaseMapper<PictureDocumentFile> {

    /**
     * 分页查询迎检附件表
     * @param page
     * @param query
     * @return
     */
    List<PictureDocumentFileVO> findPictureDocumentFilePage(Page<PictureDocumentFileVO> page, @Param("query") PictureDocumentFileQuery query);
}
