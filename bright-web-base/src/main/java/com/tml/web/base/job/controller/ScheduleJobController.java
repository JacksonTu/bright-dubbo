package com.tml.web.base.job.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tml.api.base.job.entity.ScheduleJobEntity;
import com.tml.api.base.job.service.IScheduleJobService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 *  定时任务
 * @author JacksonTu
 * @date 2019/1/18 15:59
 **/
@Tag(name = "定时任务接口")
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @DubboReference
    private IScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @Operation(summary = "定时任务列表")
    @GetMapping("/list")
    @SaCheckPermission("sys/schedule/list")
    public CommonResult<PageVo<ScheduleJobEntity>> list(com.tml.api.base.job.dto.JobDTO jobDto) {
        PageVo<ScheduleJobEntity> page = scheduleJobService.queryPage(jobDto);

        return CommonResult.success(page);
    }

    /**
     * 定时任务信息
     */
    @Operation(summary = "定时任务日志信息")
    @Parameter(in = ParameterIn.PATH, name = "jobId", description = "主键ID", required = true)
    @GetMapping("/info/{jobId}")
    @SaCheckPermission("sys/schedule/info")
    public CommonResult<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);

        return CommonResult.success(schedule);
    }

    /**
     * 保存定时任务信息
     */
    @Operation(summary = "保存定时任务信息")
    @PostMapping("/save")
    @SaCheckPermission("sys/schedule/save")
    public CommonResult save(@RequestBody ScheduleJobEntity scheduleJob) {
        scheduleJobService.insert(scheduleJob);

        return CommonResult.success("");
    }

    /**
     * 修改定时任务信息
     */
    @Operation(summary = "修改定时任务信息")
    @PostMapping("/update")
    @SaCheckPermission("sys/schedule/update")
    public CommonResult update(@RequestBody ScheduleJobEntity scheduleJob) {
        scheduleJobService.update(scheduleJob);

        return CommonResult.success("");
    }

    /**
     * 删除定时任务信息
     */
    @Operation(summary = "删除定时任务信息")
    @Parameter(name = "jobIds", description = "主键ID数组", schema = @Schema(type = "array", implementation = Integer.class), required = true)
    @PostMapping("/delete")
    @SaCheckPermission("sys/schedule/delete")
    public CommonResult delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return CommonResult.success("");
    }

    /**
     * 立即执行任务信息
     */
    @Operation(summary = "立即执行任务信息")
    @Parameter(name = "jobIds", description = "主键ID数组", schema = @Schema(type = "array", implementation = Integer.class), required = true)
    @PostMapping("/run")
    @SaCheckPermission("sys/schedule/run")
    public CommonResult run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);

        return CommonResult.success("");
    }

    /**
     * 暂停定时任务信息
     */
    @Operation(summary = "暂停定时任务信息")
    @Parameter(name = "jobIds", description = "主键ID数组", schema = @Schema(type = "array", implementation = Integer.class), required = true)
    @PostMapping("/pause")
    @SaCheckPermission("sys/schedule/pause")
    public CommonResult pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);

        return CommonResult.success("");
    }

    /**
     * 恢复定时任务信息
     */
    @Operation(summary = "恢复定时任务信息")
    @Parameter(name = "jobIds", description = "主键ID数组", schema = @Schema(type = "array", implementation = Integer.class), required = true)
    @PostMapping("/resume")
    @SaCheckPermission("sys/schedule/resume")
    public CommonResult resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);

        return CommonResult.success("");
    }

}
