package com.tml.api.base.enterprise.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tml.common.core.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息表
 *
 * @author JacksonTu
 * @date 2018-12-11 13:49:00
 */
@Schema(description = "企业信息表")
@TableName("t_enterprise")
public class Enterprise implements Serializable {
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 工商注册码
     */
    @Schema(description = "工商注册码")
    @TableField("business_license_number")
    private String businessLicenseNumber;
    /**
     * 企业编号
     */
    @Schema(description = "企业编号")
    @TableField("enterprise_code")
    private String enterpriseCode;
    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    @TableField("enterprise_name")
    private String enterpriseName;
    /**
     * 所属行业
     */
    @Schema(description = "所属行业")
    @TableField("industry_code")
    private String industryCode;
    /**
     * 所属区域
     */
    @Schema(description = "所属区域")
    @TableField("area_code")
    private String areaCode;
    /**
     * 企业类型(国企:0，民企:1，私企:2，外企:3)
     */
    @Schema(description = "企业类型(国企:0，民企:1，私企:2，外企:3)")
    @TableField("enterprise_type")
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
    @TableField("zip_code")
    private String zipCode;
    /**
     * 法人
     */
    @Schema(description = "法人")
    @TableField("legal_person")
    private String legalPerson;
    /**
     * 企业负责人姓名
     */
    @Schema(description = "企业负责人姓名")
    @TableField("main_person")
    private String mainPerson;
    /**
     * 企业负责人电话
     */
    @Schema(description = "企业负责人电话")
    @TableField("main_person_mobile")
    private String mainPersonMobile;
    /**
     * x坐标
     */
    @Schema(description = "x坐标")
    @TableField("map_x")
    private String mapX;
    /**
     * y坐标
     */
    @Schema(description = "y坐标")
    @TableField("map_y")
    private String mapY;
    /**
     * z坐标
     */
    @Schema(description = "z坐标")
    @TableField("map_z")
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
    @TableField("create_time")
    private Date createTime;
    /**
     * 记录最后修改时间
     */
    @Schema(description = "记录最后修改时间")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 记录创建者(用户)
     */
    @Schema(description = "记录创建者(用户)")
    @TableField("create_user")
    private String createUser;
    /**
     * 记录最后修改者(用户)
     */
    @Schema(description = "记录最后修改者(用户)")
    @TableField("update_user")
    private String updateUser;

    /**
     * 获取：主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：工商注册码
     */
    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }

    /**
     * 设置：工商注册码
     */
    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    /**
     * 获取：企业编号
     */
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    /**
     * 设置：企业编号
     */
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    /**
     * 获取：企业名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 设置：企业名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     * 获取：所属行业
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 设置：所属行业
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    /**
     * 获取：所属区域
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置：所属区域
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取：企业类型(国企:0，民企:1，私企:2，外企:3)
     */
    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    /**
     * 设置：企业类型(国企:0，民企:1，私企:2，外企:3)
     */
    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    /**
     * 获取：企业联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置：企业联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取：企业邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：企业邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮政编码
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置：邮政编码
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取：法人
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * 设置：法人
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * 获取：企业负责人姓名
     */
    public String getMainPerson() {
        return mainPerson;
    }

    /**
     * 设置：企业负责人姓名
     */
    public void setMainPerson(String mainPerson) {
        this.mainPerson = mainPerson;
    }

    /**
     * 获取：企业负责人移动电话号码
     */
    public String getMainPersonMobile() {
        return mainPersonMobile;
    }

    /**
     * 设置：企业负责人移动电话号码
     */
    public void setMainPersonMobile(String mainPersonMobile) {
        this.mainPersonMobile = mainPersonMobile;
    }

    /**
     * 获取：x坐标
     */
    public String getMapX() {
        return mapX;
    }

    /**
     * 设置：x坐标
     */
    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    /**
     * 获取：y坐标
     */
    public String getMapY() {
        return mapY;
    }

    /**
     * 设置：y坐标
     */
    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    /**
     * 获取：z坐标
     */
    public String getMapZ() {
        return mapZ;
    }

    /**
     * 设置：z坐标
     */
    public void setMapZ(String mapZ) {
        this.mapZ = mapZ;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：企业状态（0-正常，1-禁用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：企业状态（0-正常，1-禁用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：记录创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：记录创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：记录最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：记录最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：记录创建者(用户)
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置：记录创建者(用户)
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：记录最后修改者(用户)
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置：记录最后修改者(用户)
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }
}
