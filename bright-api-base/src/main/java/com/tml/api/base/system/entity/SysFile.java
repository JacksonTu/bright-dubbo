package com.tml.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "附件表")
@TableName("t_sys_file")
public class SysFile implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 附件类型(哪个表的附件)
     */
    @Schema(description = "附件类型(哪个表的附件)")
    @TableField("table_id")
    private String tableId;
    /**
     * 附件ID(哪个表的记录Id)
     */
    @Schema(description = "附件ID(哪个表的记录Id)")
    @TableField("record_id")
    private String recordId;
    /**
     * 表的记录Id下的附件分组的组名
     */
    @Schema(description = "表的记录Id下的附件分组的组名")
    @TableField("attachment_group")
    private String attachmentGroup;
    /**
     * 附件名称
     */
    @Schema(description = "附件名称")
    @TableField("attachment_name")
    private String attachmentName;
    /**
     * 附件路径
     */
    @Schema(description = "附件路径")
    @TableField("attachment_path")
    private String attachmentPath;
    /**
     * 附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)
     */
    @Schema(description = "附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)")
    @TableField("attachment_type")
    private Integer attachmentType;
    /**
     * 存储类型（0：本地存储，1:fastdfs）
     */
    @Schema(description = "存储类型（0：本地存储，1:fastdfs）")
    @TableField("save_type")
    private Integer saveType;
    /**
     * 存储类型（0：本地存储，1:fastdfs）
     */
    @Schema(description = "企业ID")
    @TableField("enterprise_id")
    private Long enterpriseId;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    @TableField("create_time")
    private Date createTime;
    /**
     * 记录最后修改时间
     */
    @Schema(description = "记录最后修改时间")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 记录创建者(用户)
     */
    @Schema(description = "记录创建者(用户)")
    @TableField("create_user")
    private String createUser;
    /**
     * 记录最后修改者(用户)
     */
    @Schema(description = "记录最后修改者(用户)")
    @TableField("update_user")
    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAttachmentGroup() {
        return attachmentGroup;
    }

    public void setAttachmentGroup(String attachmentGroup) {
        this.attachmentGroup = attachmentGroup;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Integer getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    public Integer getSaveType() {
        return saveType;
    }

    public void setSaveType(Integer saveType) {
        this.saveType = saveType;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
