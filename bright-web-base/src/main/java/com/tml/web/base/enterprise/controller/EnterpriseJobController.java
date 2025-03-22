package com.tml.web.base.enterprise.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tml.api.base.enterprise.entity.EnterpriseDepartment;
import com.tml.api.base.enterprise.entity.EnterpriseJob;
import com.tml.api.base.enterprise.service.IEnterpriseDepartmentService;
import com.tml.api.base.enterprise.service.IEnterpriseJobService;
import com.tml.api.base.enterprise.vo.EnterpriseJobVo;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.core.vo.PageVo;
import com.tml.common.core.vo.SelectVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  企业职务配置接口
 * @author JacksonTu
 * @date 2018/12/17 11:30
 */
@Slf4j
@Tag(name = "企业职务配置接口")
@RestController
@RequestMapping("enterprise/enterpriseJob")
public class EnterpriseJobController {

    @DubboReference
    private ISysUserService sysUserService;

    @DubboReference
    private IEnterpriseJobService enterpriseJobService;

    @DubboReference
    private IEnterpriseDepartmentService enterpriseDepartmentService;

    /**
     * 企业职务配置列表
     */
    @Operation(summary = "企业职务配置列表")
    @GetMapping("/list")
    @SaCheckPermission("enterprise/enterpriseJob/list")
    public CommonResult<PageVo<EnterpriseJobVo>> list(com.tml.api.base.enterprise.dto.EnterpriseJobDTO enterpriseJobDto) {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        // 不是管理员
        if (loginUserVo.getUserType() != 0) {
            enterpriseJobDto.setUserId(loginUserVo.getId());
        }
        PageVo<EnterpriseJobVo> page = enterpriseJobService.pageList(enterpriseJobDto);
        return CommonResult.success(page);
    }


    /**
     * 企业职务配置信息
     */
    @Operation(summary = "企业职务配置信息")
    @Parameter(name = "id", description = "职位ID", required = true)
    @GetMapping("/info/{id}")
    @SaCheckPermission("enterprise/enterpriseJob/info")
    public CommonResult<EnterpriseJob> info(@PathVariable("id") String id) {
        EnterpriseJob enterpriseJob = enterpriseJobService.getById(id);
        EnterpriseDepartment department = enterpriseDepartmentService.getById(enterpriseJob.getDepartmentId());
        enterpriseJob.setEnterpriseDepartment(department);
        return CommonResult.success(enterpriseJob);
    }

    /**
     * 保存企业职务配置信息
     */
    @Operation(summary = "保存企业职务配置信息")
    @PostMapping("/save")
    @SaCheckPermission("enterprise/enterpriseJob/save")
    public CommonResult save(@Valid @RequestBody EnterpriseJob enterpriseJob) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            enterpriseJob.setCreateTime(new Date());
            enterpriseJob.setCreateUser(loginUserVo.getLoginName());
            enterpriseJobService.save(enterpriseJob);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 修改企业职务配置信息
     */
    @Operation(summary = "修改企业职务配置信息")
    @PostMapping("/update")
    @SaCheckPermission("enterprise/enterpriseJob/update")
    public CommonResult update(@Valid @RequestBody EnterpriseJob enterpriseJob) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            enterpriseJob.setUpdateUser(loginUserVo.getLoginName());
            enterpriseJob.setUpdateTime(new Date());
            enterpriseJobService.updateById(enterpriseJob);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }

    }

    /**
     * 删除企业职务配置信息
     */
    @Operation(summary = "删除企业职务配置信息")
    @Parameter(name = "ids", description = "职位ID数组", schema = @Schema(type = "array", implementation = String.class), required = true)
    @PostMapping("/delete")
    @SaCheckPermission("enterprise/enterpriseJob/delete")
    public CommonResult delete(@RequestBody String[] ids) {
        try {
            enterpriseJobService.removeByIds(Arrays.asList(ids));
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 企业部门职位选择
     *
     * @param deptId
     * @return
     */
    @Operation(summary = "企业部门职位选择")
    @Parameter(name = "deptId", description = "部门ID", required = true)
    @GetMapping("/selectJobTree")
    public CommonResult<List<SelectVo>> selectJobTree(@RequestParam String deptId) {
        try {
            List<SelectVo> nodeList = Lists.newArrayList();
            Map<String, Object> params = Maps.newHashMap();
            if (StringUtils.isNotBlank(deptId)) {
                params.put("deptId", deptId);
            }
            List<EnterpriseJob> jobList = enterpriseJobService.selectEnterpriseJobList(params);
            if (!jobList.isEmpty()) {
                jobList.forEach(job -> {
                    SelectVo selectVo = new SelectVo();
                    selectVo.setValue(job.getId().toString());
                    selectVo.setLabel(job.getJobName());
                    nodeList.add(selectVo);
                });
            }
            return CommonResult.success(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

}
