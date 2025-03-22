package com.tml.api.base.system.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询用户对象
 *
 * @author JacksonTu
 * @date 2019/11/6 10:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "查询用户对象")
public class UserDTO extends CommonDTO {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "登录名/用户名")
    private String name;

    @Schema(description = "创建者ID", hidden = true)
    private Long createUserId;

    @Schema(description = "企业ID", hidden = true)
    private String enterpriseId;

    @Schema(description = "部门ID", hidden = true)
    private String departmentId;

    @Schema(description = "开始时间", example = "2019-10-01")
    private String startTime;

    @Schema(description = "结束时间", example = "2019-10-01")
    private String endTime;
}
