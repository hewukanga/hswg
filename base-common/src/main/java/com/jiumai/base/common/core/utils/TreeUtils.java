package com.jiumai.base.common.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：cz
 * @date ：Created in 2019/16 23:56
 */
public class TreeUtils {

    /**
     * 将List转化为Tree
     *
     * @param list        需要转化为树的List
     * @param key         树的唯一标识字段（如：id）
     * @param parentKey   父节点的唯一标识字段（如：parentId）
     * @param childrenKey 指定的子集的属性字段，必须为List (如：children)
     * @param <T>
     * @return
     */
    public static <T> List<T> parseListToTree(List<T> list, String key, String parentKey, String childrenKey) {
        if (list == null) {
            return null;
        }
        List<T> rootTrees = new ArrayList<>();

        // 拿到子节点
        Map<Object, List<T>> childrenMap = list.stream()
                .filter(item -> !ReflectionUtils.getFieldValue(item, parentKey).equals(ReflectionUtils.getFieldValue(item, key)))
                .collect(Collectors.groupingBy(t -> ReflectionUtils.getFieldValue(t, parentKey)));
        // 组装树
        list.forEach(item -> {
            Object id = ReflectionUtils.getFieldValue(item, key);
            Object parentId = ReflectionUtils.getFieldValue(item, parentKey);
            if (id.equals(parentId)) {
                rootTrees.add(item);
            }
            List<T> childrenList = childrenMap.get(id);

            ReflectionUtils.setFieldValue(item, childrenKey, childrenList);
        });

        return rootTrees;
    }
}
