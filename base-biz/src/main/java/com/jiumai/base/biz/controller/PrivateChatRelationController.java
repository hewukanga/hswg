package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PrivateChatRelationDTO;
import com.jiumai.base.biz.query.PrivateChatRelationQuery;
import com.jiumai.base.biz.service.PrivateChatRelationService;
import com.jiumai.base.biz.vo.PrivateChatRelationVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 私聊消息记录关系表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/private-chat-relation")
@Api(tags = {"私聊消息记录关系表管理"})
public class PrivateChatRelationController {

    @Resource
    private PrivateChatRelationService privateChatRelationService;

    @PostMapping("findPrivateChatRelationPage")
    @ApiOperation("分页查询私聊消息记录关系表")
    public ResultDTO<Page<PrivateChatRelationVO>> findPrivateChatRelationPage(HttpServletRequest request, @RequestBody PrivateChatRelationQuery query) {
        ResultDTO<Page<PrivateChatRelationVO>> result = new ResultDTO<>();
        Page<PrivateChatRelationVO> page = privateChatRelationService.findPrivateChatRelationPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePrivateChatRelation")
    @ApiOperation("保存或更新私聊消息记录关系表")
    @OpLog(title = "保存或更新私聊消息记录关系表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePrivateChatRelation(HttpServletRequest request, @RequestBody PrivateChatRelationDTO privateChatRelationDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = privateChatRelationService.saveOrUpdatePrivateChatRelation(privateChatRelationDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPrivateChatRelationById")
    @ApiOperation("根据ID获取私聊消息记录关系表")
    public ResultDTO<PrivateChatRelationVO> getPrivateChatRelationById(Long id) {
        ResultDTO<PrivateChatRelationVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PrivateChatRelationVO privateChatRelationVO = privateChatRelationService.getPrivateChatRelationById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", privateChatRelationVO);
    }

    @PostMapping("batchRemovePrivateChatRelationByIds")
    @ApiOperation("根据ID批量删除私聊消息记录关系表")
    @OpLog(title = "根据ID批量删除私聊消息记录关系表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePrivateChatRelationByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        privateChatRelationService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
