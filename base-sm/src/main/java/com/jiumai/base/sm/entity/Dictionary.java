package com.jiumai.base.sm.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author wangjian
 * @since 2020-08-04
 */
@TableName("sm_dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 字典类型编码
     */
    @ApiModelProperty(value = "字典类型编码")
    private String dicTypeCode;

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dicCode;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dicName;

    /**
     * 父类编码
     */
    @ApiModelProperty(value = "父类编码")
    private String parentCode;

    /**
     * 字典分类(1:系统字典  2：用户字典)
     */
    @ApiModelProperty(value = "字典分类(1:系统字典  2：用户字典)",example = "2")
    private Integer dicClassify;

    /**
     * 类型内排序
     */
    @ApiModelProperty(value = "类型内排序")
    private Integer insort;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Long createId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 修改人id
     */
    @ApiModelProperty(value = "修改人id")
    private Long modifyId;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDate;

    /**
     * 删除标志(1：未删除  -1：删除)
     */
    @ApiModelProperty(value = "删除标志(1：未删除  -1：删除)",example = "1")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    /**
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号",example = "0")
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDicTypeCode() {
        return dicTypeCode;
    }

    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }
    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }
    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public Integer getDicClassify() {
        return dicClassify;
    }

    public void setDicClassify(Integer dicClassify) {
        this.dicClassify = dicClassify;
    }
    public Integer getInsort() {
        return insort;
    }

    public void setInsort(Integer insort) {
        this.insort = insort;
    }
    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
        "id=" + id +
        ", dicTypeCode=" + dicTypeCode +
        ", dicCode=" + dicCode +
        ", dicName=" + dicName +
        ", parentCode=" + parentCode +
        ", dicClassify=" + dicClassify +
        ", insort=" + insort +
        ", createId=" + createId +
        ", createDate=" + createDate +
        ", modifyId=" + modifyId +
        ", modifyDate=" + modifyDate +
        ", delFlag=" + delFlag +
        ", version=" + version +
        "}";
    }
}
