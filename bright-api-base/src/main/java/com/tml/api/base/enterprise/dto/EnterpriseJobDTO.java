package com.tml.api.base.enterprise.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @date 2019/11/7 10:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询消息类型参数对象")
public class EnterpriseJobDTO extends CommonDTO {

    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "企业ID")
    private Long enterpriseId;
    @Schema(description = "职位代码")
    private String jobCode;
    @Schema(description = "职位名称")
    private String jobName;
    @Schema(description = "部门ID")
    private Long departmentId;
    @Schema(description = "用户ID", hidden = true)
    private Long userId;
}
