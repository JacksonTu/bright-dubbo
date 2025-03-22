package com.tml.common.core.base.dto;

import com.tml.common.core.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询参数
 * @author JacksonTu
 * @date 2019/11/6 9:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "查询参数对象")
public class CommonDTO implements Serializable {

    @Schema(description = "页码,默认为1", example = "1")
    @NotNull(message = "页码不能为空")
    private long page = CommonConstant.DEFAULT_PAGE;

    @Schema(description = "行数,默认为10", example = "10")
    @NotNull(message = "行数不能为空")
    private long limit = CommonConstant.DEFAULT_LIMIT;

    @Schema(description = "搜索字符串", example = "")
    private String key;

    public void setPage(long page) {
        if (page <= 0) {
            this.page = CommonConstant.DEFAULT_PAGE;
        } else {
            this.page = page;
        }
    }

    public void setLimit(long limit) {
        if (limit <= 0) {
            this.limit = CommonConstant.DEFAULT_LIMIT;
        } else if (this.limit >= CommonConstant.MAX_LIMIT) {
            this.limit = CommonConstant.MAX_LIMIT;
        } else {
            this.limit = limit;
        }
    }
}
