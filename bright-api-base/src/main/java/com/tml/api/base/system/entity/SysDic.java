package com.tml.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "数据字典表")
@TableName("t_sys_dic")
public class SysDic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父变量ID
     */
    @Schema(description = "父变量ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("parent_id")
    private Long parentId;
    /**
     * 变量代码
     */
    @Schema(description = "变量代码")
    @TableField("var_code")
    private String varCode;
    /**
     * 变量名称
     */
    @Schema(description = "变量名称")
    @TableField("var_name")
    private String varName;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    @TableField("create_time")
    private Date createTime;
    /**
     * 记录修改时间
     */
    @Schema(description = "记录修改时间")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 记录创建者（用户）
     */
    @Schema(description = "记录创建者（用户）")
    @TableField("create_user")
    private String createUser;
    /**
     * 记录最后修改者（用户）
     */
    @Schema(description = "记录最后修改者（用户）")
    @TableField("update_user")
    private String updateUser;
    /**
     * 数据是否同步(0:是,1:否)
     */
    @Schema(description = "数据是否同步(0:是,1:否)")
    @TableField("is_sync")
    private Integer isSync;

    /**
     * 父变量名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 获取：主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：父变量ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：父变量ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：变量代码
     */
    public String getVarCode() {
        return varCode;
    }

    /**
     * 设置：变量代码
     */
    public void setVarCode(String varCode) {
        this.varCode = varCode;
    }

    /**
     * 获取：变量名称
     */
    public String getVarName() {
        return varName;
    }

    /**
     * 设置：变量名称
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }

    /**
     * 获取：记录创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：记录创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：记录修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：记录修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：记录创建者（用户）
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置：记录创建者（用户）
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：记录最后修改者（用户）
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置：记录最后修改者（用户）
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取：数据是否同步(0:是,1:否)
     */
    public Integer getIsSync() {
        return isSync;
    }

    /**
     * 设置：数据是否同步(0:是,1:否)
     */
    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
