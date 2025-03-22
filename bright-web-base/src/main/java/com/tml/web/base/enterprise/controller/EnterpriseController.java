package com.tml.web.base.enterprise.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.tml.api.base.enterprise.entity.Enterprise;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import com.tml.api.base.enterprise.vo.EnterpriseVo;
import com.tml.api.base.system.entity.SysFile;
import com.tml.api.base.system.service.ISysDicService;
import com.tml.api.base.system.service.ISysFileService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.common.core.vo.PageVo;
import com.tml.common.core.vo.SelectVo;
import com.tml.web.base.system.controller.UpLoadController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 *  企业Controller
 * @author JacksonTu
 * @date 2018/12/17 11:14
 */
@Slf4j
@Tag(name = "企业接口")
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController extends UpLoadController {
    @DubboReference
    private ISysDicService sysDicService;

    @DubboReference
    private ISysUserService sysUserService;

    @DubboReference
    private IEnterpriseService enterpriseService;

    @DubboReference
    private ISysFileService sysFileService;

    private final Map<String, List<Map<String, String>>> uploadFileUrls = new HashMap<String, List<Map<String, String>>>();

    /**
     * 企业列表
     *
     * @return
     */
    @Operation(summary = "企业列表")
    @GetMapping("/list")
    @SaCheckPermission("enterprise/enterprise/list")
    public CommonResult<PageVo<EnterpriseVo>> treeGrid(com.tml.api.base.enterprise.dto.EnterpriseDTO enterpriseDto) {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        // 不是管理员
        if (loginUserVo.getUserType() != 0) {
            enterpriseDto.setUserId(userId);
        }
        PageVo<EnterpriseVo> page = enterpriseService.pageList(enterpriseDto);
        return CommonResult.success(page);
    }

    /**
     * 企业信息
     *
     * @param id
     * @return
     */
    @Operation(summary = "企业信息")
    @Parameter(name = "id", description = "企业ID", required = true)
    @GetMapping("/info/{id}")
    @SaCheckPermission("enterprise/enterprise/info")
    public CommonResult<Enterprise> info(@PathVariable("id") String id) {
        Enterprise enterprise = enterpriseService.getById(id);
        return CommonResult.success(enterprise);
    }

    /**
     * 保存企业信息
     *
     * @param
     * @return
     */
    @Operation(summary = "保存企业信息")
    @PostMapping("/save")
    @SaCheckPermission("enterprise/enterprise/save")
    public CommonResult save(@Valid @RequestBody Enterprise enterprise) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            enterprise.setCreateTime(new Date());
            enterprise.setCreateUser(loginUserVo.getLoginName());
            Enterprise enterprise1 = enterpriseService.saveEnterprise(enterprise);
            if (ObjectUtils.isNotEmpty(enterprise1)) {
                saveFile(enterprise1.getId().toString());
                return CommonResult.success("添加成功");
            } else {
                return CommonResult.failed("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }

    }

    /**
     * 修改企业信息
     *
     * @param enterprise
     * @return
     */
    @Operation(summary = "修改企业信息")
    @PostMapping("/update")
    @SaCheckPermission("enterprise/enterprise/update")
    public CommonResult update(@Valid @RequestBody Enterprise enterprise) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            enterprise.setUpdateTime(new Date());
            enterprise.setUpdateUser(loginUserVo.getLoginName());
            boolean b = enterpriseService.updateById(enterprise);
            saveFile(enterprise.getId().toString());
            if (b) {
                return CommonResult.success("修改成功");
            } else {
                return CommonResult.failed("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 删除企业信息
     *
     * @param ids
     * @return
     */
    @Operation(summary = "删除企业信息")
    @Parameter(name = "ids", description = "企业ID数组", required = true)
    @PostMapping("/delete")
    @SaCheckPermission("enterprise/enterprise/delete")
    public CommonResult deleteBatchIds(@RequestBody String[] ids) {
        enterpriseService.removeByIds(Arrays.asList(ids));
        return CommonResult.success("删除成功");
    }

    /**
     * 企业选择
     *
     * @param areaCode
     * @param industryCode
     * @return
     */
    @Operation(summary = "企业选择")
    @Parameters({
            @Parameter(name = "areaCode", description = "区域ID", required = false),
            @Parameter(name = "industryCode", description = "行业ID", required = false)
    })
    @GetMapping("/getEnterpriseTree")
    public CommonResult getEnterpriseTree(@RequestParam(required = false, value = "areaCode") Long areaCode,
                                          @RequestParam(required = false, value = "industryCode") Long industryCode) {
        try {
            List<SelectVo> nodeList = Lists.newArrayList();
            Map<String, Object> params = new HashMap<>();

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            Long userId = loginUserVo.getId();
            // 不是管理员
            if (loginUserVo.getUserType() != 0) {
                params.put("userId", userId);
            }
            params.put("industryCode", industryCode);
            List<Map<String, Object>> list = enterpriseService.selectEnterpriseList(params);
            if (!list.isEmpty()) {
                list.forEach(map -> {
                    SelectVo selectVo = new SelectVo();
                    selectVo.setValue(map.get("id").toString());
                    selectVo.setLabel(map.get("enterpriseName").toString());
                    nodeList.add(selectVo);
                });
            }
            return CommonResult.success(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 上传附件
     */
    @Operation(summary = "上传附件")
    @PostMapping("/uploadFile")
    public CommonResult uploadFile(@RequestParam("file") MultipartFile[] files) {
        Map<String, Object> param = new HashMap<>();
        try {
            List<Map<String, String>> uploadFileUrl = uploads(files, "enterprise");
            if (!uploadFileUrl.isEmpty()) {
                for (Map<String, String> map : uploadFileUrl) {
                    String name = map.get("fileName");
                    String url = map.get("filePath");
                    param.put("name", name);
                    param.put("url", url);
                    setUploadFile(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.success(param);
    }


    /**
     * 删除文件
     *
     * @param name 文件名
     * @param url  文件路径
     * @return
     */
    @GetMapping("/deleteFile")
    public CommonResult deleteFile(@RequestParam(value = "id") String id,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "url", required = false) String url) {
        try {
            sysFileService.deleteFile("t_enterprise", "", "", name, url);
            if (StringUtils.isNotBlank(url)) {
                deleteFileFromLocal(url);
            }
            resetUploadFile();
            return CommonResult.success("删除文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }


    /**
     * 列示文件
     *
     * @param id
     * @return
     */
    @GetMapping("/lookFile/{id}")
    public CommonResult listFile(@PathVariable("id") String id) {
        List<Map<String, String>> list = new ArrayList<>();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("tableId", "t_enterprise");
        param.put("recordId", id);
        List<SysFile> files = sysFileService.selectFileListByTableIdAndRecordId(param);
        if (!files.isEmpty()) {
            for (SysFile sysFile : files) {
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("name", sysFile.getAttachmentName());
                fileMap.put("url", sysFile.getAttachmentPath());
                list.add(fileMap);
            }
        }
        return CommonResult.success(list);
    }

    public CommonResult saveFile(String id) {
        try {
            if (getUploadFile() != null) {

                LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
                for (Map<String, String> uploadFileUrl : getUploadFile()) {
                    String fileName = uploadFileUrl.get("fileName");
                    String filePah = uploadFileUrl.get("filePath");
                    SysFile sysFile = new SysFile();
                    sysFile.setRecordId(id);
                    sysFile.setTableId("t_enterprise");
                    sysFile.setAttachmentGroup("企业");
                    sysFile.setAttachmentName(fileName);
                    sysFile.setAttachmentPath(filePah);
                    //附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他等)
                    String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if ("doc".equalsIgnoreCase(fileType) || "docx".equalsIgnoreCase(fileType)) {
                        sysFile.setAttachmentType(0);
                    } else if ("xls".equalsIgnoreCase(fileType) || "xlsx".equalsIgnoreCase(fileType)) {
                        sysFile.setAttachmentType(1);
                    } else if ("pdf".equalsIgnoreCase(fileType)) {
                        sysFile.setAttachmentType(2);
                    } else if ("jpg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType) || "gif".equalsIgnoreCase(fileType)) {
                        sysFile.setAttachmentType(3);
                    } else {
                        sysFile.setAttachmentType(4);
                    }
                    sysFile.setSaveType(0);
                    sysFile.setCreateTime(new Date());
                    sysFile.setCreateUser(loginUserVo.getLoginName());
                    sysFileService.save(sysFile);
                }
                resetUploadFile();
            }
            return CommonResult.success("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    private List<Map<String, String>> getUploadFile() {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        List<Map<String, String>> list = uploadFileUrls.get(userId.toString());
        return (list == null) ? (new ArrayList<Map<String, String>>()) : (list);
    }

    private void setUploadFile(Map<String, String> uploadFileUrl) {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        uploadFileUrls.computeIfAbsent(userId.toString(), k -> new ArrayList<Map<String, String>>());
        uploadFileUrls.get(userId.toString()).add(uploadFileUrl);
    }

    private void resetUploadFile() {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        uploadFileUrls.remove(userId.toString());
    }
}
