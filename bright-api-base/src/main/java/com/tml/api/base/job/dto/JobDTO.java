package com.tml.api.base.job.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @date 2019/11/6 21:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询定时任务日志参数对象")
public class JobDTO extends CommonDTO {

    @Schema(description = "定时任务ID")
    private String jobId;

    @Schema(description = "bean名称")
    private String beanName;
}
