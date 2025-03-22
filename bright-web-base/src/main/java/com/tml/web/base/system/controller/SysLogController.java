package com.tml.web.base.system.controller;


import com.tml.api.base.system.entity.SysLog;
import com.tml.api.base.system.service.ISysLogService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JacksonTu
 *  日志管理
 * @date 2018年3月6日 上午9:42:00
 */
@Tag(name = "日志管理接口")
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @DubboReference
    private ISysLogService sysLogService;

    @Operation(summary = "日志列表")
    @GetMapping("/list")
    public CommonResult<PageVo<SysLog>> dataGrid(com.tml.api.base.system.dto.LogDTO logDto) {

        PageVo<SysLog> page = sysLogService.pageList(logDto);
        return CommonResult.success(page);
    }
}
