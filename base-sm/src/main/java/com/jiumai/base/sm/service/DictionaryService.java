package com.jiumai.base.sm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiumai.base.sm.entity.Dictionary;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author wangjian
 * @since 2020-08-04
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 根据字典分类获取字典数据
     * @param dicClassify
     * @return
     */
    List<Dictionary> findDicByClassify(Integer dicClassify);

    /**
     * 保存或更新字典
     * @param request
     * @param dictionary
     * @return
     */
    boolean saveDic(HttpServletRequest request, Dictionary dictionary);

    /**
     * 根据编码类型获取字典数据
     * @param dicTypeCode
     * @return
     */
    List<Dictionary> findDicByTypeCode(String dicTypeCode);

    /**
     * 获取字典名称
     * @param dicTypeCode
     * @param dicCode
     * @return
     */
    Dictionary getDic(String dicTypeCode, String dicCode);

    /**
     * 根据字典名称获取字典信息
     * @param dicName
     * @return
     */
    Dictionary getDicByName(String dicName);

    /**
     * 判断字典是否重复（同一种字典类型的字典名称和编码都不能重复）
     * @param dictionary
     * @return
     */
    List<Dictionary> isRepeatDic(Dictionary dictionary);
}
