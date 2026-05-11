package com.jiumai.base.sm.query;

import com.jiumai.base.common.core.query.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "操作日志查询参数")
public class OpLogQuery extends PageParam {


    /** 操作模块 */
    private String title;

    /** 操作人员 */
    private String operName;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    private Integer businessType;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    private Integer operatorType;

    /** 部门名称 */
    private String deptName;

    /** 操作状态（0正常 1异常） */
    private Integer status;

    /** 操作时间（开始） */
    private Date startTime;

    /** 操作时间（结束） */
    private Date endTime;
}
