package com.tml.web.base.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.tml.api.base.system.entity.SysResource;
import com.tml.api.base.system.service.ISysResourceService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.api.base.system.vo.MenuVo;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.constant.CommonConstant;
import com.tml.common.core.exception.BaseException;
import com.tml.common.core.vo.LoginUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *  菜单管理接口
 * @author JacksonTu
 * @date 2018/12/11 14:21
 */
@Tag(name = "菜单管理接口")
@RestController
@RequestMapping("sys/menu")
public class SysResourceController {
    @DubboReference
    private ISysResourceService sysResourceService;
    @DubboReference
    private ISysUserService sysUserService;

    /**
     * 导航菜单
     */
    @Operation(summary = "导航菜单")
    @GetMapping("/nav")
    public CommonResult<MenuVo> nav() {
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        List<SysResource> menuList = sysResourceService.selectUserResourceListByUserId(userId);
        Set<String> permissions = sysUserService.selectUserPermissions(userId);
        MenuVo menuVo = new MenuVo();
        menuVo.setMenuList(menuList);
        menuVo.setPermissions(permissions);
        return CommonResult.success(menuVo);
    }

    /**
     * 所有菜单列表
     */
    @Operation(summary = "所有菜单列表")
    @GetMapping("/list")
    @SaCheckPermission("sys/menu/list")
    public CommonResult<List<SysResource>> list() {
        Map<String, Object> params = new HashMap<>();
        List<SysResource> menuList = sysResourceService.selectResourceList(params);
        return CommonResult.success(menuList);
    }

    /**
     * 菜单信息
     */
    @Operation(summary = "菜单信息")
    @Parameter(in = ParameterIn.PATH, name = "menuId", description = "主键ID", schema = @io.swagger.v3.oas.annotations.media.Schema(type = "integer"), required = true)
    @GetMapping("/info/{menuId}")
    @SaCheckPermission("sys/menu/info")
    public CommonResult<SysResource> info(@PathVariable("menuId") Long menuId) {
        SysResource sysResource = sysResourceService.getById(menuId);
        return CommonResult.success(sysResource);
    }

    /**
     * 保存菜单信息
     */
    @Operation(summary = "保存菜单信息")
    @PostMapping("/save")
    @SaCheckPermission("sys/menu/save")
    public CommonResult save(@RequestBody SysResource sysResource) {
        //数据校验
        verifyForm(sysResource);
        sysResource.setCreateTime(new Date());
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        sysResource.setCreateUser(loginUserVo.getLoginName());
        sysResourceService.save(sysResource);
        return CommonResult.success("");
    }

    /**
     * 修改菜单信息
     */
    @Operation(summary = "修改菜单信息")
    @PostMapping("/update")
    @SaCheckPermission("sys/menu/update")
    public CommonResult update(@RequestBody SysResource sysResource) {
        //数据校验
        verifyForm(sysResource);
        sysResource.setUpdateTime(new Date());
        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        sysResource.setUpdateUser(loginUserVo.getLoginName());
        sysResourceService.updateById(sysResource);
        return CommonResult.success("");
    }

    /**
     * 删除菜单信息
     */
    @Operation(summary = "删除菜单信息")
    @Parameter(in = ParameterIn.PATH, name = "menuId", description = "主键ID", schema = @io.swagger.v3.oas.annotations.media.Schema(type = "integer"), required = true)
    @PostMapping("/delete/{menuId}")
    @SaCheckPermission("sys/menu/delete")
    public CommonResult delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 31) {
            return CommonResult.failed("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysResource> menuList = sysResourceService.selectListByParentId(menuId);
        if (menuList.size() > 0) {
            return CommonResult.failed("请先删除子菜单或按钮");
        }
        sysResourceService.removeById(menuId);
        return CommonResult.success("");
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @Operation(summary = "选择菜单(添加、修改菜单)")
    @GetMapping("/select")
    @SaCheckPermission("sys/menu/select")
    public CommonResult<List<SysResource>> select() {
        //查询列表数据
        List<SysResource> menuList = sysResourceService.selectNotButtonList();
        //添加顶级菜单
        SysResource root = new SysResource();
        root.setId(0L);
        root.setName("顶级菜单");
        root.setParentId(0L);
        root.setOpen(true);
        menuList.add(root);
        return CommonResult.success(menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysResource sysResource) {
        if (StringUtils.isBlank(sysResource.getName())) {
            throw new BaseException("菜单名称不能为空");
        }
        if (sysResource.getParentId() == null) {
            throw new BaseException("上级菜单不能为空");
        }
        //菜单
        if (sysResource.getResourceType() == CommonConstant.MENU) {
            if (StringUtils.isBlank(sysResource.getUrl())) {
                throw new BaseException("菜单URL不能为空");
            }
        }
        //上级菜单类型
        int parentType = CommonConstant.CATALOG;
        if (sysResource.getParentId() != 0) {
            SysResource parentMenu = sysResourceService.getById(sysResource.getParentId());
            parentType = parentMenu.getResourceType();
        }
        //目录、菜单
        if (sysResource.getResourceType() == CommonConstant.CATALOG ||
                sysResource.getResourceType() == CommonConstant.MENU) {
            if (parentType != CommonConstant.CATALOG) {
                throw new BaseException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (sysResource.getResourceType() == CommonConstant.BUTTON) {
            if (parentType != CommonConstant.MENU) {
                throw new BaseException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
