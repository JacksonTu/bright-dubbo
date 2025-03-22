package com.tml.web.base.generator.controller;

import com.tml.api.base.generator.service.IGeneratorService;
import com.tml.common.core.api.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JacksonTu
 * @version 1.0
 *
 * @date 2020/11/22 11:43
 */
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @DubboReference
    private IGeneratorService generatorService;

    /**
     * 分页
     *
     * @param tableDto
     * @return
     */
    @GetMapping("/list")
    public CommonResult pageList(com.tml.api.base.generator.dto.TableDTO tableDto) {
        return CommonResult.success(generatorService.pageList(tableDto));
    }

    /**
     * 生成代码
     */
    @GetMapping("/code")
    public CommonResult code(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] tableNames = new String[]{};
        String tables = request.getParameter("tableNames");
        if (tables.indexOf(",") > 0) {
            tableNames = tables.split(",");
        } else {
            tableNames = new String[]{tables};
        }
        return CommonResult.success(generatorService.generatorCode(tableNames));

    }
}
