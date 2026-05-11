package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.NoticeDTO;
import com.jiumai.base.biz.query.NoticeQuery;
import com.jiumai.base.biz.service.NoticeService;
import com.jiumai.base.biz.vo.NoticeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 公告表 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/notice")
@Api(tags = {"公告表管理"})
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @PostMapping("findNoticePage")
    @ApiOperation("分页查询公告表")
    public ResultDTO<Page<NoticeVO>> findNoticePage(HttpServletRequest request, @RequestBody NoticeQuery query) {
        ResultDTO<Page<NoticeVO>> result = new ResultDTO<>();
        Page<NoticeVO> page = noticeService.findNoticePage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdateNotice")
    @ApiOperation("保存或更新公告表")
    @OpLog(title = "保存或更新公告表", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdateNotice(HttpServletRequest request, @RequestBody NoticeDTO noticeDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = noticeService.saveOrUpdateNotice(noticeDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getNoticeById")
    @ApiOperation("根据ID获取公告表")
    public ResultDTO<NoticeVO> getNoticeById(Long id) {
        ResultDTO<NoticeVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        NoticeVO noticeVO = noticeService.getNoticeById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", noticeVO);
    }

    @PostMapping("batchRemoveNoticeByIds")
    @ApiOperation("根据ID批量删除公告表")
    @OpLog(title = "根据ID批量删除公告表", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemoveNoticeByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        noticeService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
