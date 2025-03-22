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
 * 用户角色表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "用户角色表")
@TableName("t_sys_user_role")
public class SysUserRole implements Serializable {

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @TableField("user_id")
    private Long userId;
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @TableField("role_id")
    private Long roleId;

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
     * 获取：用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置：角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
