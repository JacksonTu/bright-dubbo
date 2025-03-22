package com.tml.api.base.enterprise.vo;


import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息表
 *
 * @author JacksonTu
 * @date 2018-12-11 13:49:00
 */
@Schema(description = "企业信息")
@Data
@EqualsAndHashCode
public class EnterpriseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;
    /**
     * 工商注册码
     */
    @Schema(description = "工商注册码")
    private String businessLicenseNumber;
    /**
     * 企业编号
     */
    @Schema(description = "企业编号")
    private String enterpriseCode;
    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String enterpriseName;
    /**
     * 所属行业
     */
    @Schema(description = "所属行业")
    private String industryCode;
    /**
     * 所属区域
     */
    @Schema(description = "所属区域")
    private String areaCode;
    /**
     * 企业类型(国企:0，民企:1，私企:2，外企:3)
     */
    @Schema(description = "企业类型(国企:0，民企:1，私企:2，外企:3)")
    private Integer enterpriseType;
    /**
     * 企业联系电话
     */
    @Schema(description = "企业联系电话")
    private String telephone;
    /**
     * 企业邮箱
     */
    @Schema(description = "企业邮箱")
    private String email;
    /**
     * 邮政编码
     */
    @Schema(description = "邮政编码")
    private String zipCode;
    /**
     * 法人
     */
    @Schema(description = "法人")
    private String legalPerson;
    /**
     * 企业负责人姓名
     */
    @Schema(description = "企业负责人姓名")
    private String mainPerson;
    /**
     * 企业负责人移动电话号码
     */
    @Schema(description = "企业负责人电话")
    private String mainPersonMobile;
    /**
     * x坐标
     */
    @Schema(description = "x坐标")
    private String mapX;
    /**
     * y坐标
     */
    @Schema(description = "y坐标")
    private String mapY;
    /**
     * z坐标
     */
    @Schema(description = "z坐标")
    private String mapZ;
    /**
     * 地址
     */
    @Schema(description = "地址")
    private String address;
    /**
     * 企业状态（0-正常，1-禁用）
     */
    @Schema(description = "企业状态（0-正常，1-禁用）")
    private Integer status;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    private Date createTime;
    /**
     * 记录最后修改时间
     */
    @Schema(description = "记录最后修改时间")
    private Date updateTime;
    /**
     * 记录创建者(用户)
     */
    @Schema(description = "记录创建者(用户)")
    private String createUser;
    /**
     * 记录最后修改者(用户)
     */
    @Schema(description = "记录最后修改者(用户)")
    private String updateUser;

    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String areaName;
    /**
     * 行业名称
     */
    @Schema(description = "行业名称")
    private String industryName;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
