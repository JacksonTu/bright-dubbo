package com.tml.common.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tml.common.core.utils.JacksonUtil;

import java.io.Serializable;
import java.util.List;

/**
 *  vue 树形控件对象
 * @author JacksonTu
 * @date 2019/8/8 11:39
 */
public class TreeVo implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * label
     */
    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TreeVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
