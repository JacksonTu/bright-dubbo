package com.tml.api.base.enterprise.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业职务配置表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
@Schema(description = "企业职务配置表")
@Data
@EqualsAndHashCode
public class EnterpriseJobVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    /**
     * 企业部门表ID
     */
    @Schema(description = "企业部门表ID")
    private Long departmentId;
    /**
     * 职务代码
     */
    @Schema(description = "职务代码")
    private String jobCode;
    /**
     * 职务名称
     */
    @Schema(description = "职务名称")
    private String jobName;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    private Date createTime;
    /**
     * 记录最后更新时间
     */
    @Schema(description = "记录最后更新时间")
    private Date updateTime;
    /**
     * 记录创建用户
     */
    @Schema(description = "记录创建用户")
    private String createUser;
    /**
     * 记录最后更新用户
     */
    @Schema(description = "记录最后更新用户")
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
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String departmentName;

    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    private String enterpriseId;

    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String enterpriseName;
}
