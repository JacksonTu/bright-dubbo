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
 * 企业职务配置表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
@Schema(description = "企业职务配置表")
@TableName("t_enterprise_job")
public class EnterpriseJob implements Serializable {

    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 企业部门表ID
     */
    @Schema(description = "企业部门表ID")
    @TableField("department_id")
    private Long departmentId;
    /**
     * 职务代码
     */
    @Schema(description = "职务代码")
    @TableField("job_code")
    private String jobCode;
    /**
     * 职务名称
     */
    @Schema(description = "职务名称")
    @TableField("job_name")
    private String jobName;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    @TableField("create_time")
    private Date createTime;
    /**
     * 记录最后更新时间
     */
    @Schema(description = "记录最后更新时间")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 记录创建用户
     */
    @Schema(description = "记录创建用户")
    @TableField("create_user")
    private String createUser;
    /**
     * 记录最后更新用户
     */
    @Schema(description = "记录最后更新用户")
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

    @Schema(description = "所属部门")
    @TableField(exist = false)
    private EnterpriseDepartment enterpriseDepartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：企业部门表ID
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置：企业部门表ID
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取：职务代码
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     * 设置：职务代码
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * 获取：职务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置：职务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
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
     * 获取：记录最后更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：记录最后更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：记录创建用户
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置：记录创建用户
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：记录最后更新用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置：记录最后更新用户
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

    public EnterpriseDepartment getEnterpriseDepartment() {
        return enterpriseDepartment;
    }

    public void setEnterpriseDepartment(EnterpriseDepartment enterpriseDepartment) {
        this.enterpriseDepartment = enterpriseDepartment;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
