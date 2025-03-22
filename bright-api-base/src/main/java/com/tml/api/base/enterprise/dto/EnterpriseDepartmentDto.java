package com.tml.api.base.enterprise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author JacksonTu
 * @date 2019/11/7 10:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询企业部门参数对象")
public class EnterpriseDepartmentDto implements Serializable {

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "企业ID")
    private Long enterpriseId;
    @Schema(description = "部门代码")
    private String departmentCode;
    @Schema(description = "部门名称")
    private String departmentName;
    @Schema(description = "企业名称")
    private String enterpriseName;
    @Schema(description = "用户ID", hidden = true)
    private Long userId;
}
