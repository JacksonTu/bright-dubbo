package com.tml.api.base.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Schema(description = "数据字典表")
@Data
@EqualsAndHashCode(callSuper = false)
public class DicVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父变量ID
     */
    @Schema(description = "父变量ID")
    private Long parentId;
    /**
     * 变量代码
     */
    @Schema(description = "变量代码")
    private String varCode;
    /**
     * 变量名称
     */
    @Schema(description = "变量名称")
    private String varName;
    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    private Date createTime;
    /**
     * 记录修改时间
     */
    @Schema(description = "记录修改时间")
    private Date updateTime;
    /**
     * 记录创建者（用户）
     */
    @Schema(description = "记录创建者（用户）")
    private String createUser;
    /**
     * 记录最后修改者（用户）
     */
    @Schema(description = "记录最后修改者（用户）")
    private String updateUser;
}
