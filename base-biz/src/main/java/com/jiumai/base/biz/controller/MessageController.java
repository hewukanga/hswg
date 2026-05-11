package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MessageDTO;
import com.jiumai.base.biz.query.MessageQuery;
import com.jiumai.base.biz.service.MessageService;
import com.jiumai.base.biz.vo.MessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 通知互动消息表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/message")
@Api(tags = {"通知互动消息表管理"})
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("findMessagePage")
    @ApiOperation("分页查询通知互动消息表")
    public ResultDTO<Page<MessageVO>> findMessagePage(HttpServletRequest request, @RequestBody MessageQuery query) {
        ResultDTO<Page<MessageVO>> result = new ResultDTO<>();
        Page<MessageVO> page = messageService.findMessagePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateMessage")
    @ApiOperation("保存或更新通知互动消息表")
    @OpLog(title = "保存或更新通知互动消息表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateMessage(HttpServletRequest request, @RequestBody MessageDTO messageDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = messageService.saveOrUpdateMessage(messageDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getMessageById")
    @ApiOperation("根据ID获取通知互动消息表")
    public ResultDTO<MessageVO> getMessageById(Long id) {
        ResultDTO<MessageVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        MessageVO messageVO = messageService.getMessageById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", messageVO);
    }

    @PostMapping("batchRemoveMessageByIds")
    @ApiOperation("根据ID批量删除通知互动消息表")
    @OpLog(title = "根据ID批量删除通知互动消息表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveMessageByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        messageService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
