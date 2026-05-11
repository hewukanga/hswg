package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.MessageNoticeDTO;
import com.jiumai.base.biz.query.MessageNoticeQuery;
import com.jiumai.base.biz.service.MessageNoticeService;
import com.jiumai.base.biz.vo.MessageNoticeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 消息通知表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/message-notice")
@Api(tags = {"消息通知表管理"})
public class MessageNoticeController {

    @Resource
    private MessageNoticeService messageNoticeService;

    @PostMapping("findMessageNoticePage")
    @ApiOperation("分页查询消息通知表")
    public ResultDTO<Page<MessageNoticeVO>> findMessageNoticePage(HttpServletRequest request, @RequestBody MessageNoticeQuery query) {
        ResultDTO<Page<MessageNoticeVO>> result = new ResultDTO<>();
        Page<MessageNoticeVO> page = messageNoticeService.findMessageNoticePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateMessageNotice")
    @ApiOperation("保存或更新消息通知表")
    @OpLog(title = "保存或更新消息通知表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateMessageNotice(HttpServletRequest request, @RequestBody MessageNoticeDTO messageNoticeDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = messageNoticeService.saveOrUpdateMessageNotice(messageNoticeDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getMessageNoticeById")
    @ApiOperation("根据ID获取消息通知表")
    public ResultDTO<MessageNoticeVO> getMessageNoticeById(Long id) {
        ResultDTO<MessageNoticeVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        MessageNoticeVO messageNoticeVO = messageNoticeService.getMessageNoticeById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", messageNoticeVO);
    }

    @PostMapping("batchRemoveMessageNoticeByIds")
    @ApiOperation("根据ID批量删除消息通知表")
    @OpLog(title = "根据ID批量删除消息通知表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveMessageNoticeByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        messageNoticeService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
