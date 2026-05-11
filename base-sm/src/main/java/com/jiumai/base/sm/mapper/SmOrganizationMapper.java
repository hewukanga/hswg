package com.jiumai.base.sm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiumai.base.sm.dto.OperatorDTO;
import com.jiumai.base.sm.dto.OrganizationDTO;
import com.jiumai.base.sm.entity.SmOrganization;
import com.jiumai.base.sm.query.OperatorQuery;
import com.jiumai.base.sm.query.OrganizationQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织 Mapper 接口
 * </p>
 *
 * @author mysql gen
 * @since 2023-01-09
 */
public interface SmOrganizationMapper extends BaseMapper<SmOrganization> {

    /**
     * 分页查询组织
     * @param page
     * @param query
     * @return
     */
    IPage<OrganizationDTO> findOrganizationPaging(@Param("page") IPage<OrganizationDTO> page, @Param("query") OrganizationQuery query);

    /**
     * 操作员可访问组织
     *
     * @param opId
     * @return
     */
    List<OrganizationDTO> findAccessOrgsByOpId(@Param("opId") Long opId);

    /**
     * 操作员可访问组织
     *
     * @param opId
     * @return
     */
    List<OrganizationDTO> searchOrganizations(@Param("searchKey") String searchKey, @Param("opId") Long opId, @Param("relType") String relType);
}
