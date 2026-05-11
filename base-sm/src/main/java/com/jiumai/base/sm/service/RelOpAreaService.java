package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.entity.SmOrganization;
import com.jiumai.base.sm.entity.SmRelOpArea;

import java.util.List;

public interface RelOpAreaService extends IService<SmRelOpArea> {

    /**
     * 批量修改过期时间
     * @param smRelOpAreas
     */
    void batchUpdateExpireDate(List<SmRelOpArea> smRelOpAreas);
    /**
     * 根据用户ID获取用户关联区域Id
     *
     * @param opId
     * @return
     * @throws Exception
     */
    List<Long> findRelOpAreaIdsByOpId(long opId) throws Exception;

    /**
     * 保存操作员与与区域关联数据
     */
    Long saveRelOpArea(SmRelOpArea relOpArea);

    /**
     * 删除操作员和区域关联关系
     */
    void deleteRelOpArea(Long opId, Long areaoleId);

}
