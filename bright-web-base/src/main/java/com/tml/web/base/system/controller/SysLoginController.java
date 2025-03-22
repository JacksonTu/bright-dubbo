package com.tml.web.base.system.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.tml.api.base.enterprise.entity.Enterprise;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import com.tml.api.base.system.entity.SysUser;
import com.tml.api.base.system.service.ISysLogService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.constant.CommonConstant;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.redis.utils.RedisUtil;
import com.tml.web.base.system.dto.SysLoginDto;
import com.wf.captcha.SpecCaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *  登录退出接口
 * @author JacksonTu
 * @date 2018/6/11 17:07
 */
@Slf4j
@Tag(name = "登录退出接口")
@RestController
public class SysLoginController {

    @DubboReference
    private ISysUserService userService;

    @DubboReference
    private IEnterpriseService enterpriseService;

    @DubboReference
    private ISysLogService sysLogService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/sys/captcha")
    public CommonResult captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = IdUtil.fastUUID();
        // 存入redis并设置过期时间为30分钟
        redisUtil.setEx(key, verCode, 1800L);
        // 将key和base64返回给前端
        ConcurrentMap<String, Object> dataMap = new ConcurrentHashMap<>();
        dataMap.put("key", key);
        dataMap.put("image", specCaptcha.toBase64());
        return CommonResult.success(dataMap);
    }

    /**
     * 登录
     */
    @Operation(summary = "登录")
    @PostMapping("/sys/login")
    public CommonResult login(@RequestBody SysLoginDto loginDto) {

        log.info("POST请求登录");
        String username = decrypt(loginDto.getUsername());
        String password = decrypt(loginDto.getPassword());
        String verCode = loginDto.getCaptcha();
        String verKey = loginDto.getCheckKey();

        if (StringUtils.isBlank(username)) {
            return CommonResult.failed("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return CommonResult.failed("密码不能为空");
        }
        if (StringUtils.isBlank(verCode)) {
            return CommonResult.failed("验证码不能为空");
        }

        //TODO: 获取redis中的验证码
        String redisCode = (String) redisUtil.get(verKey);
        //TODO: 判断验证码
        if (redisCode != null && (StringUtils.isBlank(redisCode) || !redisCode.equals(verCode.trim().toLowerCase()))) {
            return CommonResult.failed("验证码不正确");
        }

        SysUser sysUser = userService.selectByLoginName(username);

        if (ObjectUtils.isEmpty(sysUser)) {
            return CommonResult.failed("账号不存在");
        }
        String temp = password + username + sysUser.getSalt();
        if (!sysUser.getPassword().equals(SecureUtil.md5(temp))) {
            return CommonResult.failed("密码不正确");
        }
        if (sysUser.getStatus() == 1) {
            return CommonResult.failed("账号被禁用");
        }

        //TODO:当企业不存在或者企业被禁用不允许登录
        if (sysUser.getUserType() == 1) {
            Enterprise sysEnterprise = enterpriseService.getById(sysUser.getEnterpriseId());
            if (null != sysEnterprise && sysEnterprise.getStatus() == 1) {
                return CommonResult.failed("企业被禁用，该账户不允许登录");
            } else if (null == sysEnterprise) {
                return CommonResult.failed("企业不存在，该账户不允许登录");
            }
        }

        //TODO: 生成token
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(sysUser, loginUserVo);
        StpUtil.login(loginUserVo.getId());
        StpUtil.getTokenSession().set("loginUser", loginUserVo);
        LoginUserVo tokenLoginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        System.out.println(tokenLoginUserVo);
        System.out.println(tokenLoginUserVo.getId());
        String token = StpUtil.getTokenValue();
        ConcurrentMap<String, Object> concurrentMap = Maps.newConcurrentMap();
        concurrentMap.put("token", token);
        log.info("用户名:{},登录成功！ ", loginUserVo.getName());
        sysLogService.addLog(loginUserVo.getLoginName(), "用户名: " + loginUserVo.getName() + ",登录成功！", 1, null);
        return CommonResult.success(concurrentMap);
    }

    /**
     * 退出
     */
    @Operation(summary = "退出")
    @PostMapping("/sys/logout")
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response) {
        //用户退出逻辑
        String token = request.getHeader(CommonConstant.JWT_DEFAULT_TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            return CommonResult.failed("退出登录失败!");
        }
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        SysUser sysUser = userService.getById(userId);
        if (!ObjectUtils.isEmpty(sysUser)) {
            StpUtil.logout();
            // 获取当前会话是否已经登录，返回true=已登录，false=未登录
            boolean isLogin = StpUtil.isLogin();
            if (isLogin) {
                return CommonResult.failed("退出登录失败!");
            }
            sysLogService.addLog(sysUser.getLoginName(), "用户名: " + sysUser.getName() + ",退出成功！", 1, null);
            return CommonResult.success("退出登录成功!");
        } else {
            return CommonResult.failed("Token无效!");
        }
    }

    /**
     * 加密
     *
     * @param data
     * @return
     */
    @Operation(summary = "加密")
    @Parameters({
            @Parameter(name = "data", description = "待加密字符串", required = false, schema = @Schema(type = "string")),
    })
    @GetMapping("/sys/encrypt")
    public String encrypt(String data) {
        /** AES加解密 */
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, "1234567812345678".getBytes(), "1234567812345678".getBytes());
        // 解密
        String s = aes.encryptBase64(data);
        return s;
    }

    /**
     * 解密
     *
     * @param encrypt
     * @return
     */
    private String decrypt(String encrypt) {
        /** AES加解密 */
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, "1234567812345678".getBytes(), "1234567812345678".getBytes());
        // 解密
        String s = aes.decryptStr(encrypt).replace("\"", "");
        return s;
    }

}
