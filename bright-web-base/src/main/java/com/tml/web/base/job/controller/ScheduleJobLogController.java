package com.tml.web.base.job.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tml.api.base.job.entity.ScheduleJobLogEntity;
import com.tml.api.base.job.service.IScheduleJobLogService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  定时任务日志
 * @author JacksonTu
 * @date 2019/1/18 15:59
 **/
@Tag(name = "定时任务日志接口")
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @DubboReference
    private IScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @Operation(summary = "定时任务日志列表")
    @GetMapping("/list")
    @SaCheckPermission("sys/schedule/log")
    public CommonResult<PageVo<ScheduleJobLogEntity>> list(com.tml.api.base.job.dto.JobLogDTO jobLogDto) {
        PageVo<ScheduleJobLogEntity> page = scheduleJobLogService.queryPage(jobLogDto);

        return CommonResult.success(page);
    }

    /**
     * 定时任务日志信息
     */
    @Operation(summary = "定时任务日志信息")
    @Parameter(in = ParameterIn.PATH, name = "logId", description = "主键ID", required = true)
    @GetMapping("/info/{logId}")
    public CommonResult<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);

        return CommonResult.success(log);
    }
}
