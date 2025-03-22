package com.tml.web.base.system.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import com.tml.api.base.system.dto.RoleDTO;
import com.tml.api.base.system.entity.SysRole;
import com.tml.api.base.system.entity.SysRoleResource;
import com.tml.api.base.system.service.ISysRoleResourceService;
import com.tml.api.base.system.service.ISysRoleService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.constant.CommonConstant;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.core.vo.PageVo;
import com.tml.common.core.vo.TreeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  角色管理
 * @author JacksonTu
 * @date 2018/12/13 15:12
 */
@Tag(name = "角色管理接口")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @DubboReference
    private ISysRoleService sysRoleService;
    @DubboReference
    private ISysRoleResourceService sysRoleResourceService;

    /**
     * 角色列表
     */
    @Operation(summary = "角色列表")
    @GetMapping("/list")
    public CommonResult<PageVo<SysRole>> list(RoleDTO roleDto) {
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (userId != CommonConstant.SUPER_ADMIN) {
            roleDto.setCreateUserId(userId);
        }
        PageVo<SysRole> page = sysRoleService.pageList(roleDto);
        return CommonResult.success(page);
    }

    /**
     * 角色选择列表
     */
    @Operation(summary = "角色选择列表")
    @GetMapping("/select")
    public CommonResult<List<SysRole>> select() {
        Map<String, Object> map = new HashMap<>();
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (userId != CommonConstant.SUPER_ADMIN) {
            map.put("createUserId", userId);
        }
        List<SysRole> list = sysRoleService.selectSysRoleList(map);

        return CommonResult.success(list);
    }

    /**
     * 角色信息
     */
    @Operation(summary = "角色信息")
    @Parameter(in = ParameterIn.PATH, name = "roleId", description = "主键ID", required = true)
    @GetMapping("/info/{roleId}")
    public CommonResult<SysRole> info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> resourceIdList = sysRoleResourceService.selectResourceIdListByRoleId(roleId);
        role.setResourceIdList(resourceIdList);
        List<SysRoleResource> roleResourceList = sysRoleResourceService.selectResourceNodeListByRoleId(roleId);
        List<TreeVo> treeVoList = Lists.newArrayList();
        if (!roleResourceList.isEmpty()) {
            roleResourceList.forEach(roleResource -> {
                TreeVo treeVo = new TreeVo();
                treeVo.setId(roleResource.getResourceId().toString());
                treeVo.setLabel(roleResource.getResource().getName());
                treeVoList.add(treeVo);
            });
        }
        role.setResourceNodeList(treeVoList);
        return CommonResult.success(role);
    }

    /**
     * 保存角色
     */
    @Operation(summary = "保存角色")
    @PostMapping("/save")
    public CommonResult save(@RequestBody SysRole role) {
        role.setCreateTime(new Date());
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        role.setCreateUserId(userId);
        sysRoleService.saveByVo(role);

        return CommonResult.success("");
    }

    /**
     * 修改角色
     */
    @Operation(summary = "修改角色")
    @PostMapping("/update")
    public CommonResult update(@RequestBody SysRole role) {
        role.setUpdateTime(new Date());
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        role.setCreateUserId(userId);
        sysRoleService.updateByVo(role);

        return CommonResult.success("");
    }

    /**
     * 删除角色
     */
    @Operation(summary = "删除角色")
    @Parameter(name = "roleIds", description = "角色ID数组", schema = @Schema(type = "array", implementation = Integer.class), required = true)
    @PostMapping("/delete")
    @SaCheckRole("admin")
    public CommonResult delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return CommonResult.success("");
    }

}
