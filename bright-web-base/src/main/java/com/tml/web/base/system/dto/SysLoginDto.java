package com.tml.web.base.system.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *  登录表单
 * @author JacksonTu
 * @date 2018/6/11 17:07
 */
@Schema(description = "登录参数")
@Data
public class SysLoginDto {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "验证码", hidden = true)
    private String captcha;
    @Schema(description = "检查码", hidden = true)
    private String checkKey;

}
