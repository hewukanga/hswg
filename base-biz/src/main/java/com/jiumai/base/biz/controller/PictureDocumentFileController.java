package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PictureDocumentFileDTO;
import com.jiumai.base.biz.query.PictureDocumentFileQuery;
import com.jiumai.base.biz.service.PictureDocumentFileService;
import com.jiumai.base.biz.vo.PictureDocumentFileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 迎检附件表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/picture-document-file")
@Api(tags = {"迎检附件表管理"})
public class PictureDocumentFileController {

    @Resource
    private PictureDocumentFileService pictureDocumentFileService;

    @PostMapping("findPictureDocumentFilePage")
    @ApiOperation("分页查询迎检附件表")
    public ResultDTO<Page<PictureDocumentFileVO>> findPictureDocumentFilePage(HttpServletRequest request, @RequestBody PictureDocumentFileQuery query) {
        ResultDTO<Page<PictureDocumentFileVO>> result = new ResultDTO<>();
        Page<PictureDocumentFileVO> page = pictureDocumentFileService.findPictureDocumentFilePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePictureDocumentFile")
    @ApiOperation("保存或更新迎检附件表")
    @OpLog(title = "保存或更新迎检附件表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePictureDocumentFile(HttpServletRequest request, @RequestBody PictureDocumentFileDTO pictureDocumentFileDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = pictureDocumentFileService.saveOrUpdatePictureDocumentFile(pictureDocumentFileDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPictureDocumentFileById")
    @ApiOperation("根据ID获取迎检附件表")
    public ResultDTO<PictureDocumentFileVO> getPictureDocumentFileById(Long id) {
        ResultDTO<PictureDocumentFileVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PictureDocumentFileVO pictureDocumentFileVO = pictureDocumentFileService.getPictureDocumentFileById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", pictureDocumentFileVO);
    }

    @PostMapping("batchRemovePictureDocumentFileByIds")
    @ApiOperation("根据ID批量删除迎检附件表")
    @OpLog(title = "根据ID批量删除迎检附件表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePictureDocumentFileByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        pictureDocumentFileService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
