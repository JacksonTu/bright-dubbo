package com.tml.common.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 *  vue select选择器对象
 * @author JacksonTu
 * @date 2018/6/20 10:38
 */
@Schema(description = "vue select选择器对象")
public class SelectVo implements Serializable {
    /**
     * value
     */
    @Schema(description = "key")
    private String value;

    /**
     * label
     */
    @Schema(description = "value")
    private String label;

    @Schema(description = "对象List")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SelectVo> options;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SelectVo> getOptions() {
        return options;
    }

    public void setOptions(List<SelectVo> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
