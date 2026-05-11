package com.jiumai.base.sm.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonConstant;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.sm.entity.Dictionary;
import com.jiumai.base.sm.mapper.DictionaryMapper;
import com.jiumai.base.sm.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2020-08-04
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 字典系统缓存
     */
    private static Map<String, List<Dictionary>> dictMap = null;

    @Override
    public List<Dictionary> findDicByClassify(Integer dicClassify) {
        if (CommonFuntions.isEmptyObject(dicClassify)) {
            return null;
        }
        if (!redisTemplate.hasKey(CommonConstant.DICTIONARY_KEY)) {
            refreshRedisDic();
        }
        Object obj = redisTemplate.opsForHash().get(CommonConstant.DICTIONARY_KEY, dicClassify);
        List<Dictionary> dictionaryList = null;
        if (obj != null) {
            dictionaryList = JSON.parseArray(obj.toString(), Dictionary.class);
        }
        if (dictionaryList != null && !dictionaryList.isEmpty()) {
            return dictionaryList;
        }
        return dictionaryMapper.findDicByClassify(dicClassify);
    }

    @Override
    public boolean saveDic(HttpServletRequest request, Dictionary dictionary) {
        LoginOperator oper = OperatorUtil.getOperator(request);
        if (CommonFuntions.isNotEmptyObject(dictionary.getId())) {
            dictionary.setModifyId(oper.getOpId());
        } else {
            dictionary.setCreateId(oper.getOpId());
        }
        boolean b = saveOrUpdate(dictionary);
        if (b) {
            refreshRedisDic();
        }
        return b;
    }

    @Override
    public List<Dictionary> findDicByTypeCode(String dicTypeCode) {
        if (CommonFuntions.isEmptyObject(dicTypeCode)) {
            return null;
        }
        if (!redisTemplate.hasKey(CommonConstant.DICTIONARY_KEY)) {
            refreshRedisDic();
        }
        Object obj = redisTemplate.opsForHash().get(CommonConstant.DICTIONARY_KEY, dicTypeCode);
        List<Dictionary> dictionaryList = null;
        if (obj != null) {
            dictionaryList = JSON.parseArray(obj.toString(), Dictionary.class);
        }
        if (dictionaryList != null && !dictionaryList.isEmpty()) {
            dictionaryList = dictionaryList.stream().sorted(Comparator.comparing(e -> Integer.parseInt(e.getDicCode()))).collect(Collectors.toList());
            return dictionaryList.stream().filter(dictionary -> CommonFuntions.isNotEmptyObject(dictionary.getParentCode())).collect(Collectors.toList());
        }
        return dictionaryMapper.findDicByTypeCode(dicTypeCode);
    }

    @Override
    public Dictionary getDic(String dicTypeCode, String dicCode) {
        if (CommonFuntions.isEmptyObject(dicTypeCode) || CommonFuntions.isEmptyObject(dicCode) || "null".equals(dicCode)) {
            return null;
        }
        // 更新系统缓存
        if (dictMap == null || dictMap.get(dicTypeCode) == null) {
            refreshRedisDic();
        }
        List<Dictionary> dictionaryList = dictMap.get(dicTypeCode);
        // 开始匹配
        for (Dictionary dictionary : dictionaryList) {
            if (dicCode.equals(dictionary.getDicCode())) {
                return dictionary;
            }
        }
        return dictionaryMapper.getDic(dicTypeCode, dicCode);
    }

    @Override
    public Dictionary getDicByName(String dicName) {
        return dictionaryMapper.getDicByName(dicName);
    }

    @Override
    public List<Dictionary> isRepeatDic(Dictionary dictionary) {
        return dictionaryMapper.isRepeatDic(dictionary);
    }

    private void refreshRedisDic() {
        List<Dictionary> dictionaryList;
        try {
            dictionaryList = dictionaryMapper.selectList(new LambdaQueryWrapper<Dictionary>().orderByAsc(Dictionary::getDicCode));
            if (CommonFuntions.isNotEmptyObject(dictionaryList)) {
                // 将字典按 字典类型编码 分类，按 字典分类 分类，按 id 分类，存入redis
                Map<String, List<Dictionary>> dicByType = dictionaryList.stream().collect(Collectors.groupingBy(Dictionary::getDicTypeCode));
                Map<String, List<Dictionary>> dicByClassify = dictionaryList.stream().collect(Collectors.groupingBy(dictionary -> String.valueOf(dictionary.getDicClassify())));
//                Map<String, Dictionary> dicById = dictionaryList.stream().collect(Collectors.toMap(Dictionary::getId, dictionary -> dictionary));

                redisTemplate.delete(CommonConstant.DICTIONARY_KEY);
                dictMap = new HashMap<>(512);
                for (Map.Entry<String, List<Dictionary>> item : dicByType.entrySet()) {
                    redisTemplate.opsForHash().put(CommonConstant.DICTIONARY_KEY, item.getKey(), JSON.toJSONString(item.getValue()));
                    dictMap.put(item.getKey(), item.getValue());
                }
                for (Map.Entry<String, List<Dictionary>> item : dicByClassify.entrySet()) {
                    redisTemplate.opsForHash().put(CommonConstant.DICTIONARY_KEY, item.getKey(), JSON.toJSONString(item.getValue()));
                    dictMap.put(item.getKey(), item.getValue());
                }
//                redisTemplate.opsForHash().putAll(CommonConstant.DICTIONARY_KEY, dicByType);
//                redisTemplate.opsForHash().putAll(CommonConstant.DICTIONARY_KEY, dicByClassify);
//                redisTemplate.opsForHash().putAll(CommonConstant.DICTIONARY_KEY, dicById);
                redisTemplate.expire(CommonConstant.DICTIONARY_KEY, 1, TimeUnit.DAYS);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
