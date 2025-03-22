package com.tml.api.base.enterprise.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业部门表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
@Schema(description = "企业部门表")
@TableName("t_enterprise_department")
public class EnterpriseDepartment implements Serializable {

    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 企业部门父ID
     */
    @Schema(description = "企业部门父ID")
    @TableField("parent_id")
    private Long parentId;
    /**
     * 企业ID(对应企业主表ID)
     */
    @Schema(description = "企业ID(对应企业主表ID)")
    @TableField("enterprise_id")
    private Long enterpriseId;
    /**
     * 部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)
     */
    @Schema(description = "部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)")
    @TableField("department_code")
    private String departmentCode;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @TableField("department_name")
    private String departmentName;
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
    /**
     * 预留1
     */
    @Schema(description = "预留1")
    private String parameter1;
    /**
     * 预留2
     */
    @Schema(description = "预留2")
    private String parameter2;

    @TableField(exist = false)
    private Enterprise enterprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：企业部门父ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：企业部门父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：企业ID(对应企业主表ID)
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置：企业ID(对应企业主表ID)
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取：部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 设置：部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * 获取：部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置：部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
     * 获取：记录最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：记录最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：记录创建者(用户)
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置：记录创建者(用户)
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：记录最后修改者(用户)
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置：记录最后修改者(用户)
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取：预留1
     */
    public String getParameter1() {
        return parameter1;
    }

    /**
     * 设置：预留1
     */
    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    /**
     * 获取：预留2
     */
    public String getParameter2() {
        return parameter2;
    }

    /**
     * 设置：预留2
     */
    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
