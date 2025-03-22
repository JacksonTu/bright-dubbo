package com.tml.api.base.system.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @date 2019/11/7 11:51
 */
@Schema(description = "查询文件参数对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class FileDTO extends CommonDTO {

    private String id;
    /**
     * 附件类型(哪个表的附件)
     */
    @Schema(description = "附件类型(哪个表的附件)")
    private String tableId;
    /**
     * 附件ID(哪个表的记录Id)
     */
    @Schema(description = "附件ID(哪个表的记录Id)")
    private String recordId;
    /**
     * 表的记录Id下的附件分组的组名
     */
    @Schema(description = "表的记录Id下的附件分组的组名")
    private String attachmentGroup;
    /**
     * 附件名称
     */
    @Schema(description = "附件名称")
    private String attachmentName;
    /**
     * 附件路径
     */
    @Schema(description = "附件路径")
    private String attachmentPath;
    /**
     * 附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)
     */
    @Schema(description = "附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)")
    private Integer attachmentType;

    /**
     * 存储类型（0：本地存储，1:fastdfs）
     */
    @Schema(description = "存储类型（0：本地存储，1:fastdfs）")
    private Integer saveType;
}
