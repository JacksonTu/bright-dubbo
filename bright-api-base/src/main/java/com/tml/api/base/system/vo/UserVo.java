package com.tml.api.base.system.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * ：UserVo
 * @author：JacksonTu
 * @date 2018年5月6日 上午9:55:46
 */
@Schema(description = "用户VO")
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 登陆名
     */
    @Schema(description = "登陆名")
    private String loginName;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String name;
    /**
     * 性别(0:男，1：女)
     */
    @Schema(description = "性别(0:男，1：女)")
    private Integer sex;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    @Schema(description = "用户类别（0：超级管理员，1：企业用户，2：监管用户）")
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
    private Long enterpriseId;
    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    private Long departmentId;
    /**
     * 用户职务
     */
    @Schema(description = "用户职务")
    private Long jobId;
    /**
     * 是否领导（0-是，1-否）
     */
    @Schema(description = "是否领导（0-是，1-否）")
    private Integer isLeader;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private Date updateTime;

    /**
     * 创建用户ID
     */
    @Schema(description = "创建用户ID")
    private Long createUserId;

    @Schema(description = "所属企业")
    private String enterpriseName;

    @Schema(description = "所属部门")
    private String departmentName;

    @Schema(description = "所属职位")
    private String jobName;

    @Schema(description = "用户拥有的色色")
    private String rolesList;
}