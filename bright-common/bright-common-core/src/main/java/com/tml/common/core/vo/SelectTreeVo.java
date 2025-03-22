package com.tml.common.core.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

/**
 *  vue 树形选择器对象
 * @author JacksonTu
 * @date 2018/12/13 18:37
 */
@Schema(description = "vue 树形选择器对象")
public class SelectTreeVo implements Serializable {
    /**
     * 节点ID
     */
    @Schema(description = "节点ID")
    private String id;
    /**
     * 父节点ID
     */
    @Schema(description = "父节点ID")
    private String parentId;
    /**
     * 节点名称
     */
    @Schema(description = "节点名称")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private String rank;

    /**
     * 子节点
     */
    @Schema(description = "children")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SelectTreeVo> children;

    public static SelectTreeVo createParent() {
        SelectTreeVo selectTreeVo = new SelectTreeVo();
        selectTreeVo.setId("0");
        selectTreeVo.setName("顶级");
        selectTreeVo.setParentId("0");
        selectTreeVo.setRank("1");
        return selectTreeVo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<SelectTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<SelectTreeVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public void add(SelectTreeVo node) {
        children.add(node);
    }
}
