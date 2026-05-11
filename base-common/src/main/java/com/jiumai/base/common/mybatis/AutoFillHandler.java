package com.jiumai.base.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jiumai.base.common.core.session.OperatorUtil;
import com.jiumai.base.common.core.utils.CommonFuntions;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 先判断是否存在该字段
        boolean createId = metaObject.hasSetter("createId");
        boolean createName = metaObject.hasSetter("createName");
        boolean createDate = metaObject.hasSetter("createDate");
        boolean delFlag = metaObject.hasSetter("delFlag");
        boolean version = metaObject.hasSetter("version");

        insert(metaObject, createDate, delFlag, version);
        // 新增时，修改时间也保存一下
        this.updateFill(metaObject);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Object opIdObj = request.getAttribute(OperatorUtil.REQUEST_OPID);
        if (CommonFuntions.isNotEmptyObject(opIdObj)) {
            Long opId = (Long) opIdObj;
            if (CommonFuntions.isNotEmptyObject(opId)) {
                if (createId) {
                    strictInsertFill(metaObject, "createId", Long.class, opId);
                }
            }
        }
        Object opNameObj = request.getAttribute(OperatorUtil.REQUEST_OP_NAME);
        if (CommonFuntions.isNotEmptyObject(opNameObj)) {
            String opName = (String) opNameObj;
            if (CommonFuntions.isNotEmptyObject(opName)) {
                if (createName) {
                    strictInsertFill(metaObject, "createName", String.class, opName);
                }
            }
        }
    }

    private void insert(MetaObject metaObject, boolean createDate, boolean delFlag, boolean version) {
        if (delFlag) {
            strictInsertFill(metaObject, "delFlag", Integer.class, 1);
        }
        if (version) {
            strictInsertFill(metaObject, "version", Integer.class, 0);
        }
        if (createDate) {
            strictInsertFill(metaObject, "createDate", LocalDateTime.class, LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        // 先判断是否存在该字段
        boolean modifyDate = metaObject.hasSetter("modifyDate");
        boolean modifyId = metaObject.hasSetter("modifyId");
        boolean modifyName = metaObject.hasSetter("modifyName");

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (modifyDate) {
            metaObject.setValue("modifyDate", null);
            strictUpdateFill(metaObject, "modifyDate", LocalDateTime.class, LocalDateTime.now());
        }
        if (requestAttributes == null) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Object opIdObj = request.getAttribute(OperatorUtil.REQUEST_OPID);
        if (CommonFuntions.isNotEmptyObject(opIdObj)) {
            Long opId = (Long) opIdObj;
            if (modifyId) {
                metaObject.setValue("modifyId", null);
                strictUpdateFill(metaObject, "modifyId", Long.class, opId);
            }
        }
        Object opNameObj = request.getAttribute(OperatorUtil.REQUEST_OP_NAME);
        if (CommonFuntions.isNotEmptyObject(opNameObj)) {
            String opName = (String) opNameObj;
            if (modifyName) {
                metaObject.setValue("modifyName", null);
                strictUpdateFill(metaObject, "modifyName", String.class, opName);
            }
        }
    }
}