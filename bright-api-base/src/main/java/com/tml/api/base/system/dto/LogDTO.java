package com.tml.api.base.system.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询日志对象
 *
 * @author JacksonTu
 * @date 2019/11/6 15:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "查询日志对象")
public class LogDTO extends CommonDTO {

    @Schema(description = "登录名", example = "admin")
    private String loginName;

    @Schema(description = "日志类型（0:操作日志，1：登录日志）", example = "0")
    private Integer logType;

    @Schema(description = "开始时间", example = "2019-10-01")
    private String startTime;

    @Schema(description = "结束时间", example = "2019-10-01")
    private String endTime;

}
