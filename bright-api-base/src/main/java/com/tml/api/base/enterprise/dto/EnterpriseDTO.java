package com.tml.api.base.enterprise.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @date 2019/11/7 10:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询企业参数对象")
public class EnterpriseDTO extends CommonDTO {
    @Schema(description = "企业ID")
    private Long enterpriseId;
    @Schema(description = "企业名称")
    private String enterpriseName;
    @Schema(description = "企业代码")
    private String enterpriseCode;
    @Schema(description = "企业类型")
    private String enterpriseType;
    @Schema(description = "区域码")
    private String areaCode;
    @Schema(description = "行业码")
    private String industryCode;
    @Schema(description = "用户ID", hidden = true)
    private Long userId;
}
