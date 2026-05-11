package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PrivateChatDTO;
import com.jiumai.base.biz.query.PrivateChatQuery;
import com.jiumai.base.biz.service.PrivateChatService;
import com.jiumai.base.biz.vo.PrivateChatVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 私聊消息记录表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/private-chat")
@Api(tags = {"私聊消息记录表管理"})
public class PrivateChatController {

    @Resource
    private PrivateChatService privateChatService;

    @PostMapping("findPrivateChatPage")
    @ApiOperation("分页查询私聊消息记录表")
    public ResultDTO<Page<PrivateChatVO>> findPrivateChatPage(HttpServletRequest request, @RequestBody PrivateChatQuery query) {
        ResultDTO<Page<PrivateChatVO>> result = new ResultDTO<>();
        Page<PrivateChatVO> page = privateChatService.findPrivateChatPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePrivateChat")
    @ApiOperation("保存或更新私聊消息记录表")
    @OpLog(title = "保存或更新私聊消息记录表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePrivateChat(HttpServletRequest request, @RequestBody PrivateChatDTO privateChatDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = privateChatService.saveOrUpdatePrivateChat(privateChatDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPrivateChatById")
    @ApiOperation("根据ID获取私聊消息记录表")
    public ResultDTO<PrivateChatVO> getPrivateChatById(Long id) {
        ResultDTO<PrivateChatVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PrivateChatVO privateChatVO = privateChatService.getPrivateChatById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", privateChatVO);
    }

    @PostMapping("batchRemovePrivateChatByIds")
    @ApiOperation("根据ID批量删除私聊消息记录表")
    @OpLog(title = "根据ID批量删除私聊消息记录表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePrivateChatByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        privateChatService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
