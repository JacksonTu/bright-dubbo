package com.tml.api.base.enterprise.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业部门表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:36:02
 */
@Schema(description = "企业部门信息")
@Data
@EqualsAndHashCode
public class EnterpriseDepartmentVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "主键ID")
    private Long id;
    /**
     * 企业部门父ID
     */
    @Schema(description = "企业部门父ID")
    private Long parentId;
    /**
     * 企业ID(对应企业主表ID)
     */
    @Schema(description = "企业ID(对应企业主表ID)")
    private Long enterpriseId;
    /**
     * 部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)
     */
    @Schema(description = "部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)")
    private String departmentCode;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String departmentName;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    private Date createTime;
    /**
     * 记录最后修改时间
     */
    @Schema(description = "记录最后修改时间")
    private Date updateTime;
    /**
     * 记录创建者(用户)
     */
    @Schema(description = "记录创建者(用户)")
    private String createUser;
    /**
     * 记录最后修改者(用户)
     */
    @Schema(description = "记录最后修改者(用户)")
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
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String enterpriseName;
}
