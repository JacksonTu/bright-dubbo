package com.tml.web.base.system.dto;

/**
 *  密码表单
 * @author JacksonTu
 * @date 2018/6/11 17:07
 */
public class PasswordDto {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
