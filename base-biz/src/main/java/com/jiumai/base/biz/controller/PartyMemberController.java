package com.jiumai.base.biz.controller;

import com.jiumai.base.common.core.annotation.OpLog;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.BusinessTypeEnum;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.biz.dto.PartyMemberDTO;
import com.jiumai.base.biz.query.PartyMemberQuery;
import com.jiumai.base.biz.service.PartyMemberService;
import com.jiumai.base.biz.vo.PartyMemberVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 党员信息 管理
 * </p>
 *
 * @author mysqlGen
 * @since 2026-05-11
 */
@RestController
@RequestMapping("/d-admin/party-member")
@Api(tags = {"党员信息管理"})
public class PartyMemberController {

    @Resource
    private PartyMemberService partyMemberService;

    @PostMapping("findPartyMemberPage")
    @ApiOperation("分页查询党员信息")
    public ResultDTO<Page<PartyMemberVO>> findPartyMemberPage(HttpServletRequest request, @RequestBody PartyMemberQuery query) {
        ResultDTO<Page<PartyMemberVO>> result = new ResultDTO<>();
        Page<PartyMemberVO> page = partyMemberService.findPartyMemberPage(query);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", page);
    }

    @PostMapping("saveOrUpdatePartyMember")
    @ApiOperation("保存或更新党员信息")
    @OpLog(title = "保存或更新党员信息", businessType = BusinessTypeEnum.INSERT_OR_UPDATE, isSaveRequestData = true)
    public ResultDTO<Long> saveOrUpdatePartyMember(HttpServletRequest request, @RequestBody PartyMemberDTO partyMemberDTO) {
        ResultDTO<Long> result = new ResultDTO<>();
        Long id = partyMemberService.saveOrUpdatePartyMember(partyMemberDTO);
        if (CommonFuntions.isNotEmptyObject(id)) {
            return result.set(ResultCodeEnum.SUCCESS, "保存成功", id);
        }
        return result.set(ResultCodeEnum.FAIL, "保存失败");
    }

    @GetMapping("getPartyMemberById")
    @ApiOperation("根据ID获取党员信息")
    public ResultDTO<PartyMemberVO> getPartyMemberById(Long id) {
        ResultDTO<PartyMemberVO> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(id)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "查询失败,id为空");
        }
        PartyMemberVO partyMemberVO = partyMemberService.getPartyMemberById(id);
        return result.set(ResultCodeEnum.SUCCESS, "查询成功", partyMemberVO);
    }

    @PostMapping("batchRemovePartyMemberByIds")
    @ApiOperation("根据ID批量删除党员信息")
    @OpLog(title = "根据ID批量删除党员信息", businessType = BusinessTypeEnum.DELETE, isSaveRequestData = true)
    public ResultDTO<String> batchRemovePartyMemberByIds(@RequestBody List<Long> ids) {
        ResultDTO<String> result = new ResultDTO<>();
        if (CommonFuntions.isEmptyObject(ids)) {
            return result.set(ResultCodeEnum.ERROR_MISSING_PARAMS, "删除失败,ids为空");
        }
        partyMemberService.removeByIds(ids);
        return result.set(ResultCodeEnum.SUCCESS, "删除成功");
    }
}
