package com.tml.api.base.system.dto;

import com.tml.common.core.base.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询角色对象
 *
 * @author JacksonTu
 * @date 2019/11/6 11:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "查询角色对象")
public class RoleDTO extends CommonDTO {

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "创建者ID", hidden = true)
    private Long createUserId;


}
