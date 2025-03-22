package com.tml.web.base.notice.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.api.notice.entity.SysNoticeSend;
import com.tml.api.notice.service.ISysNoticeSendService;
import com.tml.common.core.api.CommonResult;
import com.tml.common.core.constant.WebsocketConstant;
import com.tml.common.core.vo.LoginUserVo;
import com.tml.web.base.websocket.CommonWebSocket;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 用户通告阅读标记表 Controller
 *
 * @author JacksonTu
 * @date 2020-10-12 15:03:04
 */
@Slf4j
@Validated
@RestController
@RequestMapping("notice/send")
public class SysNoticeSendController {

    @DubboReference
    private ISysNoticeSendService sysNoticeSendService;

    @DubboReference
    private ISysUserService sysUserService;

    @Resource
    private CommonWebSocket webSocket;

    @GetMapping
    @SaCheckPermission("notice/send/list")
    public CommonResult listSysNoticeSend(com.tml.api.notice.dto.NoticeSendDTO noticeSendDto) {
        return CommonResult.success(sysNoticeSendService.listSysNoticeSend(noticeSendDto));
    }

    @GetMapping("list")
    @SaCheckPermission("notice/send/list")
    public CommonResult pageSysNoticeSend(com.tml.api.notice.dto.NoticeSendDTO noticeSendDto) {
        return CommonResult.success(this.sysNoticeSendService.pageSysNoticeSend(noticeSendDto));
    }

    @GetMapping("info/{id}")
    @SaCheckPermission("notice/send/info")
    public CommonResult info(@PathVariable("id") Long id) {
        return CommonResult.success(this.sysNoticeSendService.getById(id));
    }

    @PostMapping("save")
    @SaCheckPermission("notice/send/save")
    public CommonResult saveSysNoticeSend(@Valid @RequestBody SysNoticeSend sysNoticeSend) {
        try {
            this.sysNoticeSendService.saveSysNoticeSend(sysNoticeSend);
            return CommonResult.success("新增成功");
        } catch (Exception e) {
            String message = "新增失败";
            log.error(message, e);
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    @PostMapping("delete")
    @SaCheckPermission("notice/send/delete")
    public CommonResult deleteSysNoticeSend(@RequestBody String[] ids) {
        try {
            this.sysNoticeSendService.deleteSysNoticeSend(ids);
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            String message = "删除失败";
            log.error(message, e);
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    @PostMapping("update")
    @SaCheckPermission("notice/send/update")
    public CommonResult updateSysNoticeSend(@RequestBody SysNoticeSend sysNoticeSend) {
        try {
            this.sysNoticeSendService.updateSysNoticeSend(sysNoticeSend);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 更新用户系统消息阅读状态
     *
     * @param noticeId
     * @return
     */
    @GetMapping("editByNoticeIdAndUserId/{noticeId}")
    public CommonResult editByNoticeIdAndUserId(@PathVariable("noticeId") Long noticeId) {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            Long userId = loginUserVo.getId();
            SysNoticeSend noticeSend = new SysNoticeSend();
            noticeSend.setUserId(userId);
            noticeSend.setNoticeId(noticeId);
            noticeSend.setCreateUser(loginUserVo.getLoginName());
            noticeSend.setCreateTime(new Date());
            noticeSend.setUpdateUser(loginUserVo.getLoginName());
            noticeSend.setUpdateTime(new Date());
            noticeSend.setReadFlag(WebsocketConstant.HAS_READ_FLAG);
            noticeSend.setReadTime(new Date());
            this.sysNoticeSendService.updateByUserIdAndNoticeId(noticeSend);
            JSONObject obj = new JSONObject();
            obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_REVOKE);
            webSocket.sendAllMessage(obj.toJSONString());
            return CommonResult.success("");
        } catch (Exception e) {
            String message = "更新阅读状态失败";
            log.error(message, e);
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }

    /**
     * 获取我的消息
     *
     * @param noticeSendDto
     * @return
     */
    @GetMapping("getMyNoticeSend")
    public CommonResult getMyNoticeSend(com.tml.api.notice.dto.NoticeSendDTO noticeSendDto) {

        LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
        Long userId = loginUserVo.getId();
        noticeSendDto.setUserId(userId);
        return CommonResult.success(this.sysNoticeSendService.pageMyNoticeSend(noticeSendDto));
    }

    /**
     * 一键已读
     *
     * @return
     */
    @GetMapping("readAll")
    public CommonResult readAll() {
        try {

            LoginUserVo loginUserVo = (LoginUserVo) StpUtil.getTokenSession().get("loginUser");
            Long userId = loginUserVo.getId();
            SysNoticeSend noticeSend = new SysNoticeSend();
            noticeSend.setUserId(userId);
            noticeSend.setCreateUser(loginUserVo.getLoginName());
            noticeSend.setCreateTime(new Date());
            noticeSend.setUpdateUser(loginUserVo.getLoginName());
            noticeSend.setUpdateTime(new Date());
            noticeSend.setReadFlag(WebsocketConstant.HAS_READ_FLAG);
            noticeSend.setReadTime(new Date());
            this.sysNoticeSendService.updateByUserIdAndNoticeId(noticeSend);
            JSONObject obj = new JSONObject();
            obj.put(WebsocketConstant.MSG_CMD, WebsocketConstant.CMD_REVOKE);
            webSocket.sendAllMessage(obj.toJSONString());
            return CommonResult.success("");
        } catch (Exception e) {
            String message = "全部已读失败";
            log.error(message, e);
            return CommonResult.failed("运行异常，请联系管理员");
        }
    }
}
