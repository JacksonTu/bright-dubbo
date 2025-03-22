package com.tml.api.notice.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @version 1.0
 * 查询企业参数对象
 * @date 2020/11/21 15:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询企业参数对象")
public class NoticeSendDTO extends CommonDTO {

    @Schema(description = "通知ID")
    private Long noticeId;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "阅读状态")
    private String readFlag;
    @Schema(description = "消息类型")
    private String msgCategory;
    @Schema(description = "业务类型")
    private String busType;
}
