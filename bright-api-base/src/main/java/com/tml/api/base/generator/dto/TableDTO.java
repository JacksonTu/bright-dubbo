package com.tml.api.base.generator.dto;

import com.tml.common.core.base.dto.CommonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JacksonTu
 * @version 1.0
 * @date 2020/11/22 11:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TableDTO extends CommonDTO {

    private String tableName;
}
