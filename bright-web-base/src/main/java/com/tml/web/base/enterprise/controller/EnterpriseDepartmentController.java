package com.tml.web.base.enterprise.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tml.api.base.enterprise.dto.EnterpriseDepartmentDto;
import com.tml.api.base.enterprise.entity.EnterpriseDepartment;
import com.tml.api.base.enterprise.service.IEnterpriseDepartmentService;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import com.tml.api.base.enterprise.vo.EnterpriseDepartmentVo;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.core.vo.SelectTreeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *  企业部门表
 * @author JacksonTu
 * @date 2018/12/17 11:29
 */
@Slf4j
@Tag(name = "企业部门接口")
@RestController
@RequestMapping("enterprise/enterpriseDepartment")
public class EnterpriseDepartmentController {
    @DubboReference
    private IEnterpriseDepartmentService enterpriseDepartmentService;

    @DubboReference
    private IEnterpriseService enterpriseService;

    @DubboReference
    private ISysUserService sysUserService;


    /**
     * 列表
     */
    @Operation(summary = "企业部门列表")
    @GetMapping("/list")
    @SaCheckPermission("enterprise/enterpriseDepartment/list")
    public CommonResult<List<EnterpriseDepartmentVo>> treeGrid(EnterpriseDepartmentDto enterpriseDepartmentDto) {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        // 不是管理员
        if (loginUserVo.getUserType() != 0) {
            enterpriseDepartmentDto.setUserId(loginUserVo.getId());
        }
        List<EnterpriseDepartmentVo> list = enterpriseDepartmentService.selectTreeGrid(enterpriseDepartmentDto);
        return CommonResult.success(list);
    }


    /**
     * 企业部门信息
     */
    @Operation(summary = "企业部门信息")
    @Parameter(in = ParameterIn.PATH, name = "id", description = "部门ID", required = true)
    @GetMapping("/info/{id}")
    @SaCheckPermission("enterprise/enterpriseDepartment/info")
    public CommonResult<EnterpriseDepartmentVo> info(@PathVariable("id") String id) {
        EnterpriseDepartment enterpriseDepartment = enterpriseDepartmentService.getById(id);
        EnterpriseDepartmentVo enterpriseDepartmentVo = new EnterpriseDepartmentVo();
        BeanUtils.copyProperties(enterpriseDepartment, enterpriseDepartmentVo);
        return CommonResult.success(enterpriseDepartmentVo);
    }

    /**
     * 保存企业部门信息
     */
    @Operation(summary = "保存企业部门信息")
    @PostMapping("/save")
    @SaCheckPermission("enterprise/enterpriseDepartment/save")
    public CommonResult save(@Valid @RequestBody EnterpriseDepartment enterpriseDepartment) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            enterpriseDepartment.setCreateTime(new Date());
            enterpriseDepartment.setCreateUser(loginUserVo.getLoginName());
            enterpriseDepartmentService.save(enterpriseDepartment);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 修改企业部门信息
     */
    @Operation(summary = "修改企业部门信息")
    @PostMapping("/update")
    @SaCheckPermission("enterprise/enterpriseDepartment/update")
    public CommonResult update(@Valid @RequestBody EnterpriseDepartment enterpriseDepartment) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            enterpriseDepartment.setUpdateUser(loginUserVo.getLoginName());
            enterpriseDepartment.setUpdateTime(new Date());
            enterpriseDepartmentService.updateById(enterpriseDepartment);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }

    }

    /**
     * 删除企业部门信息
     */
    @Operation(summary = "删除企业部门信息")
    @Parameter(name = "ids", description = "部门ID数组", schema = @Schema(type = "array", implementation = String.class), required = true)
    @PostMapping("/delete")
    @SaCheckPermission("enterprise/enterpriseDepartment/delete")
    public CommonResult deleteBatchIds(@RequestBody String[] ids) {
        try {
            List<String> idList = new ArrayList<>();
            Collections.addAll(idList, ids);
            if (idList != null && !idList.isEmpty()) {
                enterpriseDepartmentService.removeByIds(Arrays.asList(ids));
                for (String id : idList) {
                    QueryWrapper<EnterpriseDepartment> wrapper = new QueryWrapper<>();
                    wrapper.eq("parent_id", id);
                    enterpriseDepartmentService.remove(wrapper);
                }
                return CommonResult.success("删除成功");
            } else {
                return CommonResult.failed("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 企业部门树形选择
     *
     * @param enterpriseId
     * @return
     */
    @Operation(summary = "企业部门树形选择")
    @Parameter(name = "enterpriseId", description = "企业ID", required = true)
    @GetMapping("/getDeptSelectTree")
    public CommonResult<List<SelectTreeVo>> getDeptSelectTree(@RequestParam String enterpriseId) {
        try {
            List<SelectTreeVo> treeNodeList = Lists.newArrayList();
            Map<String, Object> params = Maps.newHashMap();
            if (StringUtils.isNotBlank(enterpriseId)) {
                params.put("enterpriseId", enterpriseId);
            }
            List<EnterpriseDepartment> departmentList = enterpriseDepartmentService.selectEnterpriseDepartmentList(params);
            if (!departmentList.isEmpty()) {
                departmentList.forEach(dept -> {
                    SelectTreeVo selectTreeVo = new SelectTreeVo();
                    selectTreeVo.setId(dept.getId().toString());
                    selectTreeVo.setName(dept.getDepartmentName());
                    selectTreeVo.setParentId(dept.getParentId().toString());
                    treeNodeList.add(selectTreeVo);
                });
            }
            return CommonResult.success(treeNodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

}
