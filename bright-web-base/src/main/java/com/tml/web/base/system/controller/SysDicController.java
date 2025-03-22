package com.tml.web.base.system.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import com.tml.api.base.system.entity.SysDic;
import com.tml.api.base.system.service.ISysDicService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.api.base.system.vo.DicVo;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.core.vo.SelectTreeVo;
import com.tml.common.core.vo.SelectVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author JacksonTu
 *  数据字典接口
 * @date 2018年3月6日 上午10:02:48
 */
@Slf4j
@Tag(name = "数据字典接口")
@RestController
@RequestMapping("/sys/dic")
public class SysDicController {

    @DubboReference
    private ISysUserService sysUserService;

    @DubboReference
    private ISysDicService sysDicService;

    @DubboReference
    private IEnterpriseService enterpriseService;

    /**
     * 数据字典树表
     *
     * @return
     */
    @Operation(summary = "日志列表")
    @Parameters({
            @Parameter(name = "dicName", description = "名称", required = false),
            @Parameter(name = "dicCode", description = "编码", required = false)
    })
    @GetMapping("/list")
    public CommonResult<List<DicVo>> treeGrid(@RequestParam(required = false) String dicName,
                                              @RequestParam(required = false) String dicCode) {
        Map<String, Object> par = new HashMap<>();
        if (StringUtils.isNotBlank(dicName)) {
            par.put("varName", StringUtils.deleteWhitespace(dicName));
        }
        if (StringUtils.isNotBlank(dicCode)) {
            par.put("varCode", StringUtils.deleteWhitespace(dicCode));
        }
        return CommonResult.success(sysDicService.selectTreeGrid(par));

    }

    /**
     * 数据字典选择
     *
     * @return
     */
    @Operation(summary = "数据字典选择")
    @Parameter(name = "parentId", description = "父ID", required = true)
    @GetMapping("/select/{parentId}")
    public CommonResult<List<SelectTreeVo>> select(@PathVariable("parentId") Long parentId) {
        Map<String, Object> params = Maps.newHashMap();
        if (parentId != null && 0 != parentId) {
            params.put("parentId", parentId);
        }
        List<SysDic> dicList = sysDicService.selectDicList(params);
        List<SelectTreeVo> treeNodeList = Lists.newArrayList();
        if (!dicList.isEmpty()) {
            dicList.forEach(dic -> {
                SelectTreeVo selectTreeVo = new SelectTreeVo();
                selectTreeVo.setId(dic.getId().toString());
                selectTreeVo.setParentId(dic.getParentId().toString());
                selectTreeVo.setName(dic.getVarName());
                treeNodeList.add(selectTreeVo);
            });
        }
        treeNodeList.add(SelectTreeVo.createParent());
        return CommonResult.success(treeNodeList);
    }

    /**
     * 字典信息
     *
     * @param dicId
     * @return
     */
    @Operation(summary = "字典信息")
    @Parameter(name = "dicId", description = "主键ID", required = true)
    @GetMapping("/info/{dicId}")
    public CommonResult<SysDic> info(@PathVariable("dicId") Long dicId) {
        SysDic sysDic = sysDicService.getById(dicId);
        SysDic sysDic2 = sysDicService.getById(sysDic.getParentId());
        if (sysDic2 != null) {
            sysDic.setParentName(sysDic.getVarName());
        } else {
            sysDic.setParentName("顶级");
        }
        return CommonResult.success(sysDic);
    }

    /**
     * 添加字典信息
     *
     * @param
     * @return
     */
    @Operation(summary = "添加字典信息")
    @PostMapping("/save")
    public CommonResult save(@Valid @RequestBody SysDic sysDic) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            sysDic.setCreateTime(new Date());
            sysDic.setCreateUser(loginUserVo.getLoginName());
            boolean b = sysDicService.save(sysDic);
            if (b) {
                return CommonResult.success("添加成功！");
            } else {
                return CommonResult.failed("添加失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("添加失败，请联系管理员");
        }

    }

    /**
     * 修改字典信息
     *
     * @param sysDic
     * @return
     */
    @Operation(summary = "修改字典信息")
    @PostMapping("/update")
    public CommonResult update(@Valid @RequestBody SysDic sysDic) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            sysDic.setUpdateTime(new Date());
            sysDic.setUpdateUser(loginUserVo.getLoginName());
            boolean b = sysDicService.updateById(sysDic);
            if (b) {
                return CommonResult.success("修改成功！");
            } else {
                return CommonResult.failed("修改失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("编辑失败，请联系管理员");
        }
    }

    /**
     * 删除字典信息
     *
     * @param dicId
     * @return
     */
    @Operation(summary = "删除字典信息")
    @Parameter(name = "roleId", description = "主键ID", required = true)
    @PostMapping("/delete/{dicId}")
    public CommonResult delete(@PathVariable("dicId") Long dicId) {
        try {
            sysDicService.removeById(dicId);
            QueryWrapper<SysDic> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id", dicId);
            sysDicService.remove(wrapper);
            return CommonResult.success("删除成功！");
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("批量删除失败，请联系管理员");
        }
    }

    /**
     * 批量删除字典信息
     *
     * @param ids
     * @return
     */
    @Operation(summary = "批量删除字典信息")
    @Parameter(name = "roleIds", description = "字典ID数组", required = true)
    @PostMapping("/delete")
    public CommonResult deleteBatchIds(@RequestParam Long[] ids) {
        try {
            List<Long> idList = new ArrayList<Long>();
            Collections.addAll(idList, ids);
            if (idList != null && !idList.isEmpty()) {
                sysDicService.removeByIds(Arrays.asList(ids));
                for (Long id : idList) {
                    QueryWrapper<SysDic> wrapper = new QueryWrapper<>();
                    wrapper.eq("parent_id", id);
                    sysDicService.remove(wrapper);
                }
                return CommonResult.success("删除成功！");
            } else {
                return CommonResult.failed("删除失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.failed("批量删除失败，请联系管理员");
        }
    }

    /**
     * 数据字典select树
     *
     * @return
     */
    @Operation(summary = "数据字典select树")
    @Parameter(name = "parentId", description = "父ID", required = true)
    @GetMapping("/selectNode/{parentId}")
    public CommonResult<List<SelectVo>> selectTree(@PathVariable("parentId") Long parentId) {
        List<SelectVo> tree = new ArrayList<>();
        Map<String, Object> par = new HashMap<>();
        par.put("parentId", parentId);
        List<Map<String, Object>> list = sysDicService.selectTreeByParentId(par);
        if (!list.isEmpty()) {
            for (Map<String, Object> map : list) {
                SelectVo selectVo = new SelectVo();
                selectVo.setLabel(map.get("varName").toString());
                selectVo.setValue(map.get("id").toString());
                tree.add(selectVo);
            }
        }
        return CommonResult.success(tree);
    }
}
