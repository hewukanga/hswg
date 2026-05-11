package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.DocumentManagementDTO;
import com.jiumai.base.biz.query.DocumentManagementQuery;
import com.jiumai.base.biz.service.DocumentManagementService;
import com.jiumai.base.biz.vo.DocumentManagementVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 文档管理 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/document-management")
@Api(tags = {"文档管理管理"})
public class DocumentManagementController {

    @Resource
    private DocumentManagementService documentManagementService;

    @PostMapping("findDocumentManagementPage")
    @ApiOperation("分页查询文档管理")
    public ResultDTO<Page<DocumentManagementVO>> findDocumentManagementPage(HttpServletRequest request, @RequestBody DocumentManagementQuery query) {
        ResultDTO<Page<DocumentManagementVO>> result = new ResultDTO<>();
        Page<DocumentManagementVO> page = documentManagementService.findDocumentManagementPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateDocumentManagement")
    @ApiOperation("保存或更新文档管理")
    @OpLog(title = "保存或更新文档管理", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateDocumentManagement(HttpServletRequest request, @RequestBody DocumentManagementDTO documentManagementDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = documentManagementService.saveOrUpdateDocumentManagement(documentManagementDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getDocumentManagementById")
    @ApiOperation("根据ID获取文档管理")
    public ResultDTO<DocumentManagementVO> getDocumentManagementById(Long id) {
        ResultDTO<DocumentManagementVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        DocumentManagementVO documentManagementVO = documentManagementService.getDocumentManagementById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", documentManagementVO);
    }

    @PostMapping("batchRemoveDocumentManagementByIds")
    @ApiOperation("根据ID批量删除文档管理")
    @OpLog(title = "根据ID批量删除文档管理", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveDocumentManagementByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        documentManagementService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
