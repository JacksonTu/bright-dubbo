package com.tml.api.base.job.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询定时任务日志参数对象
 *
 * @author JacksonTu
 * @date 2019/11/6 18:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询定时任务日志参数对象")
public class JobLogDTO extends CommonDTO {

    @Schema(description = "定时任务ID")
    private String jobId;
}
