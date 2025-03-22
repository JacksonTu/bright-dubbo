package com.tml.common.core.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *  自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author JacksonTu
 * @date 2018/12/12 19:03
 */
@Schema(description = "登录信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUserVo implements Serializable {
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 登陆名
     */
    @Schema(description = "登陆名")
    private String loginName;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String name;
//    /**
//     * 密码
//     */
//    @Schema(description="密码", hidden = true)
//    private String password;
//    /**
//     * 盐值
//     */
//    @Schema(description="盐值", hidden = true)
//    private String salt;
    /**
     * 性别(0:男，1：女)
     */
    @Schema(description = "性别(0:男，1：女)")
    private Integer sex;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 用户类别（0：超级管理员，1：企业用户，2：监管用户）
     */
    @Schema(description = "用户类别（0：超级管理员，1：企业用户，2：监管用户）")
    private Integer userType;
    /**
     * 用户状态(0：正常，1：不正常)
     */
    @Schema(description = "用户状态(0-正常，1-不正常)")
    private Integer status;
    /**
     * 过期字段（0-不过期，1-过期）
     */
    @Schema(description = "过期字段（0-不过期，1-过期）")
    private Integer expired;
    /**
     * 所属企业
     */
    @Schema(description = "所属企业ID")
    private Long enterpriseId;
    /**
     * 所属部门
     */
    @Schema(description = "所属部门ID")
    private Long departmentId;
    /**
     * 用户职务
     */
    @Schema(description = "用户职务ID")
    private Long jobId;
    /**
     * 是否领导（0-是，1-否）
     */
    @Schema(description = "是否领导（0-是，1-否）")
    private Integer isLeader;
    /**
     * 记录创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
     * 用户管理的企业ID集合
     */
    @Schema(description = "用户管理的企业ID集合")
    private List<Long> enterpriseIdList;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

}