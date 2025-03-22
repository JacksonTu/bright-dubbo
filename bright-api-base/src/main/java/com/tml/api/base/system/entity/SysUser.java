package com.tml.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "用户表")
@TableName("t_sys_user")
public class SysUser implements Serializable {

    /**
     * 主键id
     */
    @NotNull(message = "用户ID不能为空")
    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 登陆名
     */
    @NotBlank(message = "登陆名不能为空")
    @Length(max = 64, message = "登陆名不能超过64个字符")
    @Schema(description = "登陆名")
    @TableField("login_name")
    private String loginName;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 64, message = "用户名不能超过64个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户名限制：最多64字符，包含文字、字母和数字")
    @Schema(description = "用户名")
    private String name;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 密码加密盐
     */
    @Schema(description = "密码加密盐")
    private String salt;
    /**
     * 性别(0:男，1：女)
     */
    @Schema(description = "性别(0:男，1：女)")
    private Integer sex;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Schema(description = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不对")
    @Schema(description = "邮箱")
    private String email;
    /**
     * 用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    @Schema(description = "用户类别（0：超级管理员，1：企业用户，2：监管用户）")
    @TableField("user_type")
    private Integer userType;
    /**
     * 用户状态(0：正常，1：不正常)
     */
    @Schema(description = "用户状态(0：正常，1：不正常)")
    private Integer status;
    /**
     * 过期字段（0-不过期，1-过期）
     */
    @Schema(description = "过期字段（0-不过期，1-过期）")
    private Integer expired;
    /**
     * 所属企业
     */
    @Schema(description = "所属企业")
    @TableField("enterprise_id")
    private Long enterpriseId;
    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    @TableField("department_id")
    private Long departmentId;
    /**
     * 用户职务
     */
    @Schema(description = "用户职务")
    @TableField("job_id")
    private Long jobId;
    /**
     * 是否领导（0-是，1-否）
     */
    @Schema(description = "是否领导（0-是，1-否）")
    @TableField("is_leader")
    private Integer isLeader;
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
     * 记录创建用户ID
     */
    @Schema(description = "记录创建用户ID")
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 角色Id列表
     */
    @TableField(exist = false)
    private List<Long> roleIdList;

    /**
     * 监管用户拥有的企业Id列表
     */
    @TableField(exist = false)
    private List<Long> enterpriseIdList;

    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：登陆名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置：登陆名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取：用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码加密盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置：密码加密盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取：性别(0:男，1：女)
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置：性别(0:男，1：女)
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取：手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置：用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取：用户状态(0：正常，1：不正常)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：用户状态(0：正常，1：不正常)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：过期字段（0-不过期，1-过期）
     */
    public Integer getExpired() {
        return expired;
    }

    /**
     * 设置：过期字段（0-不过期，1-过期）
     */
    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    /**
     * 获取：所属企业
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置：所属企业
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取：所属部门
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置：所属部门
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取：用户职务
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 设置：用户职务
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取：是否领导（0-是，1-否）
     */
    public Integer getIsLeader() {
        return isLeader;
    }

    /**
     * 设置：是否领导（0-是，1-否）
     */
    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<Long> getEnterpriseIdList() {
        return enterpriseIdList;
    }

    public void setEnterpriseIdList(List<Long> enterpriseIdList) {
        this.enterpriseIdList = enterpriseIdList;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
