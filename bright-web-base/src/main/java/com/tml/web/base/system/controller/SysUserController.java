package com.tml.web.base.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.tml.api.base.system.dto.UserDTO;
import com.tml.api.base.system.entity.SysUser;
import com.tml.api.base.system.service.ISysUserEnterpriseService;
import com.tml.api.base.system.service.ISysUserRoleService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.api.base.system.vo.UserVo;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.constant.CommonConstant;
import com.tml.common.core.exception.BaseException;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.core.vo.PageVo;
import com.tml.common.core.vo.SelectVo;
import com.tml.web.base.system.dto.PasswordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  用户表接口
 * @author JacksonTu
 * @date 2018/12/13 11:42
 */
@Slf4j
@Tag(name = "用户表接口")
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @DubboReference
    private ISysUserService sysUserService;
    @DubboReference
    private ISysUserRoleService sysUserRoleService;
    @DubboReference
    private ISysUserEnterpriseService sysUserEnterpriseService;

    /**
     * 所有用户列表
     */
    @Operation(summary = "用户列表")
    @GetMapping("/list")
    @SaCheckPermission("sys/user/list")
    public CommonResult<PageVo<UserVo>> list(UserDTO userDto) {
        //只有超级管理员，才能查看所有管理员列表
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        if (userId != CommonConstant.SUPER_ADMIN) {
            //非管理员查看自己和自己创建的账号
            userDto.setCreateUserId(userId);
            userDto.setId(userId);
        }
        PageVo<UserVo> page = sysUserService.pageList(userDto);

        return CommonResult.success(page);
    }

    /**
     * 修改登录用户密码
     */
    @Operation(summary = "修改登录用户密码")
    @PostMapping("/password")
    public CommonResult password(@RequestBody PasswordDto form) {
        if (StringUtils.isBlank(form.getNewPassword())) {
            return CommonResult.failed("新密码不为能空");
        }
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        SysUser user = sysUserService.getById(userId);
        String temp = form.getPassword() + user.getLoginName() + user.getSalt();
        String password = SecureUtil.md5(temp);
        if (!user.getPassword().equals(password)) {
            return CommonResult.failed("原密码不正确");
        }
        String newPassword = SecureUtil.md5(temp);
        user.setPassword(newPassword);
        user.setUpdateTime(new Date());
        sysUserService.updateById(user);
        return CommonResult.success("密码修改成功");
    }

    /**
     * 登录用户信息
     */
    @Operation(summary = "登录用户信息")
    @GetMapping("/info")
    public CommonResult<LoginUserVo> info() {
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        return CommonResult.success(sysUserService.selectLoginUserVoByLoginId(userId));
    }

    /**
     * 用户信息
     */
    @Operation(summary = "用户信息")
    @Parameter(in = ParameterIn.PATH, name = "userId", description = "主键ID", required = true)
    @GetMapping("/info/{userId}")
    @SaCheckPermission("sys/user/info")
    public CommonResult<SysUser> info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        List<Long> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
        user.setRoleIdList(roleIdList);
        List<Long> enterpriseIdList = sysUserEnterpriseService.selectEnterpriseIdByUserId(userId);
        user.setEnterpriseIdList(enterpriseIdList);
        return CommonResult.success(user);

    }

    /**
     * 保存用户
     */
    @Operation(summary = "保存用户")
    @PostMapping("/save")
    @SaCheckPermission("sys/user/save")
    public CommonResult save(@RequestBody @Validated SysUser user) {
        try {
            SysUser sysUser = sysUserService.selectByLoginName(user.getLoginName());
            if (!ObjectUtils.isEmpty(sysUser)) {
                return CommonResult.failed("登录名已存在");
            }
            String salt = RandomUtil.randomString(16);
            user.setSalt(salt);
            String temp = user.getPassword() + user.getLoginName() + salt;
            String pwd = SecureUtil.md5(temp);
            user.setPassword(pwd);
            user.setCreateTime(new Date());
            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            Long userId = loginUserVo.getId();
            user.setCreateUserId(userId);
            sysUserService.saveByVo(user);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("运行异常，请联系管理员");
        }
    }

    /**
     * 修改用户
     */
    @Operation(summary = "修改用户")
    @PostMapping("/update")
    @SaCheckPermission("sys/user/update")
    public CommonResult update(@RequestBody @Validated SysUser user) {
        try {
            if (StringUtils.isNotBlank(user.getPassword())) {
                String salt = RandomUtil.randomString(16);
                user.setSalt(salt);
                String temp = user.getPassword() + user.getLoginName() + salt;
                String pwd = SecureUtil.md5(temp);
                user.setPassword(pwd);
            } else {
                user.setPassword(null);
            }
            user.setUpdateTime(new Date());
            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            Long userId = loginUserVo.getId();
            user.setCreateUserId(userId);
            sysUserService.updateByVo(user);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("运行异常，请联系管理员");
        }
    }

    /**
     * 删除用户
     */
    @Operation(summary = "删除用户")
    @Parameter(name = "userIds", description = "用户ID数组", schema = @Schema(type = "array", implementation = Integer.class), required = true)
    @PostMapping("/delete")
    @SaCheckPermission("sys/user/delete")
    public CommonResult delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, CommonConstant.SUPER_ADMIN)) {
            return CommonResult.failed("系统管理员不能删除");
        }
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        if (ArrayUtils.contains(userIds, userId)) {
            return CommonResult.failed("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return CommonResult.success("");
    }

    /**
     * 用户选择树
     *
     * @return
     */
    @Operation(summary = "用户选择树")
    @GetMapping("/getUserTree")
    public CommonResult<List<SelectVo>> getUserTree() {
        try {
            List<SelectVo> nodeList = new ArrayList<>();
            List<SysUser> list = sysUserService.list();
            list.forEach(baseUser -> {
                SelectVo node = new SelectVo();
                node.setLabel(baseUser.getName());
                node.setValue(baseUser.getId().toString());
                nodeList.add(node);
            });
            return CommonResult.success(nodeList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("运行异常，请联系管理员");
        }
    }
}
