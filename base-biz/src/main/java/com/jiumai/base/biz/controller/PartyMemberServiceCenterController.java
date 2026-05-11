package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PartyMemberServiceCenterDTO;
import com.jiumai.base.biz.query.PartyMemberServiceCenterQuery;
import com.jiumai.base.biz.service.PartyMemberServiceCenterService;
import com.jiumai.base.biz.vo.PartyMemberServiceCenterVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 党员服务中心 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/party-member-service-center")
@Api(tags = {"党员服务中心管理"})
public class PartyMemberServiceCenterController {

    @Resource
    private PartyMemberServiceCenterService partyMemberServiceCenterService;

    @PostMapping("findPartyMemberServiceCenterPage")
    @ApiOperation("分页查询党员服务中心")
    public ResultDTO<Page<PartyMemberServiceCenterVO>> findPartyMemberServiceCenterPage(HttpServletRequest request, @RequestBody PartyMemberServiceCenterQuery query) {
        ResultDTO<Page<PartyMemberServiceCenterVO>> result = new ResultDTO<>();
        Page<PartyMemberServiceCenterVO> page = partyMemberServiceCenterService.findPartyMemberServiceCenterPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePartyMemberServiceCenter")
    @ApiOperation("保存或更新党员服务中心")
    @OpLog(title = "保存或更新党员服务中心", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePartyMemberServiceCenter(HttpServletRequest request, @RequestBody PartyMemberServiceCenterDTO partyMemberServiceCenterDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = partyMemberServiceCenterService.saveOrUpdatePartyMemberServiceCenter(partyMemberServiceCenterDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPartyMemberServiceCenterById")
    @ApiOperation("根据ID获取党员服务中心")
    public ResultDTO<PartyMemberServiceCenterVO> getPartyMemberServiceCenterById(Long id) {
        ResultDTO<PartyMemberServiceCenterVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PartyMemberServiceCenterVO partyMemberServiceCenterVO = partyMemberServiceCenterService.getPartyMemberServiceCenterById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", partyMemberServiceCenterVO);
    }

    @PostMapping("batchRemovePartyMemberServiceCenterByIds")
    @ApiOperation("根据ID批量删除党员服务中心")
    @OpLog(title = "根据ID批量删除党员服务中心", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePartyMemberServiceCenterByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        partyMemberServiceCenterService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
