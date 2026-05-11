package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.LeavingMessageDTO;
import com.jiumai.base.biz.query.LeavingMessageQuery;
import com.jiumai.base.biz.service.LeavingMessageService;
import com.jiumai.base.biz.vo.LeavingMessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 留言表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/leaving-message")
@Api(tags = {"留言表管理"})
public class LeavingMessageController {

    @Resource
    private LeavingMessageService leavingMessageService;

    @PostMapping("findLeavingMessagePage")
    @ApiOperation("分页查询留言表")
    public ResultDTO<Page<LeavingMessageVO>> findLeavingMessagePage(HttpServletRequest request, @RequestBody LeavingMessageQuery query) {
        ResultDTO<Page<LeavingMessageVO>> result = new ResultDTO<>();
        Page<LeavingMessageVO> page = leavingMessageService.findLeavingMessagePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateLeavingMessage")
    @ApiOperation("保存或更新留言表")
    @OpLog(title = "保存或更新留言表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateLeavingMessage(HttpServletRequest request, @RequestBody LeavingMessageDTO leavingMessageDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = leavingMessageService.saveOrUpdateLeavingMessage(leavingMessageDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getLeavingMessageById")
    @ApiOperation("根据ID获取留言表")
    public ResultDTO<LeavingMessageVO> getLeavingMessageById(Long id) {
        ResultDTO<LeavingMessageVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        LeavingMessageVO leavingMessageVO = leavingMessageService.getLeavingMessageById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", leavingMessageVO);
    }

    @PostMapping("batchRemoveLeavingMessageByIds")
    @ApiOperation("根据ID批量删除留言表")
    @OpLog(title = "根据ID批量删除留言表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveLeavingMessageByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        leavingMessageService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
