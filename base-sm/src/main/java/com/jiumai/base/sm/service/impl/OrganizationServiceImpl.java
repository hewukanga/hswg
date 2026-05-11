/*
 * organizationServiceImpl.java Created on 2016年10月25日 下午8:16:03
 * Copyright (c) 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiumai.base.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.PageUtils;
import com.jiumai.base.sm.dto.OrganizationDTO;
import com.jiumai.base.sm.entity.SmOrganization;
import com.jiumai.base.sm.entity.SmRelOpOrg;
import com.jiumai.base.sm.enums.OrgTypeEnum;
import com.jiumai.base.sm.enums.RelTypeEnum;
import com.jiumai.base.sm.mapper.SmOrganizationMapper;
import com.jiumai.base.sm.query.OrganizationQuery;
import com.jiumai.base.sm.service.OrganizationService;
import com.jiumai.base.sm.service.RelOpOrgService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author louis<8 3 0 3 0 2 4 6 @ qq.com>
 * @version 1.0
 * @date 2018-02-12
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<SmOrganizationMapper, SmOrganization> implements OrganizationService {
    @Resource
    private SmOrganizationMapper smOrganizationMapper;

    @Resource
    private RelOpOrgService relOpOrgService;

    @Override
    public List<SmOrganization> searchPayeeOrgs(String searchKey) {
        List<String> inList = new ArrayList<String>();
        inList.add(OrgTypeEnum.CHILDREN_COMPANY.getValue());
        inList.add(OrgTypeEnum.COMPANY.getValue());
        inList.add(OrgTypeEnum.PART_COMPANY.getValue());
        List<SmOrganization> orgs = smOrganizationMapper.selectList(new QueryWrapper<SmOrganization>()
                .lambda()
                .in(SmOrganization::getOrgType, inList)
                .isNull(SmOrganization::getExpireDate)
                .like(CommonFuntions.isNotEmptyObject(searchKey), SmOrganization::getOrgName, searchKey)
                .select()
        );
        return orgs;
    }

    @Override
    public long save(OrganizationDTO organizationDTO) throws Exception {
        smOrganizationMapper.insert(organizationDTO);
        long orgId = organizationDTO.getOrgId();
        SmOrganization parentOrg = this.getOrganizationByOrgId(organizationDTO.getParentId());
        SmOrganization smOrganization = new SmOrganization();
        smOrganization.setOrgId(orgId);
        smOrganization.setOrgPath(parentOrg.getOrgPath() + orgId + "#");
        smOrganizationMapper.updateById(smOrganization);
        //保存操作员与组织关系
        SmRelOpOrg rel = new SmRelOpOrg();
        rel.setRelType(RelTypeEnum.REAL);
        rel.setCreateId(organizationDTO.getCreateId());
        rel.setOpId(organizationDTO.getCreateId());
        rel.setOrgId(orgId);
        relOpOrgService.save(rel);
        //父组织为real的设为UNREAL
        List<SmRelOpOrg> opOrgs = relOpOrgService.list(new QueryWrapper<SmRelOpOrg>()
                .lambda()
                .eq(SmRelOpOrg::getOrgId, organizationDTO.getParentId())
                .isNull(SmRelOpOrg::getExpireDate)
        );

        for (SmRelOpOrg opOrg : opOrgs) {
            if (RelTypeEnum.REAL.getValue().equals(opOrg.getRelType()) && !opOrg.getRelId().equals(rel.getRelId())) {
                opOrg.setRelType(RelTypeEnum.UNREAL);
                relOpOrgService.updateById(opOrg);
            }
        }
        return orgId;
    }

    @Override
    public List<OrganizationDTO> findOrganizationList() throws Exception {

        List<SmOrganization> orgList = smOrganizationMapper.selectList(new QueryWrapper<SmOrganization>()
                .lambda()
                .isNull(SmOrganization::getExpireDate)
                .orderByDesc(SmOrganization::getCreateDate)
                .select()
        );

        List<OrganizationDTO> orgs = new ArrayList<>();

        for (SmOrganization smOrg : orgList) {
            OrganizationDTO orgDTO = new OrganizationDTO();
            BeanUtils.copyProperties(smOrg, orgDTO);
            orgs.add(orgDTO);
        }

        return orgs;
    }

    @Override
    public List<SmOrganization> findOrganizationListByParentId(long parentId) throws Exception {

        return smOrganizationMapper.selectList(new QueryWrapper<SmOrganization>().lambda().eq(SmOrganization::getParentId, parentId).select());

    }

    @Override
    public OrganizationDTO getOrganizationByOrgId(long orgId) throws Exception {

        OrganizationDTO organizationDTO = new OrganizationDTO();

        SmOrganization smOrganization = smOrganizationMapper.selectById(orgId);

        BeanUtils.copyProperties(smOrganization, organizationDTO);

        return organizationDTO;

    }

    @Override
    public List<SmOrganization> getOrganizationByOrgName(String orgName) throws Exception {

        return smOrganizationMapper.selectList(new QueryWrapper<SmOrganization>()
                .lambda()
                .like(SmOrganization::getOrgName, orgName)
                .isNull(SmOrganization::getExpireDate)
                .select()
        );
    }

    @Override
    public IPage<OrganizationDTO> findOrganizationPaging(OrganizationQuery queryCondition) throws Exception {
        PageUtils<OrganizationDTO> pageUtils = new PageUtils<>();
        Page<OrganizationDTO> page = pageUtils.getPageByPageParam(queryCondition);
        smOrganizationMapper.findOrganizationPaging(page, queryCondition);
        return page;
    }

    @Override
    public int updateOrganization(OrganizationDTO organizationDTO) throws Exception {


        return smOrganizationMapper.updateById(organizationDTO);
    }

    @Override
    public int deleteOrg(Long orgId, Long opId) throws Exception {

        long parentCount = smOrganizationMapper.selectCount(new QueryWrapper<SmOrganization>()
                .lambda()
                .eq(SmOrganization::getParentId, orgId)
                .isNull(SmOrganization::getExpireDate)
        );
        if (parentCount > 0) {
            throw new Exception("该组织下有子组织，不能删除！");
        }
        SmOrganization org = new SmOrganization();
        org.setOrgId(orgId);
        org.setModifyId(opId);
        org.setModifyDate(LocalDateTime.now());
        org.setExpireDate(new Date());

        int index = smOrganizationMapper.updateById(org);
        if (index <= 0) {
            throw new Exception("删除失败！");
        }
        return index;
    }


    @Override
    public Long checkOrgName(OrganizationDTO orgDTO) throws Exception {

        List<SmOrganization> list = smOrganizationMapper.selectList(new QueryWrapper<SmOrganization>()
                .lambda()
                .isNull(SmOrganization::getExpireDate)
                .eq(SmOrganization::getOrgName, orgDTO.getOrgName())
                .eq(SmOrganization::getParentId, orgDTO.getParentId())
                .select()
        );
        if (list == null || list.size() <= 0) {
            return 0L;
        }
        SmOrganization org = list.get(0);
        if (org.getOrgId().longValue() == orgDTO.getOrgId().longValue()) {
            return 0L;
        }
        return 1L;
    }

    @Override
    @Deprecated
    public List<OrganizationDTO> findDisabedOrgTreeByOpId(Long opId) {

        List<SmOrganization> allOrgss = smOrganizationMapper.selectList(new QueryWrapper<SmOrganization>()
                .lambda()
                .isNull(SmOrganization::getExpireDate)
                .orderByAsc(SmOrganization::getParentId)
        );
        List<OrganizationDTO> tempOrgs = new ArrayList<OrganizationDTO>();
        if (opId.longValue() != 1) {
            List<SmRelOpOrg> opOrgs = relOpOrgService.list(new QueryWrapper<SmRelOpOrg>()
                    .lambda()
                    .isNull(SmRelOpOrg::getExpireDate)
                    .eq(SmRelOpOrg::getOpId, opId)
            );
            List<Long> opOrgIds = new ArrayList<Long>();

            for (SmRelOpOrg rel : opOrgs) {
                opOrgIds.add(rel.getOrgId());
            }

            for (SmOrganization org : allOrgss) {
                OrganizationDTO dto = new OrganizationDTO(org);
                if (opId.longValue() == 1 || opOrgIds.contains(org.getOrgId().longValue())) {
                    // dto.setDisabled(false);
                }
                tempOrgs.add(dto);
            }

            return organizationTree(tempOrgs);
        } else {
            for (SmOrganization org : allOrgss) {
                OrganizationDTO dto = new OrganizationDTO(org);
                // dto.setDisabled(false);
                tempOrgs.add(dto);
            }
            return organizationTree(tempOrgs);
        }

    }

    @Override
    public List<OrganizationDTO> findOrganizationTreeByOpId(Long opId) {
        List<OrganizationDTO> access = smOrganizationMapper.findAccessOrgsByOpId(opId);
        return organizationTree(access);

    }

    /**
     * 组织树
     *
     * @return
     * @throws Exception
     */
    private List<OrganizationDTO> organizationTree(List<OrganizationDTO> orgList) {

        List<OrganizationDTO> rootTrees = new ArrayList<OrganizationDTO>();

        for (OrganizationDTO tree : orgList) {
            if (tree.getParentId().longValue() == tree.getOrgId().longValue()) {
                rootTrees.add(tree);
            }
            for (OrganizationDTO t : orgList) {
                if (t.getParentId().longValue() == t.getOrgId().longValue()) {
                    continue;
                }
                if (t.getParentId().longValue() == tree.getOrgId().longValue()) {
                    if (tree.getChildrens() == null) {
                        List<OrganizationDTO> myChildrens = new ArrayList<OrganizationDTO>();
                        myChildrens.add(t);
                        tree.setChildrens(myChildrens);
                    } else {
                        tree.getChildrens().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }

    @Override
    public SmRelOpOrg getRelOpOrgByOpOrgId(Long opId, Long orgId) {
        return relOpOrgService.getOne(new QueryWrapper<SmRelOpOrg>()
                .lambda()
                .eq(SmRelOpOrg::getOpId, opId)
                .eq(SmRelOpOrg::getOrgId, orgId)
                .isNull(SmRelOpOrg::getExpireDate)
        );
    }

    @Override
    public List<OrganizationDTO> searchOrganizations(String searchKey, LoginOperator nowOperDTO) {
        List<OrganizationDTO> list = smOrganizationMapper.searchOrganizations(searchKey, nowOperDTO.getOpId(), RelTypeEnum.REAL.getValue());
        this.setParentOrgName(list);
        return list;
    }

    private void setParentOrgName(List<OrganizationDTO> list) {
        Map<Long, String> orgMap = new HashMap();
        List<SmOrganization> orgList = smOrganizationMapper.selectList(new QueryWrapper<>());
        if(CommonFuntions.isEmptyObject(orgList)){
            return;
        }
        for (SmOrganization org : orgList) {
            orgMap.put(org.getOrgId(), org.getOrgName());
        }

        for (OrganizationDTO organizationDTO : list) {
            String orgPath = organizationDTO.getOrgPath();
            String parentOrgName = "";
            String[] idArray = orgPath.substring(1, orgPath.length() - 1).split("#");
            for (int i = 0; i < idArray.length - 1; i++) {
                parentOrgName += ("".equals(parentOrgName) ? "" : "/") + orgMap.get(Long.valueOf(idArray[i]));
            }
            organizationDTO.setParentOrgName(parentOrgName);
        }
    }
}
