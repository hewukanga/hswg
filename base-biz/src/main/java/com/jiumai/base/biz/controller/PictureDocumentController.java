package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PictureDocumentDTO;
import com.jiumai.base.biz.query.PictureDocumentQuery;
import com.jiumai.base.biz.service.PictureDocumentService;
import com.jiumai.base.biz.vo.PictureDocumentVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 文档管理表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/picture-document")
@Api(tags = {"文档管理表管理"})
public class PictureDocumentController {

    @Resource
    private PictureDocumentService pictureDocumentService;

    @PostMapping("findPictureDocumentPage")
    @ApiOperation("分页查询文档管理表")
    public ResultDTO<Page<PictureDocumentVO>> findPictureDocumentPage(HttpServletRequest request, @RequestBody PictureDocumentQuery query) {
        ResultDTO<Page<PictureDocumentVO>> result = new ResultDTO<>();
        Page<PictureDocumentVO> page = pictureDocumentService.findPictureDocumentPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePictureDocument")
    @ApiOperation("保存或更新文档管理表")
    @OpLog(title = "保存或更新文档管理表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePictureDocument(HttpServletRequest request, @RequestBody PictureDocumentDTO pictureDocumentDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = pictureDocumentService.saveOrUpdatePictureDocument(pictureDocumentDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPictureDocumentById")
    @ApiOperation("根据ID获取文档管理表")
    public ResultDTO<PictureDocumentVO> getPictureDocumentById(Long id) {
        ResultDTO<PictureDocumentVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PictureDocumentVO pictureDocumentVO = pictureDocumentService.getPictureDocumentById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", pictureDocumentVO);
    }

    @PostMapping("batchRemovePictureDocumentByIds")
    @ApiOperation("根据ID批量删除文档管理表")
    @OpLog(title = "根据ID批量删除文档管理表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePictureDocumentByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        pictureDocumentService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
