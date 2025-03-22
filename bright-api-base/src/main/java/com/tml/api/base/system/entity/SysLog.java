package com.tml.api.base.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "系统日志表")
@TableName("t_sys_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 登陆名
     */
    @Schema(description = "登陆名")
    @TableField("login_name")
    private String loginName;
    /**
     * 日志类型（0:操作日志，1：登录日志）
     */
    @Schema(description = "日志类型（0:操作日志，1：登录日志）")
    @TableField("log_type")
    private Integer logType;

    /**
     * 日志内容
     */
    @Schema(description = "日志内容")
    @TableField("log_content")
    private String logContent;

    /**
     * 操作类型（添加;2:修改;3:删除）
     */
    @Schema(description = "操作类型（添加;2:修改;3:删除）")
    @TableField("operate_type")
    private Integer operateType;

    /**
     * 类名
     */
    @Schema(description = "类名")
    @TableField("class_name")
    private String className;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String method;
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;
    /**
     * 执行时长
     */
    @Schema(description = "执行时长")
    private Long time;
    /**
     * 客户端ip
     */
    @Schema(description = "客户端ip")
    @TableField("client_ip")
    private String clientIp;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

}
