package com.tml.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 监管用户与企业关联表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "监管用户与企业关联表")
@TableName("t_sys_user_enterprise")
public class SysUserEnterprise implements Serializable {
    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @TableField("user_id")
    private Long userId;
    /**
     * 企业id列表(;分割)
     */
    @Schema(description = "企业id列表(;分割)")
    @TableField("enterprise_id")
    private Long enterpriseId;

    /**
     * 获取：主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：角色id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：角色id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：企业id列表(;分割)
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置：企业id列表(;分割)
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
